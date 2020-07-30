package com.proximastudio.animecharguess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //show ads

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        spanduk.loadAd(adRequest)

        btnStart.setOnClickListener {
            startActivity(Intent(this, Mode::class.java))
        }
        btnAbout.setOnClickListener {
            startActivity(Intent(this, About::class.java))
        }

    }
}
