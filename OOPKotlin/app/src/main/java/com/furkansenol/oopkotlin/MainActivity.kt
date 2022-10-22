package com.furkansenol.oopkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Counstructor
        val kullanici1 = Kullanici("Furkan", 24)
        val kullanici2 = Kullanici("Ahmet", 22)

        //Encapsulation
        val sanatci1 = Sanatci("Sanatci", 24, "İşsiz")
        print(sanatci1.isim)
        sanatci1.cinsiyet = "ErkekS"

        //Inheritance
        val ozelSanatci = OzelSanatci("Ozel Sanatci", 25, "Sarkici")
        println(ozelSanatci.isim)
        ozelSanatci.sarkiSoyle()

        //Polymorphism
        //Static
        val islem = Islem()
        println(islem.carpma())
        println(islem.carpma(5, 2))
        println(islem.carpma(1, 2, 7))

        //dynamic
        val hayvan = Hayvan()
        println(hayvan.sesCikar())

        val kopek = Kopek()
        println(kopek.sesCikar())
        println(kopek.kopekFonksiyonu())

        //Abstract
        println(kullanici1.insanFonksiyonu())

        //Interface
        val gitar = Gitar("marka", true)
        gitar.bilgi()
        println(gitar.oda)

        //Lambda

    }
}