package com.example.foodorder

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    val TAG = "MYOWNTAG"
    lateinit var time_tv:TextView
    lateinit var start_bt:Button
    lateinit var reset_tv:TextView
    lateinit var title_tv:TextView
    lateinit var progress:ProgressBar
    var timer:CountDownTimer? = null

    val StartInMili :Long= 25 * 60 * 1000
    var RemainingTime :Long= StartInMili

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        InitViews()

        start_bt.setOnClickListener{
            StartTimer()
            start_bt.isEnabled = false

        }

        reset_tv.setOnClickListener {
            StopTimer()
            start_bt.isEnabled = true
        }
    }








    private fun StartTimer() {
        timer = object : CountDownTimer(StartInMili, 10) {

            override fun onTick(left: Long) {
                RemainingTime = left
                UpdateTimerText()
                title_tv.setText("Keep Going..")
                progress.progress = RemainingTime.toDouble().div(StartInMili.toDouble()).times(100).toInt()
            }

            override fun onFinish() {

            }
        }.start()

    }


    private fun InitViews(){
        time_tv = findViewById(R.id.time_tv)
        start_bt = findViewById(R.id.start_bt)
        reset_tv = findViewById(R.id.reset_bt)
        title_tv = findViewById(R.id.titel_tv)
        progress = findViewById(R.id.progressBar)
    }

    private fun UpdateTimerText(){
        val minut = (RemainingTime / 1000) /60
        val sec = (RemainingTime / 1000) % 60
        val time = String.format("%02d : %02d" , minut , sec )
        time_tv.setText(time)

    }

    private fun StopTimer(){
        timer?.cancel()
        title_tv.setText("Take a Promodoro")
        time_tv.setText("25:00")
        progress.progress = 100
    }

}