package com.proximastudio.animecharguess

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.proximastudio.animecharguess.model.Database
import kotlinx.android.synthetic.main.activity_mode.*
import kotlinx.android.synthetic.main.activity_mode.spanduk

class Mode : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mode)

        //show ads

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        spanduk.loadAd(adRequest)


        btnClassic.setOnClickListener {
            Database.isClassic = true
            startActivity(Intent(this, Start::class.java))

        }

        btnChal.setOnClickListener {
            Database.isClassic = false
            startActivity(Intent(this, Start::class.java))

        }

    }
}
