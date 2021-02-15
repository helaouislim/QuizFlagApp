package com.example.quizzapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        btn_start.setOnClickListener {
            if (et_name.text.toString().isEmpty())
                Toast.makeText(this, "Enter your name ", Toast.LENGTH_LONG).show()
            else{
               val intent = Intent(this, QuizQuestionActivity::class.java)

                intent.putExtra(ConstantQuestion.USER_NAME,et_name.text.toString())
                startActivity(intent)
              //  finish()
            }
        }
    }
}