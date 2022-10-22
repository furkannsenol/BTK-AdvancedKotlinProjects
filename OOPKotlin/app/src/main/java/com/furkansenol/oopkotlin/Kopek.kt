package com.furkansenol.oopkotlin

class Kopek: Hayvan() {

    fun kopekFonksiyonu(){
        super.sesCikar()
    }

    override fun sesCikar(){
        println("Kopek sınıfı")
    }
}