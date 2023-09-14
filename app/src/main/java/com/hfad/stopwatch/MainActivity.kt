package com.hfad.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {
    private lateinit var stopwatch: Chronometer // the stopwatch. Chua khoi tao
    // (gan gia tri cho bien) View chronometer stopwatch o day duoc
    // vi stopwatch chua ton tai vao thoi diem nay => phai dung lateinit
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
                // lay thoi diem SystemClock.elapsedRealtime()
                // la thoi gian tinh tu luc boot dien thoai den luc bam nut start nay
                // chinh la thoi diem bam start, vao thuoc tinh base
                // stopwatch.base = SystemClock.elapsedRealtime() - offset

                // Neu co bam pause truoc khi bam start nay, thi offset co gia tri duong,
                // vi du, offset la 14 phut 05 giay
                // thi gia tri base (thoi diem bat dau dem, thoi diem start)
                // duoc tinh lai la thoi diem hien tai, tru di phan chenh lech offset
                // vi du thoi diem hien tai la 16 phut 10 giay

                setBaseTime()

                // sau khi dat thoi diem bat dau la base, moi chay chronometer tinh
                // tu thoi diem base
                stopwatch.start()
                // dat running la true de luu trang thai nut start da bam, dong ho dang chay roi
                // neu lo bam vao start tiep thi ko vao day duoc, dong ho van dang chay, ko tinh
                // lai thoi gian base
                running = true
            }
        }

        val pauseButton = findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            // khi bam pause, lay thoi diem hien tai, tru di thoi diem start (thoi diem base)
            // de tinh ra khoang thoi gian tu thoi diem base den thoi diem hien tai bam pause
            // luu vao bien offset
            // offset = SystemClock.elapsedRealtime() - stopwatch.base
            saveOffset() // offset da luu duoc thoi gian  da troi qua tu khi
            // bam start den luc bam pause nay
            stopwatch.stop()
            // doi running thanh false de cho bam duoc nut start
            running = false
        }

        val resetButton =  findViewById<Button>(R.id.reset_button)
        resetButton.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    private fun setBaseTime() {
        // base la thoi diem bam nut start, bo dem chronometer bat dau chay
        // dat thoi diem bat dau chay cua bo dem chronometer
        // la thoi gian hien tai, tru di thoi gian do lech. Do lech offset duoc tao ra
        // do bam nut pause. Ban dau chua lech thi start bang thoi diem hien
        // tai cua dong ho luon.
        stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    private fun saveOffset() {
        // dang chay, bam pause thi save lai thoi gian da troi qua, tinh tu
        // thoi diem base cho den luc bam pause
        // lay thoi diem hien tai luc bam pause, tru di thoi diem bat dau bam start
        // duoc thoi gian so giay da troi qua tu bo dem chronometer start
        offset = SystemClock.elapsedRealtime() - stopwatch.base
    }

}