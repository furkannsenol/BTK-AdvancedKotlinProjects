package com.atilsamancioglu.besinlerkitabigradlework.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData

import com.atilsamancioglu.besinlerkitabigradlework.model.Besin
import com.atilsamancioglu.besinlerkitabigradlework.service.BesinAPIServis
import com.atilsamancioglu.besinlerkitabigradlework.service.BesinDatabase
import com.atilsamancioglu.besinlerkitabigradlework.util.OzelSharedPreferences

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class BesinListesiViewModel(application: Application) : BaseViewModel(application) {
    val besinler = MutableLiveData<List<Besin>>()
    val besinHataMesaji = MutableLiveData<Boolean>()
    val besinYukleniyor = MutableLiveData<Boolean>()

    private val besinApiServis = BesinAPIServis()
    private val disposable = CompositeDisposable() //istekleri kullan at anlamında
    private val ozelSharedPreferences = OzelSharedPreferences(getApplication())
    private var guncellemeZamani = 5 * 60 * 1000 * 1000 * 1000L //nano time a cevirme işlemi

    fun refreshData() {
        /*
        val muz = Besin("muz","1","1","1","1","1")
        val elma = Besin("elma","2","2","2","2","2")

        val besinListesi = arrayListOf<Besin>(muz,elma)

        besinler.value=besinListesi
        besinHataMesaji.value=false
        besinYukleniyor.value=false*/

        val kaydedilmeZamani = ozelSharedPreferences.zamaniAl()
        if (kaydedilmeZamani != null && kaydedilmeZamani != 0L && System.nanoTime() - kaydedilmeZamani < guncellemeZamani) {
            //SQLite dan al
            verileriSQLitetanAl()
        } else {
            verileriInternettenAl()
        }
    }

    fun refreshFromInternet() {
        verileriInternettenAl()
    }

    private fun verileriSQLitetanAl() {
        besinYukleniyor.value=true
        launch {
            val besinListesi = BesinDatabase(getApplication()).besinDao().getAllBesin()
            besinleriGoster(besinListesi)
            Toast.makeText(getApplication(), "Besinleri Room dan aldık", Toast.LENGTH_SHORT).show()
        }
    }

    private fun verileriInternettenAl() {
        besinYukleniyor.value = true

        disposable.add(
            besinApiServis.getData()
                .subscribeOn(Schedulers.newThread())//gözlemleri izleme
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Besin>>() {
                    override fun onSuccess(t: List<Besin>) {
                        //başarılı olursa
                        sqliteSakla(t)
                        Toast.makeText(
                            getApplication(),
                            "Besinleri Internetten aldık",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onError(e: Throwable) {
                        //hata alınırsa
                        besinHataMesaji.value = true
                        besinYukleniyor.value = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun besinleriGoster(besinlerListesi: List<Besin>) {
        besinler.value = besinlerListesi
        besinHataMesaji.value = false
        besinYukleniyor.value = false
    }

    private fun sqliteSakla(besinlerListesi: List<Besin>) {
        launch {
            val dao = BesinDatabase(getApplication()).besinDao()
            dao.deleteAllBesin()
            val uuidListesi = dao.insertAll(*besinlerListesi.toTypedArray())// verileri tek tek atar
            var i = 0
            while (i < besinlerListesi.size) {
                besinlerListesi[i].uuid = uuidListesi[i].toInt()
                i++
            }
            besinleriGoster(besinlerListesi)
        }
        ozelSharedPreferences.zamaniKaydet(System.nanoTime())
    }
}