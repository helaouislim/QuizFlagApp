package com.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        val name = intent.getStringExtra(ConstantQuestion.USER_NAME)
        val totalQuestion = intent.getStringExtra(ConstantQuestion.TOTAL_QUESTIONS)
        val score = intent.getStringExtra(ConstantQuestion.CORRECT_ANSWERS)
        tv_name.text = name
        tv_score.text = "Your Score is $score out of $totalQuestion"
        btn_finish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}