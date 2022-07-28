package com.example.englanguagefinal

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.adapter.ListVocabularyOfTopicAdapter
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.example.englanguagefinal.databinding.ActivityVocabularyOfTopicBinding
import java.util.ArrayList

class VocabularyOfTopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVocabularyOfTopicBinding
    private lateinit var adapter: ListVocabularyOfTopicAdapter
    private val postsList: MutableList<SuccessVocabulary> = ArrayList()
    private var viewModelApp: ViewModelApp = ViewModelApp(context = this@VocabularyOfTopicActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary_of_topic)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary_of_topic)
        supportActionBar?.hide()

        val intent = intent
        val bundle = intent.getBundleExtra("data")
        val position = bundle!!.getInt("id")
        val nameTopic = bundle.getString("nameTopic")

        binding.tvTopic.text = nameTopic

        binding.rcvVocabularyOfTopic.apply {
            val itemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.divider_rcv
                )!!
            )
            this.addItemDecoration(itemDecoration)
            this.layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListVocabularyOfTopicAdapter(postsList, context = this@VocabularyOfTopicActivity)
        binding.rcvVocabularyOfTopic.adapter = adapter

        binding.imgBack.setOnClickListener {
            launchActivity(TopicActivity::class.java)
            finish()
        }
        binding.layoutLoading.visibility = View.VISIBLE
        viewModelApp.mGetVocabularyOfTopic(position, postsList, adapter, binding.layoutLoading)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        launchActivity(TopicActivity::class.java)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}