package com.atilsamancioglu.besinlerkitabigradlework.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.atilsamancioglu.besinlerkitabigradlework.model.Besin
import com.atilsamancioglu.besinlerkitabigradlework.service.BesinDatabase
import kotlinx.coroutines.launch

class BesinDetayiViewModel(application: Application) : BaseViewModel(application) {
    val besinLiveData = MutableLiveData<Besin>()

    fun roomVerisiniAli(uuid: Int) {
        /*val muz = Besin("muz","1","1","1","1","1")
        besinLiveData.value = muz*/
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            val besin = dao.getBesin(uuid)
            besinLiveData.value = besin
        }
    }
}