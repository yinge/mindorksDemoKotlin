package com.seven.mindorks.ui.main

import android.graphics.Color
import android.widget.Button
import android.widget.TextView
import com.androidnetworking.widget.ANImageView
import com.mindorks.placeholderview.annotations.*
import com.seven.mindorks.R
import com.seven.mindorks.data.model.others.QuestionCardData

/**
 * at 2019/12/10
 * at 9:55
 * summary:
 */
@NonReusable
@Layout(R.layout.card_layout)
class QuestionCard(val questionCardData: QuestionCardData) {
    @View(R.id.btn_option_1)
    private lateinit var mOption1Button: Button

    @View(R.id.btn_option_2)
    private lateinit var mOption2Button: Button

    @View(R.id.btn_option_3)
    private lateinit var mOption3Button: Button

    @View(R.id.iv_pic)
    private lateinit var mPicImageView: ANImageView

    @View(R.id.tv_question_txt)
    private lateinit var mQuestionTextView: TextView

    @Click(R.id.btn_option_1)
    fun onOption1Click() {
        showCorrectOptions()
    }

    @Click(R.id.btn_option_2)
    fun onOption2Click() {
        showCorrectOptions()
    }

    @Click(R.id.btn_option_3)
    fun onOption3Click() {
        showCorrectOptions()
    }

    @Resolve
    fun onResolved() {
        mQuestionTextView.text = questionCardData.question.questionText
        if (questionCardData.mShowCorrectOptions) {
            showCorrectOptions()
        }
        for (i in 0..2) {
            val button = when (i) {
                0 -> mOption1Button
                1 -> mOption2Button
                2 -> mOption3Button
                else -> mOption1Button
            }
            button?.text = questionCardData.options[i].optionText
            questionCardData.question.imgUrl?.let {
                mPicImageView.setImageUrl(it)
            }
        }

    }

    private fun showCorrectOptions() {
        questionCardData.mShowCorrectOptions = true
        for (i in 0..2) {
            val option = questionCardData.options[i]
            val button = when (i) {
                0 -> mOption1Button
                1 -> mOption2Button
                2 -> mOption3Button
                else -> mOption1Button
            }
            button?.let {
                if (option.isCorrect) {
                    it.setBackgroundColor(Color.GREEN)
                } else {
                    it.setBackgroundColor(Color.RED)
                }
            }
        }
    }
}