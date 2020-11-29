package com.proximastudio.animecharguess

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.proximastudio.animecharguess.model.Character
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.activity_start.spanduk
import com.proximastudio.animecharguess.model.Database as Database
import kotlin.collections.List


class Start : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    lateinit var characters : List<Character>


    lateinit var countDownTimer: CountDownTimer
    lateinit var shuffledQuestions: List<Int>
    lateinit var shuffledOptions : kotlin.collections.List<Int>
    var questionCounter = 0
    var myAnswer = -1
    var isWrong = false
    var key = -1
    var numberOfQuestions = 0
    var questionIndex = 0
    var scoreCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        characters = Database.characters!!

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        spanduk.loadAd(adRequest)

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = Database.interstitialID
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        setup()

    }



    private fun setup() {
        var setOfQuestions = ArrayList<Int>()

        for(i in 0 until characters.size){
            setOfQuestions.add(i)
        }
        var preShuffleQuestions = setOfQuestions.shuffled()

        if(Database.isClassic){
            // do classic mode

            numberOfQuestions = 10
            shuffledQuestions = listOf<Int>(
                preShuffleQuestions.get(0),
                preShuffleQuestions.get(1),
                preShuffleQuestions.get(2),
                preShuffleQuestions.get(3),
                preShuffleQuestions.get(4),
                preShuffleQuestions.get(5),
                preShuffleQuestions.get(6),
                preShuffleQuestions.get(7),
                preShuffleQuestions.get(8),
                preShuffleQuestions.get(9)
            )
            startQuestion(shuffledQuestions.get(0))

        }
        else{
            // do challenge mode
            numberOfQuestions = characters.size
            shuffledQuestions = preShuffleQuestions
            startQuestion(shuffledQuestions.get(0))

        }


        // click listener

        btnA.setOnClickListener {
            myAnswer = shuffledOptions.get(0)
            checkAnswer(btnA)
        }
        btnB.setOnClickListener {
            myAnswer = shuffledOptions.get(1)
            checkAnswer(btnB)
        }
        btnC.setOnClickListener {
            myAnswer = shuffledOptions.get(2)
            checkAnswer(btnC)
        }
        btnD.setOnClickListener {
            myAnswer = shuffledOptions.get(3)
            checkAnswer(btnD)
        }

        btnNext.setOnClickListener{

            Database.playSound(R.raw.klik,this)
            // cek if end of question

            timer.text = ""



            if(questionCounter == numberOfQuestions){


                // go to result

                Database.score = scoreCounter
                startActivity(Intent(this, Result::class.java))

//                // inters ads
//                if (mInterstitialAd.isLoaded) {
//                    mInterstitialAd.show()
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.")
//                }


            }
            else if(!Database.isClassic && isWrong){

                Database.score = scoreCounter
                startActivity(Intent(this, Result::class.java))

//                // show ads
//                if (mInterstitialAd.isLoaded) {
//                    mInterstitialAd.show()
//                } else {
//                    Log.d("TAG", "The interstitial wasn't loaded yet.")
//                }
            }
            else{
                // go to next question
                resetView()

                questionIndex++
                startQuestion(shuffledQuestions.get(questionCounter))
            }
        }
    }

    fun startQuestion(indexOfQuestion : Int){


        // setup countdown

        countDownTimer = object : CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
               timer.text = "${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                timer.setText("Time Out !")
                btnNext.isEnabled = true
                btnNext.setBackgroundResource(R.drawable.button_bg1)
                findAnswer()
            }
        }


        questionCounter++

        btnNext.setBackgroundResource(R.drawable.button_bg0)

        //key
        key = indexOfQuestion

        btnNext.isEnabled = false

        // generate alternative options

        val opt1 = key
        val opt2 = getRandom(arrayOf(opt1))
        val opt3 = getRandom(arrayOf(opt1, opt2))
        val opt4 = getRandom(arrayOf(opt1, opt2, opt3))

        val setOfOptions  = listOf(opt1, opt2, opt3, opt4)
        shuffledOptions = setOfOptions.shuffled()


        // set up questions
        val url ="https://firzaelbuho.github.io/hosting/anime/${characters.get(key).pict}.png"

        credit.text = "Art by : ${characters.get(key).artist}"
        name.text = ""
        series.text = ""
        Glide.with(question)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .listener(object : RequestListener<Drawable> {

                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                   countDownTimer.start()
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return true

                }
            })
            .into(question)




        btnA.text = "${characters.get(shuffledOptions.get(0)).name}"
        btnB.text = "${characters.get(shuffledOptions.get(1)).name}"
        btnC.text = "${characters.get(shuffledOptions.get(2)).name}"
        btnD.text = "${characters.get(shuffledOptions.get(3)).name}"




    }

    fun checkAnswer(btn : Button){

        btnNext.setBackgroundResource(R.drawable.button_bg1)
        countDownTimer.cancel()
        disbleButton()
        btnNext.isEnabled = true


        if(myAnswer == key){
            // true answer
            btn.setBackgroundResource(R.drawable.button_bg_true)
            timer.text = "correct !"
            scoreCounter += 10
            Database.playSound(R.raw.benar, this)
        }
        else{
            // false answer
            timer.text = "incorrect !"
            btn.setBackgroundResource(R.drawable.button_bg_false)
            Database.playSound(R.raw.salah, this)
            isWrong = true
            // find answer

           findAnswer()

            }
        }

    private fun findAnswer() {
        when(key){
            shuffledOptions.get(0) -> btnA.setBackgroundResource(R.drawable.button_bg_true)
            shuffledOptions.get(1) -> btnB.setBackgroundResource(R.drawable.button_bg_true)
            shuffledOptions.get(2) -> btnC.setBackgroundResource(R.drawable.button_bg_true)
            shuffledOptions.get(3) -> btnD.setBackgroundResource(R.drawable.button_bg_true)
    }

    // setup detail
        name.text = "Name : ${characters.get(key).name}"
        series.text = "Series : ${characters.get(key).series}"

    }

    fun disbleButton(){
        btnA.isEnabled = false
        btnB.isEnabled = false
        btnC.isEnabled = false
        btnD.isEnabled = false

    }


    //varargs random functions

    fun getRandom(excludes: Array<Int>):Int{

        var isNew = true
        val x = (0 until characters.size).random()

        for(e in excludes){
            if(e == x ){
                isNew = false
            }
        }

        if(isNew){
            return x
        }
        else{
           return getRandom(excludes)
        }

    }

    fun resetView(){
        btnNext.isEnabled = false
        btnA.setBackgroundResource(R.drawable.button_bg)
        btnB.setBackgroundResource(R.drawable.button_bg)
        btnC.setBackgroundResource(R.drawable.button_bg)
        btnD.setBackgroundResource(R.drawable.button_bg)
        btnA.isEnabled = true
        btnB.isEnabled = true
        btnC.isEnabled = true
        btnD.isEnabled = true

    }
}