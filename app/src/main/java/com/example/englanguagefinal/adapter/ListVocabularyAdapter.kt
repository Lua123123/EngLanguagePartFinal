package com.example.englanguagefinal.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.OneVocabularyActivity
import com.example.englanguagefinal.R
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import java.util.*

class ListVocabularyAdapter(private var context: Context?) : RecyclerView.Adapter<ListVocabularyAdapter.ViewHolder>(), Filterable {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private lateinit var mTTS: TextToSpeech

    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf()) {
        this.mListVocabulary = mListVocabulary
        mListVocabularyOld = mListVocabulary
        notifyDataSetChanged()
    }

    fun reload(mListVocabulary: List<SuccessVocabulary>?) {
        this.mListVocabulary.clear()
        this.mListVocabulary.addAll(mListVocabulary!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        callMTTS()
        if (true) {
            holder.tvWord.text = mListVocabulary[position].word
            holder.tvMean.text = mListVocabulary[position].mean
            holder.tvExample.text = mListVocabulary[position].example
            val word = mListVocabulary[position].word
            val mean = mListVocabulary[position].mean

            holder.img_detail.setOnClickListener {
                if (holder.tvWord.text == "Determine") {
                    val speech = "/di't??:min/"
                    val translate = context!!.getString(R.string.determine)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Establish") {
                    val speech = "/is't??bli??/"
                    val translate = context!!.getString(R.string.establish)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Engage") {
                    val speech = "/in'geid??/"
                    val translate = context!!.getString(R.string.engage)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Abide by") {
                    val speech = "/??'baid/"
                    val translate = context!!.getString(R.string.abideby)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Assurance") {
                    val speech = "/??'??u??r??ns/"
                    val translate = context!!.getString(R.string.assurance)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Cancellation") {
                    val speech = "/,k??nse'lei??n/"
                    val translate = context!!.getString(R.string.cancellation)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Agreement") {
                    val speech = "/??'gri:m??nt/"
                    val translate = context!!.getString(R.string.agreement)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Satisfaction") {
                    val speech = "/,s??tis'f??k??n/"
                    val translate = context!!.getString(R.string.satisfaction)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Productive") {
                    val speech = "/pr????d??kt??v/"
                    val translate = context!!.getString(R.string.productive)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Persuasion") {
                    val speech = "/p??'swei??n/"
                    val translate: String = context!!.getString(R.string.persuasion)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Market") {
                    val speech = "/'m??:kit/"
                    val translate: String = context!!.getString(R.string.market)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Inspiration") {
                    val speech = "/,insp??'rei??n/"
                    val translate: String = context!!.getString(R.string.inspiration)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Fad") {
                    val speech = "/f??d/"
                    val translate = context!!.getString(R.string.fad)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Currently") {
                    val speech = "/??k??r??ntli/"
                    val translate = context!!.getString(R.string.currently)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Convince") {
                    val speech = "/k??n'vins/"
                    val translate: String = context!!.getString(R.string.convince)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Consume") {
                    val speech = "/k??n'sju:m/"
                    val translate = context!!.getString(R.string.consume)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Competition") {
                    val speech = "/,k??mpi'ti??n/"
                    val translate = context!!.getString(R.string.competition)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Compare") {
                    val speech = "/k??m'pe??/"
                    val translate = context!!.getString(R.string.compare)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Attract") {
                    val speech = "/??'tr??kt/"
                    val translate = context!!.getString(R.string.attract)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Resolve") {
                    val speech = "/ri'z??lv/"
                    val translate = context!!.getString(R.string.resolve)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Specific") {
                    val speech = "/spi'sifik/"
                    val translate = context!!.getString(R.string.specific)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Provision") {
                    val speech = "/provision/"
                    val translate = context!!.getString(R.string.provision)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Characteristic") {
                    val speech = "/,k??rikt??'ristik/"
                    val translate = context?.getString(R.string.characteristic)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Consequence") {
                    val speech = "/'k??nsikw??ns/"
                    val translate = context?.getString(R.string.consequence)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Consider") {
                    val speech = "/k??n'sid??/"
                    val translate = context?.getString(R.string.consider)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Cover") {
                    val speech = "/'k??v??/"
                    val translate = context?.getString(R.string.cover)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Expiration") {
                    val speech = "/,ekspai??'rei??n/"
                    val translate = context?.getString(R.string.expiration)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Frequently") {
                    val speech = "/??fri??kw??ntli/"
                    val translate = context?.getString(R.string.frequently)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Imply") {
                    val speech = "/im'plai/"
                    val translate = context?.getString(R.string.imply)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Protect") {
                    val speech = "/protect/"
                    val translate = context?.getString(R.string.protect)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Promise") {
                    val speech = "/promise/"
                    val translate = "Ng?????i c???ng t??c kinh doanh h???a r???ng t???m n???m m???i c???a ch??ng ta s??? ?????n tr?????c tr??a th??? B???y"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Reputation") {
                    val speech = "/,repju:'tei??n/"
                    val translate = "C??ng ty bi???t r???ng ti???ng t??m s???n ph???m c???a h??? l?? th??? t??i s???n quan tr???ng nh???t m?? h??? c??"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Require") {
                    val speech = "/ri'kwai??/"
                    val translate = "Lu???t ph??p y??u c???u m???i m??n h??ng tr??nh b??y r?? r??ng th??ng tin v??? b???o h??nh"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Variety") {
                    val speech = "/v??'rai??ti/"
                    val translate = context?.getString(R.string.variety)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Address") {
                    val speech = "/??'dres/"
                    val translate = "K??? ho???ch kinh doanh c???a Marco nh???m v??o nhu c???u c???a nh???ng ch??? doanh nghi???p nh???"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Avoid") {
                    val speech = "/??'v??id/"
                    val translate = "Nh???m tr??nh l???n b???i vi???c l??m ??n, nh???ng ng?????i ch??? n??n chu???n b??? m???t k??? ho???ch KD ph?? h???p"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Demonstrate") {
                    val speech = "/'dem??nstreit/"
                    val translate = "V??? gi??o s?? ???? ch???ng minh th??ng qua b??i h???c t??nh hu???ng l?? m???t k??? ho???ch kinh doanh c?? th??? g??y ???n t?????ng v???i m???t ng?????i cho vay"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Develop") {
                    val speech = "/di'vel??p/"
                    val translate = "Lily ???? ph??t tri???n ?? t?????ng c???a c?? ta v??o k??? ho???ch kinh doanh b???ng c??ch tham d??? m???t l???p h???c t???i tr?????ng cao ?????ng c???ng ?????ng"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Evaluate") {
                    val speech = "/i'v??ljueit/"
                    val translate = "????nh gi?? s???c c???nh tranh c???a b???n l?? vi???c quan tr???ng khi l???p m???t k??? ho???ch kinh doanh"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Gather") {
                    val speech = "/'g??????/"
                    val translate = "Ch??ng t??i thu th???p th??ng tin cho b???n k??? ho???ch c???a m??nh t??? nhi???u ngu???n"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Substitution") {
                    val speech = "/,s??bsti'tju:??n/"
                    val translate = "Vi???c anh l???y t??n gi??? thay cho t??n th???t khi???n cho t??i li???u c?? v??? nh?? kh??ng th??nh th???t"
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate)
                }
                if (holder.tvWord.text == "Offer") {
                    val speech = "/'??f??/"
                    val translate = context?.getString(R.string.offer)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Primarily") {
                    val speech = "/'praim??rili/"
                    val translate = context?.getString(R.string.primarily)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Risk") {
                    val speech = "/r??sk/"
                    val translate = context?.getString(R.string.risk)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Strategy") {
                    val speech = "/??str??t??d??i/"
                    val translate = context?.getString(R.string.strategy)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Strong") {
                    val speech = "/str????/"
                    val translate = context?.getString(R.string.strong)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Accommodate") {
                    val speech = "/??'k??m??deit/"
                    val translate = context?.getString(R.string.accommodate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Arrangement") {
                    val speech = "/??'reind??m??nt/"
                    val translate = context?.getString(R.string.arrangement)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Association") {
                    val speech = "/??,sousi'ei??n/"
                    val translate = context?.getString(R.string.association)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Attend") {
                    val speech = "/??'tend/"
                    val translate = context?.getString(R.string.attend)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "get in touch") {
                    val speech = "N/A"
                    val translate = context?.getString(R.string.getintouch)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "hold") {
                    val speech = "/hould/"
                    val translate = context?.getString(R.string.hold)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "take part in") {
                    val speech = "N/A"
                    val translate = context?.getString(R.string.takepartin)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Location") {
                    val speech = "/lou'kei??n/"
                    val translate = context?.getString(R.string.location)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Overcrowded") {
                    val speech = "/????v????kra??d??d/"
                    val translate = context?.getString(R.string.overcrowded)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Register") {
                    val speech = "/'red??ist??/"
                    val translate = context?.getString(R.string.register)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Select") {
                    val speech = "/si'lekt/"
                    val translate = context?.getString(R.string.select)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Session") {
                    val speech = "/'se??n/"
                    val translate = context?.getString(R.string.session)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "access") {
                    val speech = "/????kses/"
                    val translate = context?.getString(R.string.access)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "allocate") {
                    val speech = "/????l??ke??t/"
                    val translate = context?.getString(R.string.allocate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Compatible") {
                    val speech = "/k??m'p??t??bl/"
                    val translate = context?.getString(R.string.compatible)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Delete") {
                    val speech = "/di'li:t/"
                    val translate = context?.getString(R.string.delete)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Display") {
                    val speech = "/dis'plei/"
                    val translate = context?.getString(R.string.display)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Duplicate") {
                    val speech = "/'dju:plikit/"
                    val translate = context?.getString(R.string.duplicate)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Failure") {
                    val speech = "/'feilj??/"
                    val translate = context?.getString(R.string.failure)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Figure out") {
                    val speech = "/??f??????/ /a??t/"
                    val translate = context?.getString(R.string.figureout)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Ignore") {
                    val speech = "/ig'n??:/"
                    val translate = context?.getString(R.string.ignore)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Search") {
                    val speech = "/s??:t??/"
                    val translate = context?.getString(R.string.search)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Shut down") {
                    val speech = "/????t/ /da??n/"
                    val translate = context?.getString(R.string.shutdown)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
                if (holder.tvWord.text == "Warning") {
                    val speech = "/'w??:ni??/"
                    val translate = context?.getString(R.string.warning)
                    openDialogShowVocabulary(Gravity.CENTER, position, speech, translate!!)
                }
            }

            holder.layout_item.setOnClickListener {
                val intent = Intent(context, OneVocabularyActivity::class.java)
                intent.putExtra("word", word)
                intent.putExtra("mean", mean)
                context!!.startActivity(intent)
            }

        }
    }

    override fun getItemCount(): Int {
        return if (mListVocabulary != null) {
            mListVocabulary.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val tvExample: TextView
        val img_detail: ImageView
        val layout_item: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            tvExample = itemView.findViewById(R.id.example)
            img_detail = itemView.findViewById(R.id.img_detail)
            layout_item = itemView.findViewById(R.id.layout_item)
        }
    }

    fun release() {
        context = null
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val strSearch = charSequence.toString()
                mListVocabulary = if (strSearch.isEmpty()) {
                    mListVocabularyOld
                } else {
                    val list: MutableList<SuccessVocabulary> = ArrayList()
                    for (successVocabulary in mListVocabularyOld!!) {
                        if (successVocabulary.word.lowercase()
                                .contains(strSearch.lowercase())
                        ) {
                            list.add(successVocabulary)
                        }
                    }
                    list
                }
                val filterResults = FilterResults()
                filterResults.values = mListVocabulary
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mListVocabulary = (filterResults.values as List<SuccessVocabulary>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }

    private fun openDialogShowVocabulary(gravity: Int, position: Int, speech: String, translate: String) {
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_view_xemchitiet)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val wordDialog = dialog.findViewById<TextView>(R.id.wordDialog)
        val speechDialog = dialog.findViewById<TextView>(R.id.speechDialog)
        val meanDialog = dialog.findViewById<TextView>(R.id.meanDialog)
        val exampleDialog = dialog.findViewById<TextView>(R.id.exampleDialog)
        val translateDialog = dialog.findViewById<TextView>(R.id.translateDialog)
        val imgVolumeDialog = dialog.findViewById<ImageView>(R.id.imgVolumeDialog)
        val btnConFirmDialog: Button = dialog.findViewById(R.id.btnConFirmDialog)
        val word: String = mListVocabulary[position].word
        val mean: String = mListVocabulary[position].mean
        val example: String = mListVocabulary[position].example
        wordDialog.text = word
        speechDialog.text = speech
        meanDialog.text = mean
        exampleDialog.text = example
        translateDialog.text = translate

        btnConFirmDialog.setOnClickListener {
            dialog.dismiss()
        }

        imgVolumeDialog.setOnClickListener {
            speak(word)
        }

        dialog.show()
    }

    fun callMTTS() {
        mTTS = TextToSpeech(context, object : TextToSpeech.OnInitListener {
            override fun onInit(i: Int) {
                if (i == TextToSpeech.SUCCESS) {
                    val result = mTTS.setLanguage(Locale.ENGLISH)
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported")
                    }
                    run { Log.e("TTS", "Initialization failed") }
                }
            }
        })
    }

    fun speak(word: String) {
        val text: String = word
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}