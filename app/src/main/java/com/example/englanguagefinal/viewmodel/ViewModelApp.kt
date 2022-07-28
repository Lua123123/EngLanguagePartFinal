package com.example.englanguagefinal.viewmodel

import android.content.Context
import android.content.SharedPreferences
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.englanguagefinal.LoginActivity
import com.example.englanguagefinal.MainActivity
import com.example.englanguagefinal.adapter.ListTopicAdapter
import com.example.englanguagefinal.adapter.ListVocabularyOfTopicAdapter
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.example.englanguagefinal.model.login.Login
import com.example.englanguagefinal.model.login.UserFireBase
import com.example.englanguagefinal.model.login.UserLogin
import com.example.englanguagefinal.model.signup.SignUp
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.model.topic.Topic
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.model.vocabulary.Vocabulary
import com.example.englanguagefinal.network.API
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ViewModelApp(var context: Context) : ViewModel() {

    val mVocabulary = MutableLiveData<Vocabulary>()
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private lateinit var job: Job
    private val rememberMe = true
    //SHARED PREFERENCE
    private var sharedPref: SharedPreferences? = null

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun mutableLiveDataClickGetVocabulary() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabulary()
        }
    }

    private fun clickGetVocabulary(): List<SuccessVocabulary?>? {

        API.api.getVocabulary(1, "", "10")?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                val vocabulary: Vocabulary? = response.body()
                if (vocabulary != null) {
                    mVocabulary.postValue(vocabulary!!)
                } else {
                    onError("Error: ${response.message()} ")
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
            }
        })
        return null
    }

    fun mClickLogin(
        email: String,
        password: String,
        layoutLoading: ConstraintLayout,
        mAuth: FirebaseAuth
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickLogin(email, password, layoutLoading, mAuth)
        }
    }

    private fun clickLogin(
        email: String,
        password: String,
        layoutLoading: ConstraintLayout,
        mAuth: FirebaseAuth
    ) {
        val userLogin = UserLogin(email, password, rememberMe)
        //SHARED PREFERENCE
        sharedPref = context.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPref?.edit()
        API.api.getUsers(userLogin)?.enqueue(object : Callback<Login?> {
            override fun onResponse(call: Call<Login?>, response: Response<Login?>) {
                val login = response.body()!!
                val status = login.status.toString()
                if (status == "true") {
                    val tokenType = login.data.token_type.trim { it <= ' ' }
                    val accessToken = login.data.access_token.trim { it <= ' ' }
                    val authorization = "$tokenType $accessToken"
                    //SHARED PREFERENCE
                    editor?.putString("Authorization", authorization)
                    editor?.apply()
                    loginFirebase(email, password, mAuth, layoutLoading)
                } else {
                    context.toast("EMAIL OR PASSWORD INVALID!")
                    layoutLoading.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Login?>, t: Throwable) {
                context.toast("LOGIN FAILED!")
                layoutLoading.visibility = View.GONE
            }
        })
    }

    private fun loginFirebase(
        email: String,
        password: String,
        mAuth: FirebaseAuth,
        layoutLoading: ConstraintLayout
    ) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                if (user?.isEmailVerified == true) {
                    context.launchActivity(MainActivity::class.java)
                    context.toast("LOGIN SUCCESSFULLY")
                    layoutLoading.visibility = View.GONE
                } else {
                    user?.sendEmailVerification()
                    context.toast("CHECK YOUR EMAIL TO VERIFY YOUR ACCOUNT!")
                    layoutLoading.visibility = View.GONE
                    return@addOnCompleteListener
                }
            } else {
                context.toast("FAILED!!!!!!!!!!!!")
                layoutLoading.visibility = View.GONE
            }
        }
    }

    fun clickSignUp(
        email: String?,
        password: String?,
        name: String?,
        conformPassword: String?,
        mAuth: FirebaseAuth
    ) {
        //SHARED PREFERENCE
        sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPref?.edit()
        API.api.postUsers(email, password, name, conformPassword)
            ?.enqueue(object : Callback<SignUp?> {
                override fun onResponse(call: Call<SignUp?>, response: Response<SignUp?>) {
                    editor?.putString("email", "")
                    editor?.putString("password", "")
                    editor?.apply()
                    registerFirebase(email, password, name, mAuth)
                }

                override fun onFailure(call: Call<SignUp?>, t: Throwable) {
                    context.toast("Please check your Internet connection!!!")
                }
            })
    }

    private fun registerFirebase(
        email: String?,
        password: String?,
        name: String?,
        mAuth: FirebaseAuth
    ) {
        mAuth.createUserWithEmailAndPassword(email!!, password!!).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userFireBase = UserFireBase(email, name!!, password)
                FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .setValue(userFireBase).addOnCompleteListener {
                    if (it.isSuccessful) {
                        context.toast("SIGN UP SUCCESSFULLY")
                        context.launchActivity(LoginActivity::class.java)
                    } else {
                        context.toast("The email was in existence!")
                    }
                }
            } else {
                context.toast("Sign up failed!")
            }
        }
    }

    fun mGetVocabularyOfTopic(
        position: Int,
        postsList: MutableList<SuccessVocabulary>,
        adapter: ListVocabularyOfTopicAdapter,
        layoutLoading: ConstraintLayout
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabularyOfTopic(position, postsList, adapter, layoutLoading)
        }
    }

    private fun clickGetVocabularyOfTopic(
        position: Int,
        postsList: MutableList<SuccessVocabulary>,
        adapter: ListVocabularyOfTopicAdapter,
        layoutLoading: ConstraintLayout
    ) {
        var totalList: List<SuccessVocabulary> = ArrayList()

        API.api.getVocabularyOfTopic(1, position)?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                val vocabulary = response.body()
                for (i in vocabulary?.success?.indices!!) {
                    val successVocabulary = SuccessVocabulary(
                        0,
                        vocabulary.success?.get(i)?.word!!,
                        vocabulary.success?.get(i)?.mean!!,
                        vocabulary.success?.get(i)?.example!!
                    )

                    postsList.add(successVocabulary)
                }
                adapter.notifyDataSetChanged()

                if (position == 0) {
                    totalList = postsList
                    VocabularyDatabase.getInstance(context).VocabularyOfTopicDAO()?.insertVocabularyOfTopic(totalList)
                }
                layoutLoading.visibility = View.GONE
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                context.toast("LOAD DATA VOCABULARY OF TOPIC FAILED")
                layoutLoading.visibility = View.GONE
            }
        })
    }

    fun mClickGetTopic(
        postsList: MutableList<Success>,
        adapter: ListTopicAdapter,
        layoutLoading: ConstraintLayout
    ) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetTopic(postsList, adapter, layoutLoading)
        }
    }

    private fun clickGetTopic(
        postsList: MutableList<Success>,
        adapter: ListTopicAdapter,
        layoutLoading: ConstraintLayout
    ) {
        API.api.getTopics(1)?.enqueue(object : Callback<Topic?> {
            override fun onResponse(call: Call<Topic?>, response: Response<Topic?>) {
                val topic = response.body()
                for (i in topic!!.success!!.indices) {
                    val success = Success(
                        topic.success!![i].name,
                        topic.success!![i].soluong,
                        topic.success!![i].id
                    )
                    postsList.add(success)

                    if (VocabularyDatabase.getInstance(context).topicDAO()?.getListTopic()?.size!! <= postsList.size) {
                        val strTopic = topic.success!![i].name
                        val strAmount = topic.success!![i].soluong
                        val successDataRoom = Success(strTopic, strAmount, 0)
                        VocabularyDatabase.getInstance(context).topicDAO()?.insertTopic(successDataRoom)
                    }

                }
                adapter!!.notifyDataSetChanged()
                layoutLoading.visibility = View.GONE
            }

            override fun onFailure(call: Call<Topic?>, t: Throwable) {
                context.toast("SHOW LIST TOPIC FAILED")
                layoutLoading.visibility = View.GONE
            }
        })
    }
}