package com.example.englanguagefinal

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.englanguagefinal.adapter.ListVocabularyAdapter
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.helper.MyButtonClickListener
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.viewmodel.VocabularyViewModel
import com.example.englanguagefinal.databinding.ActivityVocabularyBinding
import com.example.englanguagefinal.helper.MySwipeHelperJV

class VocabularyActivity : AppCompatActivity() {

    private lateinit var adapter: ListVocabularyAdapter
    private lateinit var authorization: String
    private lateinit var binding: ActivityVocabularyBinding
    private val context: Context = this@VocabularyActivity
    private var mListVocabulary: MutableList<SuccessVocabulary> = ArrayList()
    private var vocabularyViewModel: VocabularyViewModel? = null
    private val search: String = ""
    private var searchView: SearchView? = null
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefreshLayout)
    }

    //SHARED PREFERENCE
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary)
        //SHARED PREFERENCE
        sharedPref = this.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        authorization = sharedPref.getString("Authorization", "").toString()

        binding.recyclerView.apply {
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
        adapter = ListVocabularyAdapter(context)
        binding.recyclerView.adapter = adapter

//        authorization = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIzIiwianRpIjoiMTllOTAzNDY2OWIwZmQzZmMxMGE0ZjlkNTNjNGJmNDQxNjc5MDE2YTUyMzdkZDEzMTgwZjhkNmFkMDkwMGE0NTY1Mjk4NDBmYjFjYjg0ZjAiLCJpYXQiOjE2NTI3NzAxNTEuMTgyMzI0LCJuYmYiOjE2NTI3NzAxNTEuMTgyMzM2LCJleHAiOjE2ODQzMDYxNTEuMTY4NTA5LCJzdWIiOiI5Iiwic2NvcGVzIjpbXX0.G0-AbCxIIfWTmHKLlVM88UKBaReKi3oIAkY2mlY2FTgjb55DzJUOW5rKTMkh3nUg2VuVhQvSEKVlRc6ARoKrr5e58WrTXEqAoRM8pTVYk4XYXG-5nVFkRGhcMEplXr9HkhxwUBaDwCUT29nnPeuFvwVmhU2etVN2C5X07RhznWnjSlp-Y1ohbz4DwCGt8yvlSWZDYgr2we2eiPbTvtsRi4aakh0Xi1dOjxaD3ULRNlqhUmoyAgUvC2IpLbg1D9NyMjJ1DA7H_Zu9NuAtf3tyD7C2elUUJXBzxfzRC5RtlyWtMDu7Cb6vvEVUX4FKDlIF_MqjZA2kejPEQ6MNVF7hmo0eqvMwfQkIv0jXDFWoxwmJQ9zNQw9CHvfYgBqSAqhp-GlhIvDCdMmpUPfe9BRizAo3M04Ox0Y0RluaWjeB-x1JzRm_ch6o9OGK1QK_YllFhchuY_EbN3-wypoPZ7YKvsU4fitgDE3W09TP1Ic38d45s-O4JpxI5qloHqig5X6UDaRV5IwHehq6jfDEMJtszqCe9fO7ASgg0YOV9sDSHgO9RiwRc3zfAHuKeMxNSVf1FeiGStJ5MkUEEj4OmGkTjAUoZFeVCXMBY1WxA3eywm3N6ITDS-T3k7Qr9uVROOlAfRUdn9MVA-5GxNetQLwvgPuiv-8lNGFW9awWAYkrvxk"

        vocabularyViewModel = VocabularyViewModel()

        binding.imgBack.setOnClickListener {
            val intentMainActivity = Intent(this@VocabularyActivity, MainActivity::class.java)
            startActivity(intentMainActivity)
            finish()
        }
        binding.layoutLoading.visibility = View.VISIBLE
        vocabularyViewModel?.mClickGetVocabulary(binding.recyclerView, adapter, context, search, binding.layoutLoading)

        binding.addVocabulary.setOnClickListener {
//            if (!searchView!!.isIconified) {
//                searchView!!.isIconified = true
//                searchView!!.isIconified = true
//            }
            vocabularyViewModel!!.openDialogInsertVocabulary(
                Gravity.CENTER,
                authorization,
                context,
                binding.recyclerView,
                adapter,
                search,
                binding.layoutLoading
            )
        }

        //H??m vu_???t xo??
        val swipeHelper: MySwipeHelperJV =
            object : MySwipeHelperJV(this, binding.recyclerView, 200) {
                override fun instantiateMyButton(
                    viewHolder: RecyclerView.ViewHolder,
                    buffer: MutableList<MyButton>
                ) {
                    if (!searchView!!.isIconified) {
                        searchView!!.isIconified = true
                        searchView!!.isIconified = true
                    }
//                    else if (searchView!!.isIconified) {
                    buffer.add(
                        MyButton(
                            Gravity.CENTER,
                            this@VocabularyActivity,
                            "Delete",
                            50,
                            0,
                            Color.parseColor("#2e3648"), object : MyButtonClickListener {
                                override fun onClick(pos: Int) {
                                    val position: Int = viewHolder.adapterPosition
                                    vocabularyViewModel?.openDialogDeleteVocabulary(
                                        Gravity.CENTER,
                                        authorization,
                                        position,
                                        swipeRefreshLayout,
                                        context, binding.recyclerView, adapter, search
                                    )
                                }
                            }
                        )
                    )
                }
            }
        //LOAD LAI
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            vocabularyViewModel!!.mClickGetVocabulary(
                binding.recyclerView,
                adapter,
                context,
                search,
                binding.layoutLoading
            )
            adapter.reload(mListVocabulary)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView!!.maxWidth = Int.MAX_VALUE
        searchView!!.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                adapter.filter.filter(s)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter.filter.filter(s)
                return false
            }
        })
        return true
    }

    //H??M KHI B???M V??O SEARCH V?? T???T B??N PH??_M ??I
    //TH?? NH???_N NEXT S??? THO_??T N??T SEARCH, NEXT L???N N???A M???I THO_??T RA GIA_O DI_???N KH_??C
    //THA_Y V?? ??AN_G TRO_NG CH??? ????? SEARCH NH???_N NEXT N?? V??? GIA_O DI???_N KH??_C TH?? KH??_NG ????N_G
    override fun onBackPressed() {
        if (!searchView!!.isIconified) {
            searchView!!.isIconified = true
            searchView!!.isIconified = true
            return
        }
        launchActivity(MainActivity::class.java)
        finish()
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
        adapter.release()
    }
}