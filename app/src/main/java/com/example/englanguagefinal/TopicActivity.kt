package com.example.englanguagefinal

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.adapter.ListTopicAdapter
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.example.englanguagefinal.databinding.ActivityTopicBinding

class TopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicBinding
    private lateinit var adapter: ListTopicAdapter
    private val postsList: MutableList<Success> = ArrayList()
    private var viewModelApp: ViewModelApp = ViewModelApp(context = this@TopicActivity)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topic)
        supportActionBar?.hide()

        binding.recyclerView.apply {
            val itemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                    applicationContext,
                    R.drawable.divider_rcv
                )!!
            )
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListTopicAdapter(postsList, context = this@TopicActivity)
        binding.recyclerView.adapter = adapter

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
        }
        binding.layoutLoading.visibility = View.VISIBLE
        viewModelApp.mClickGetTopic(postsList, adapter, binding.layoutLoading)
    }

    override fun onBackPressed() {
        launchActivity(MainActivity::class.java)
        finish()
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}