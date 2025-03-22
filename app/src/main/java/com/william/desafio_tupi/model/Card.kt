package com.william.desafio_tupi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_logs")
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val holderName: String? = null, // Número do cartão (Primary Account Number)
    val pan: String, // Número do cartão (Primary Account Number)
    val validDate: String, // Data de validade no formato MM/YY
    val cvm: String, // Método de verificação (ex: "PIN", "Signature", "None")
    val cvv: String, // Código de verificação (ex: "123")
    val codeService: String = "101",// Código de serviço (ex: "101")
    var isAuthorized: Boolean = false,
    val createdAt: Long? = null // Timestamp da criação

)