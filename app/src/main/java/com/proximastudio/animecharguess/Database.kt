package com.proximastudio.animecharguess

import android.content.Context
import android.media.MediaPlayer

class Database() {

    companion object{

        lateinit var bgm : MediaPlayer

        val interstitialID = "ca-app-pub-8201627220488781/1044501742"
        var isClassic = true

        // must be resetted every begin of quiz

        var score = 0


        val pict = arrayOf(
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p12,
            R.drawable.p13,
            R.drawable.p14,
            R.drawable.p15,
            R.drawable.p16,
            R.drawable.p17,
            R.drawable.p18,
            R.drawable.p19,
            R.drawable.p20,
            R.drawable.p21,
            R.drawable.p22,
            R.drawable.p23,
            R.drawable.p24,
            R.drawable.p25,
            R.drawable.p26,
            R.drawable.p27,
            R.drawable.p28,
            R.drawable.p29,
            R.drawable.p30,
            R.drawable.p31,
            R.drawable.p32,
            R.drawable.p33,
            R.drawable.p34,
            R.drawable.p35,
            R.drawable.p36,
            R.drawable.p37,
            R.drawable.p38,
            R.drawable.p39,
            R.drawable.p40,
            R.drawable.p41,
            R.drawable.p42,
            R.drawable.p43,
            R.drawable.p44,
            R.drawable.p45,
            R.drawable.p46,
            R.drawable.p47,
            R.drawable.p48,
            R.drawable.p49,
            R.drawable.p50,
            R.drawable.p51,
            R.drawable.p52,
            R.drawable.p53,
            R.drawable.p54,
            R.drawable.p55,
            R.drawable.p56,
            R.drawable.p57,
            R.drawable.p58,
            R.drawable.p59,
            R.drawable.p60,
            R.drawable.p61,
            R.drawable.p62,
            R.drawable.p63,
            R.drawable.p64,
            R.drawable.p65,
            R.drawable.p66,
            R.drawable.p67,
            R.drawable.p68,
            R.drawable.p69,
            R.drawable.p70,
            R.drawable.p71,
            R.drawable.p72,
            R.drawable.p73,
            R.drawable.p74,
            R.drawable.p75,
            R.drawable.p76,
            R.drawable.p77,
            R.drawable.p78,
            R.drawable.p79,
            R.drawable.p80,
            R.drawable.p81,
            R.drawable.p82,
            R.drawable.p83,
            R.drawable.p84,
            R.drawable.p85,
            R.drawable.p86,
            R.drawable.p87,
            R.drawable.p88,
            R.drawable.p89,
            R.drawable.p90,
            R.drawable.p91,
            R.drawable.p92,
            R.drawable.p93,
            R.drawable.p94,
            R.drawable.p95,
            R.drawable.p96,
            R.drawable.p97,
            R.drawable.p98,
            R.drawable.p99,
            R.drawable.p100,
            R.drawable.p101,
            R.drawable.p102,
            R.drawable.p103,
            R.drawable.p104,
            R.drawable.p105,
            R.drawable.p106,
            R.drawable.p107,
            R.drawable.p108,
            R.drawable.p109,
            R.drawable.p110,
            R.drawable.p111,
            R.drawable.p112,
            R.drawable.p113,
            R.drawable.p114,
            R.drawable.p115,
            R.drawable.p116,
            R.drawable.p117,
            R.drawable.p118,
            R.drawable.p119,
            R.drawable.p120
        )
        var questionName = arrayOf(
            arrayOf("Miyazono Kaori", "Yoiur Lie in April", "greenmapple17"),
            arrayOf("Arima Kousei", "Your Lie in April", "NN"),
            arrayOf("Hishiro Chizuru", "ReLife", "MaltyCrab"),
            arrayOf("Kaizaki Arata", "ReLife", "AlfredoRM"),
            arrayOf("Light Yagami", "Death Note", "Reverendtundra"),
            arrayOf("L", "Death Note", "lucidixp"),
            arrayOf("Okabe Rintarou", "Steins Gate", "SykotixUK"),
            arrayOf("Makise Kurisu", "Steins Gate", "chrisfch"),
            arrayOf("Edward Elric", "Fullmetal Alchemist", "ExLu"),
            arrayOf("Alphonse Elric", "Fullmetal Alchemist", "tonyp2121"),
            arrayOf("Roy Mustang", "Fullmetal Alchemist", "Hailstone294"),
            arrayOf("King Bradley", "Fullmetal Alchemist", "greenmaple17"),
            arrayOf("Levi Ackerman", "Attsck on Titan", "slezzy7"),
            arrayOf("Mikasa Ackerman", "Attsck on Titan", "NN"),
            arrayOf("Armin Arlelt", "Attsck on Titan", "greenmaple17"),
            arrayOf("Shimura Shinpachi", "Gintama", "genmapple17"),
            arrayOf("Sakata Gintoki", "Gintama", "goodfon"),
            arrayOf("Kagura", "Gintama", "greenmapple17"),
            arrayOf("Kirisaki Chitoge", "Nisekoi", "NN"),
            arrayOf("Kosaki Onodera", "Nisekoi", "greenmapple17"),
            arrayOf("Meliodas", "Seven Deadly Sins", "BellaAderton"),
            arrayOf("Elizabeth", "Seven Deadly Sins", ""),
            arrayOf("Meiko Honma", "Anohana", "greenmapple17"),
            arrayOf("Erwin Smith", "Attack on Titan", "greenmapple17"),
            arrayOf("Eren Yeager", "Attack on Titan", "NN"),
            arrayOf("Todoroki Shoto", "My Hero Academia", "uhdpaper"),
            arrayOf("Allmight", "My Hero Academia", "uhdpixel"),
            arrayOf("Midoriya Izuku", "My Hero Academia", "Chrisfch"),
            arrayOf("Katsuki Bakugo", "My Hero Academia", "NN"),
            arrayOf("Uchiha Madara", "Naruto", "NN"),
            arrayOf("Uzumaki Naruto", "Naruto", "wallpaperflre"),
            arrayOf("Hatake Kakashi", "Naruto", "ncoll36"),
            arrayOf("Hyuuga Neji", "Naruto", "FikriMochizou"),
            arrayOf("Nara Shikamaru", "Naruto", "NN"),
            arrayOf("Namikaze Minato", "Naruto", "dSolitude"),
            arrayOf("Killer Bee", "Naruto", "wallpaperflare"),
            arrayOf("Haruno Sakura", "Naruto", "FikriMochizou"),
            arrayOf("Uchiha Sasuke", "Naruto", "greenmapple17"),
            arrayOf("Hyuuga Hinata", "Naruto", "NN"),
            arrayOf("Gaara", "Naruto", "douglaaz"),
            arrayOf("Sato Kazuma", "Konosuba", "NN"),
            arrayOf("Aqua", "Konosuba", "wallhere"),
            arrayOf("Darkness", "Konosuba", "greenmapple17"),
            arrayOf("Uchiha Itachi", "Naruto", "slezzy7"),
            arrayOf("Kusakabe Shinra", "Fire Force", "Zunnno"),
            arrayOf("Trunks", "Dragon Ball", "NN"),
            arrayOf("Portgas D Ace", "One Piece", "MrRobotboy"),
            arrayOf("Seijuro Akashi", "Kuroko no Basket", "Klikster"),
            arrayOf("Asta", "Black Clover", "NN"),
            arrayOf("Ban", "Seven Deadly Sins", "wallpapersafari"),
            arrayOf("Boa Hancook", "One Piece", "Aho1225"),
            arrayOf("Uzumaki Boruto", "Naruto", "NN"),
            arrayOf("Chihiro Ogino", "Spirited Away", "NifgtInDarkness29"),
            arrayOf("Envy", "Fullmetal Alchemist", "greenmapple17"),
            arrayOf("Erza Scarlet", "Fairy Tail", "NN"),
            arrayOf("Chitanda Eru", "Hyouka", "sybercyber"),
            arrayOf("Gon Freecss", "Hunter X Hunter", "greenmapple17"),
            arrayOf("Shinichi Kudo", "Detective Conan", "siawsharingan"),
            arrayOf("Ishigami Senku", "Dr. Stone", "Ryuzaky-kun"),
            arrayOf("Saitama", "One Punch Man", "NN"),
            arrayOf("Ryuk", "Death Note", "wallpaperflare"),
            arrayOf("Saiki Kusuo", "Saiki Kusuo no Psi Nan", "agenFneptunus"),
            arrayOf("Senju Tsunade", "Naruto", "raketa3"),
            arrayOf("Vegeta", "Dragon Ball", "wallpaperaccess"),
            arrayOf("Yui Yuigahama", "Oregairu", "greenmapple17"),
            arrayOf("Violet Evergarden", "Violet Evergarden", "wallpaperflame"),
            arrayOf("Yuu Otosaka", "Charlotte", "NN"),
            arrayOf("Kotaro Katsura", "Gintama", "Awesomeness333"),
            arrayOf("Yuuki Asuna", "Sword Art Online", "matsumayu"),
            arrayOf("Tachibana Kanade", "Angel Beats", "NN"),
            arrayOf("Kirigaya Kazuto", "Sword Art Online", "alphacoders"),
            arrayOf("Shinichi Izumi", "Parasyte", "Martianz-Art"),
            arrayOf("Shiro", "No Game No Life", "wallhere"),
            arrayOf("Kirishima Touka", "Tokyo Ghoul", "Darkfate17"),
            arrayOf("Ken Kaneki", "Tokyo Ghoul", "NN"),
            arrayOf("Kamado Tanjirou", "Demon Slayer", "wallpapercave"),
            arrayOf("Kamado Nezuko", "Demon Slayer", "uhdpaper"),
            arrayOf("Mash Kyrelight", "Fate", "SteveCharge"),
            arrayOf("Arima Kishou", "Tokyo Ghoul", "JUEGZI"),
            arrayOf("Korosensei", "Assasination Classroom", "Nur Alif SiDoel"),
            arrayOf("Isla", "Plastic Memories", "Lucifer012"),
            arrayOf("Ikoma", "Kabaneri of The Iron Fortress", "Lucifer012"),
            arrayOf("Van Hohenheim", "Fullmetal Alchemist", "Abesario25"),
            arrayOf("Handa Seishu", "Barakamon", "NN"),
            arrayOf("Son Goku", "Dragon Ball", "UzumakiAsh"),
            arrayOf("Gluttony", "Fullmetal Alchemist", "greenmapple17"),
            arrayOf("Genos", "One Punch Man", "Abesario25"),
            arrayOf("Shoyo Hinata", "Haikyuu", "?"),
            arrayOf("Killua Zoldyck", "Hunter X Hunter", "greenmapple17"),
            arrayOf("Lelouch", "Code Geass", "Lucifer012"),
            arrayOf("Lucy Heartfilia", "Fairy Tail", "greenmapple17"),
            arrayOf("Monkey D Luffy", "One Piece", "Lucifer012"),
            arrayOf("Lust", "Fullmetal Alchemist", "NN"),
            arrayOf("Miyamizu Mitsuha", "Your Name", "natsuaisyah"),
            arrayOf("Natsu Dragneel", "Fairy Tail", "THEsenpaiRYAN"),
            arrayOf("Ozora Tsubasa", "Captain Tsubasa", "akubelumganteng"),
            arrayOf("Ran Mouri", "Detective Conan", "hanasaki-ran"),
            arrayOf("Rem", "Re:Zero", "NN"),
            arrayOf("Escanor", "Seven Deadly Sins", "alphacoders"),
            arrayOf("Togata Mirio", "My Hero Academia", "scoolharis"),
            arrayOf("Asada Sinon", "Sword Art Online", "greenmapple17"),
            arrayOf("Reiner Braun", "Attack on Titan", "julz214"),
            arrayOf("Rias Gtremory", "Highschool DXD", "Lucifer012"),
            arrayOf("Yuzuriha Inori", "Guilty Crown", "dnerox"),
            arrayOf("Diane", "Seven Deadly Sins", "thecursedchuro"),
            arrayOf("Tohsaka Rin", "Fate", "Krukmeister"),
            arrayOf("Emilia", "Re:Zero", "MrTapoz-kun"),
            arrayOf("Tokisaki Kurumi", "Date A Life", "max028"),
            arrayOf("Izumi Sagiri", "Eromanga Sensei", "RizalExe"),
            arrayOf("Nishikino Maki", "Love Live", "Fransiskus Febryan"),
            arrayOf("Nao Tomori", "Charlotte", "FakhriNH"),
            arrayOf("Yukino Yukinoshita", "Oregairu", "ExLu"),
            arrayOf("Raku Ichijou", "Nisekoi", "Abesario25"),
            arrayOf("Jintan Yadomi", "Anohana", "darkprinceah"),
            arrayOf("Kariu Rena", "ReLife", "Rendracula"),
            arrayOf("Kibutsuji Muzan", "Demon Slayer", "kurijezatives"),
            arrayOf("Sanji Vinsmoke", "One Piece", "wallpsprtaccess"),
            arrayOf("Orochimaru", "Naruto", "zaneibrahime"),
            arrayOf("Takasugi Shinsuke", "Gintama", "wallpaperbetter"),
            arrayOf("Pain", "Naruto", "Darkfate17")
        )

        fun playSound(source: Int, context: Context) {
            val mediaPlayer = MediaPlayer.create(context, source)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer.stop();
                mediaPlayer.release();
            }


        }

    }



}
