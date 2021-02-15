package com.example.quizzapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionList: ArrayList<Question>? = null
    private var mSelectedOption: Int = 0
    private var mCorrectAnswers: Int = 0
    private var mUserName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mUserName = intent.getStringExtra(ConstantQuestion.USER_NAME)

        mQuestionList = ConstantQuestion.getQuestions()
        setQuestion()
        tv_option_one.setOnClickListener(this)
        tv_option_two.setOnClickListener(this)
        tv_option_three.setOnClickListener(this)
        tv_option_four.setOnClickListener(this)
        btn_submit.setOnClickListener(this)

    }

    private fun setQuestion() {
        val question: Question? = mQuestionList?.get(mCurrentPosition - 1)
        val quizSize = mQuestionList?.size


        defaultOptionView()
        if (mCurrentPosition == mQuestionList!!.size) {
            btn_submit.text = "FINISH"
        } else {
            btn_submit.text = "Go to next question".toUpperCase()
        }
        tv_question.text = question?.question
        if (question != null) {
            iv_image_question.setImageResource(question.image)
        }
        progressBar.progress = mCurrentPosition
        tv_progress_Bar.text = "$mCurrentPosition/$quizSize"
        tv_option_one.text = question?.optionOne
        tv_option_two.text = question?.optionTwo
        tv_option_three.text = question?.optionThree
        tv_option_four.text = question?.optionFour
    }

    private fun defaultOptionView() {
        val options = arrayListOf<TextView>()
        options.add(0, tv_option_one)
        options.add(1, tv_option_two)
        options.add(2, tv_option_three)
        options.add(3, tv_option_four)

        for (option in options) {
            option.setBackgroundColor(Color.parseColor("#E8E8E8"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border_gb)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            btn_submit.id -> {
                if (mSelectedOption == 0) {
                    mCurrentPosition++
                    when {
                        mCurrentPosition <= mQuestionList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this,ResultActivity::class.java)
                            intent.putExtra(ConstantQuestion.USER_NAME,mUserName)
                            intent.putExtra(ConstantQuestion.CORRECT_ANSWERS,mCorrectAnswers.toString())
                            intent.putExtra(ConstantQuestion.TOTAL_QUESTIONS,mQuestionList!!.size.toString())
                            startActivity(intent)
                        }
                    }
                } else {
                    val question = mQuestionList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOption) {
                        answerView(mSelectedOption, R.drawable.wrong_option_border_gb)
                    } else
                        mCorrectAnswers++
                    answerView(question.correctAnswer, R.drawable.correct_option_border_gb)
                    if (mCurrentPosition == mQuestionList!!.size) {
                        btn_submit.text = "FINISH"
                    } else {
                        btn_submit.text = "Go to next question".toUpperCase()
                    }
                    mSelectedOption = 0
                }

            }
            tv_option_one.id -> selectedOptionView(tv_option_one, 1)
            tv_option_two.id -> selectedOptionView(tv_option_two, 2)
            tv_option_three.id -> selectedOptionView(tv_option_three, 3)
            else -> selectedOptionView(tv_option_four, 4)
        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                tv_option_one.background = ContextCompat.getDrawable(this, drawableView)
            }
            2 -> {
                tv_option_two.background = ContextCompat.getDrawable(this, drawableView)
            }
            3 -> {
                tv_option_three.background = ContextCompat.getDrawable(this, drawableView)
            }
            4 -> {
                tv_option_four.background = ContextCompat.getDrawable(this, drawableView)
            }
        }
    }

    private fun onSubmitAnswer() {
        mCurrentPosition += 1
        setQuestion()
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionView()
        mSelectedOption = selectedOptionNumber
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border_gb)
        tv.setBackgroundColor(Color.parseColor("#FFFFFF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)

    }
}