package com.atilsamancioglu.besinlerkitabigradlework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atilsamancioglu.besinlerkitabigradlework.model.Besin

class BesinListesiViewModel: ViewModel() {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji=MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()

    fun refreshData(){
        val muz = Besin("muz","1","1","1","1","1")
        val elma = Besin("elma","2","2","2","2","2")

        val besinListesi = arrayListOf<Besin>(muz,elma)

        besinler.value=besinListesi
        besinHataMesaji.value=false
        besinYukleniyor.value=false
    }
}