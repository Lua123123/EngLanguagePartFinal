package com.example.englanguagefinal.exoviewpager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.englanguagefinal.R
import java.util.ArrayList

class ViewPagerAdapter(
    fm: FragmentManager,
    private val context: Context,
    private val word: String
) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val listMp4 = ArrayList<String>()
    private val listText = ArrayList<String>()
    private val listComment = ArrayList<String>()
    private val listVoca = ArrayList<String>()
    override fun getItem(position: Int): Fragment {
        if (word == "Contracts") {
            listMp4.add(context.getString(R.string.abide_by_mp4))
            listMp4.add(context.getString(R.string.agreement_mp4))
            listMp4.add(context.getString(R.string.assurance_mp4))
            listMp4.add(context.getString(R.string.cancellation_mp4))
            listMp4.add(context.getString(R.string.determine_mp4))
            listMp4.add(context.getString(R.string.engage_mp4))
            listMp4.add(context.getString(R.string.establish_mp4))
            listMp4.add(context.getString(R.string.obligate_mp4))
            listMp4.add(context.getString(R.string.party_mp4))
            listMp4.add(context.getString(R.string.provision_mp4))
            listMp4.add(context.getString(R.string.resolve_mp4))
            listMp4.add(context.getString(R.string.specific_mp4))
            listText.add(context.getString(R.string.abide_by_text))
            listText.add(context.getString(R.string.agreement_text))
            listText.add(context.getString(R.string.assurance_text))
            listText.add(context.getString(R.string.cancellation_text))
            listText.add(context.getString(R.string.determine_text))
            listText.add(context.getString(R.string.engage_text))
            listText.add(context.getString(R.string.establish_text))
            listText.add(context.getString(R.string.obligate_text))
            listText.add(context.getString(R.string.party_text))
            listText.add(context.getString(R.string.provision_text))
            listText.add(context.getString(R.string.resolve_text))
            listText.add(context.getString(R.string.specific_text))
            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")
            listVoca.add("#abideby")
            listVoca.add("#agreement")
            listVoca.add("#assurance")
            listVoca.add("#cancellation")
            listVoca.add("#determine")
            listVoca.add("#engage")
            listVoca.add("#establish")
            listVoca.add("#obligate")
            listVoca.add("#party")
            listVoca.add("#provision")
            listVoca.add("#resolve")
            listVoca.add("#specific")
        }
        if (word == "Marketing") {
            listMp4.add(context.getString(R.string.attract_mp4))
            listMp4.add(context.getString(R.string.compare_mp4))
            listMp4.add(context.getString(R.string.competition_mp4))
            listMp4.add(context.getString(R.string.consume_mp4))
            listMp4.add(context.getString(R.string.convince_mp4))
            listMp4.add(context.getString(R.string.currently_mp4))
            listMp4.add(context.getString(R.string.fad_mp4))
            listMp4.add(context.getString(R.string.inspiration_mp4))
            listMp4.add(context.getString(R.string.market_mp4))
            listMp4.add(context.getString(R.string.persuasion_mp4))
            listMp4.add(context.getString(R.string.productive_mp4))
            listMp4.add(context.getString(R.string.satisfaction_mp4))
            listText.add(context.getString(R.string.attract_text))
            listText.add(context.getString(R.string.compare_text))
            listText.add(context.getString(R.string.competition_text))
            listText.add(context.getString(R.string.consume_text))
            listText.add(context.getString(R.string.convince_text))
            listText.add(context.getString(R.string.currently_text))
            listText.add(context.getString(R.string.fad_text))
            listText.add(context.getString(R.string.inspiration_text))
            listText.add(context.getString(R.string.market_text))
            listText.add(context.getString(R.string.persuasion_text))
            listText.add(context.getString(R.string.productive_text))
            listText.add(context.getString(R.string.satisfaction_text))


            listComment.add("")

            listVoca.add("#attract")
            listVoca.add("#compare")
            listVoca.add("#competition")
            listVoca.add("#consume")
            listVoca.add("#convince")
            listVoca.add("#currently")
            listVoca.add("#fad")
            listVoca.add("#inspiration")
            listVoca.add("#market")
            listVoca.add("#persuasion")
            listVoca.add("#productive")
            listVoca.add("#satisfaction")
        }
        if (word == "Warranties") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("")

            listVoca.add("#characteristic")
            listVoca.add("#consequence")
            listVoca.add("#consider")
            listVoca.add("#cover")
            listVoca.add("#expiration")
            listVoca.add("#frequently")
            listVoca.add("#imply")
            listVoca.add("#promise")
            listVoca.add("#protect")
            listVoca.add("#reputation")
            listVoca.add("#require")
            listVoca.add("#variety")
        }

        if (word == "Business planning") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Conferences") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Computers and the internet") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Office technology") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Office procedures") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Electronics") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Correspondence") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Job ads and recruitment") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("#")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }

        if (word == "Apply and interviewing") {
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listMp4.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")
            listText.add("")


            listComment.add("obey, follow, comply")
            listComment.add("acceptance, concordance, assent")
            listComment.add("guarantee, commitment, oath, vow")

            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
            listVoca.add("#")
        }
        val child = ChildFragment()
        val bundle = Bundle()
        bundle.putString("mp4", listMp4[position])
        bundle.putString("text", listText[position])
        bundle.putString("comment", listComment[position])
        bundle.putString("voca", listVoca[position])
        child.arguments = bundle
        return child
    }

    override fun getCount(): Int {
        return 12
    }
}