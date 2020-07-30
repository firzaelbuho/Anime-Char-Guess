package com.proximastudio.animecharguess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class Result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        score.text = "${Database.score}"

        if(Database.score != (Database.pict.size * 10)){
            congrats.text = ""
        }

        btnBack.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
    }
}
