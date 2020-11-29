package com.proximastudio.animecharguess.model

import android.content.Context
import android.media.MediaPlayer

object Database {



        lateinit var bgm : MediaPlayer

        val interstitialID = "ca-app-pub-8201627220488781/1044501742"
        var isClassic = true

        // must be resetted every begin of quiz

        var score = 0



        fun playSound(source: Int, context: Context) {
            val mediaPlayer = MediaPlayer.create(context, source)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.stop();
                mediaPlayer.release();
            }


        }

    var characters : List<Character>? = null





}
