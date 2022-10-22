package com.furkansenol.oopkotlin

class Gitar(marka: String?, elektro: Boolean?):Dekorasyon,Enstruman {
    var marka: String? = marka
    var elektro: Boolean? = elektro
    override var oda: String
        get() = "Salon"
        set(value) {}
}