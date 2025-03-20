package com.william.desafio_tupi.model

data class Card(
    val pan: String, // Número do cartão (Primary Account Number)
    val validDate: String, // Data de validade no formato MM/YY
    val cvm: String, // Método de verificação (ex: "PIN", "Signature", "None")
    val cvv: String, // Código de verificação (ex: "123")
    val codeService: String = "101"// Código de serviço (ex: "101")
)