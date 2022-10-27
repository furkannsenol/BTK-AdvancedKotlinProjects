package com.atilsamancioglu.besinlerkitabigradlework.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atilsamancioglu.besinlerkitabigradlework.model.Besin

class BesinDetayiViewModel: ViewModel(){
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAli(){
        val muz = Besin("muz","1","1","1","1","1")
        besinLiveData.value = muz
    }
}