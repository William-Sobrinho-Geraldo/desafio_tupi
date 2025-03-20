package com.william.desafio_tupi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_logs")
data class CardLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val pan: String,
    val validDate: String,
    val cvm: String,
    val cvv: String,
    val codeService: String,
    val isAuthorized: Boolean = false
)


//    val timestamp: Date = Date(),