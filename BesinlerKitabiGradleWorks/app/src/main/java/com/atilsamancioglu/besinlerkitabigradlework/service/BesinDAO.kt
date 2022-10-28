package com.atilsamancioglu.besinlerkitabigradlework.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.atilsamancioglu.besinlerkitabigradlework.model.Besin

@Dao
interface BesinDAO {

    //Data Accces Object : verilere ulaşma işlemleri
    //Insert -> Room, insert into
    //suspends -> coroutine scope (verileri yerele kaydetme fonk)
    //varargs -> birden fazla obje oluşturmamıza olanak sağlıyor
    //QList<Long> -> id'ler için long verdik
    @Insert
    suspend fun insertAll(vararg  besin: Besin):List<Long>

    @Query("Select * from besin")
    suspend fun getAllBesin():List<Besin>

    @Query("Select * from besin where uuid = :besinId")
    suspend fun getBesin(besinId: Int):Besin

    @Query("Delete from besin")
    suspend fun deleteAllBesin()
}