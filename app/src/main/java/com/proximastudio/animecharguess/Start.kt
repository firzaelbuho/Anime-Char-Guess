package com.proximastudio.animecharguess

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.activity_start.spanduk
import java.util.*
import kotlin.collections.List as List1


class Start : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd


    lateinit var countDownTimer: CountDownTimer
    lateinit var shuffledQuestions: List1<Int>
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

        MobileAds.initialize(this) {}
        val adRequest = AdRequest.Builder().build()
        spanduk.loadAd(adRequest)




        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = Database.interstitialID
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        var setOfQuestions = ArrayList<Int>()

        for(i in 0 until Database.pict.size){
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
            numberOfQuestions = Database.pict.size
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
        countDownTimer.start()

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

        credit.text = "Art by : ${Database.questionName.get(key).get(2)}"
        name.text = ""
        series.text = ""
        question.setImageResource(Database.pict.get(key))


        btnA.text = "${Database.questionName.get(shuffledOptions.get(0)).get(0)}"
        btnB.text = "${Database.questionName.get(shuffledOptions.get(1)).get(0)}"
        btnC.text = "${Database.questionName.get(shuffledOptions.get(2)).get(0)}"
        btnD.text = "${Database.questionName.get(shuffledOptions.get(3)).get(0)}"




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
        name.text = "Name : ${Database.questionName.get(key).get(0)}"
        series.text = "Series : ${Database.questionName.get(key).get(1)}"

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
        val x = (0 until Database.pict.size).random()

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
