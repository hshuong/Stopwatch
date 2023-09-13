package com.hfad.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    lateinit var stopwatch: Chronometer // the stopwatch
    private var running = false // Is the stopwatch running?
    private var offset: Long = 0 // The offset from base of stopwatch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Chi co the khoi tao bien stopwatch sau khi goi ham setContentView
        // Truoc diem nay doi tuong view Chronometer stopwatch chua ton tai
        stopwatch = findViewById<Chronometer>(R.id.stopwatch)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if (!running) {
                setBaseTime()
                stopwatch.start()
                running = true
            }
        }

        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            saveOffset()
            stopwatch.stop()
            running = false
        }

        val resetButton =  findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    private fun setBaseTime() {
        // base la thoi diem bo dem chronometer bat dau chay
        // dat thoi diem bat dau chay cua bo dem chronometer
        // la thoi gian hien tai, tru di thoi gian do lech. Do lech offset la
        // do bam nut pause. Ban dau chua lech thi start bang thoi diem hien
        // tai cua dong ho luon.
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    private fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }

}