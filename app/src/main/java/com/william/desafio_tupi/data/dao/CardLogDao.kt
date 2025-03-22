package com.william.desafio_tupi.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.william.desafio_tupi.data.entity.CardLog
import com.william.desafio_tupi.model.Card

@Dao
interface CardLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertCardLog(cardLog: CardLog): Long
    fun insertCardLog(cardLog: Card): Long

    @Query("SELECT * FROM card_logs")
//    fun getAllCardLogs(): LiveData<List<CardLog>>
    fun getAllCardLogs(): LiveData<List<Card>>

    @Query("SELECT * FROM card_logs WHERE isAuthorized = 1")
//    fun getAuthorizedCardLogs(): LiveData<List<CardLog>>
    fun getAuthorizedCardLogs(): LiveData<List<Card>>
}


//    @Query("SELECT * FROM card_logs ORDER BY timestamp DESC")


//    @Query("SELECT * FROM card_logs WHERE isAuthorized = 1 ORDER BY timestamp DESC")


