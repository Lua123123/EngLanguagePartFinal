package com.example.englanguagefinal

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.databinding.ActivityMainBinding
import com.example.englanguagefinal.exam.StartQuizActivity
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {
    private var backPressedTime: Long = 0
    private lateinit var authorization: String
    private lateinit var binding: ActivityMainBinding
    private lateinit var reference: DatabaseReference
    private lateinit var user: FirebaseUser
    private var name: String? = null
    private var email: String? = null
    //SHARED PREFERENCE
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        supportActionBar?.hide()

        getSharedPreferencesMain()

        getFirebaseInformation()

        binding.topic.setOnClickListener {
            launchActivity(TopicActivity::class.java)
            finish()
        }

        binding.vocabulary.setOnClickListener {
            launchActivity(VocabularyActivity::class.java)
            finish()
        }

        binding.speak.setOnClickListener {
            launchActivity(TextToSpeechActivity::class.java)
            finish()
        }

        binding.logout.setOnClickListener {
            openDialogInsertVocabulary(Gravity.CENTER)
        }

        binding.flipcard.setOnClickListener {
            launchActivity(FlipCardActivity::class.java)
            finish()
        }

        binding.videoExo.setOnClickListener {
            launchActivity(ExoMenuActivity::class.java)
            finish()
        }

        binding.test.setOnClickListener{
            launchActivity(StartQuizActivity::class.java)
            finish()
        }

        binding.imgInformation.setOnClickListener{
            clickOpenInformation()
        }
    }


    private fun getSharedPreferencesMain() {
        /**
         *SHARED PREFERENCES
         */
        sharedPref = this.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        authorization = sharedPref.getString("Authorization", "").toString()
    }

    private fun getFirebaseInformation() {
        /**
         * FIREBASE
         */
        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        reference.child(user.uid).child("name").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                name = snapshot.value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                toast("Something wrong happened!")
            }
        })
        reference.child(user.uid).child("email").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                email = snapshot.value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                toast("Something wrong happened!")
            }
        })
    }

    private fun clickOpenInformation() {
        /**
         * BOTTOM_SHEET_DIALOG
         */
        val viewDialog: View = layoutInflater.inflate(R.layout.layout_bottom_information, null)
        val bottomSheetDialog = BottomSheetDialog(this@MainActivity)
        bottomSheetDialog.setContentView(viewDialog)
        bottomSheetDialog.show()
        bottomSheetDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        bottomSheetDialog.setCancelable(true)
        val tvName: TextView = viewDialog.findViewById(R.id.tvName)
        val tvEmail: TextView = viewDialog.findViewById(R.id.tvEmail)
        tvName.text = "Full name: $name"
        tvEmail.text = "Email: $email"
        val btnHide: Button = viewDialog.findViewById(R.id.btnHide)
        btnHide.setOnClickListener {
            bottomSheetDialog.dismiss()
        }
    }


    private fun openDialogInsertVocabulary(gravity: Int) {
        val dialog = Dialog(this@MainActivity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_logout)
        val window = dialog.window ?: return
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val btnCancel = dialog.findViewById<Button>(R.id.btnCancelDialog)
        val btnConfirm = dialog.findViewById<Button>(R.id.btnConFirmDialog)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            launchActivity(LoginActivity::class.java)
            FirebaseAuth.getInstance().signOut()
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            AlertDialog.Builder(this)
                .setTitle("Do you want to exit?")
                .setNegativeButton("Cancel", null)
                .setPositiveButton(
                    "Confirm",
                    DialogInterface.OnClickListener { _, _ ->
                        setResult(RESULT_OK, Intent().putExtra("Exit", true))
                        finish()
                    }).create().show()
        } else {
            toast("Press again to exit!")
        }
        backPressedTime = System.currentTimeMillis()
    }
}