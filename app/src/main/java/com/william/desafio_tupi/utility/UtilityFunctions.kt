package com.william.desafio_tupi.utility

import android.content.Context
import android.widget.Toast
import com.william.desafio_tupi.R
import com.william.desafio_tupi.model.Card
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import kotlin.random.Random

class Utility {
    companion object {

        fun validateCard(card: Card, context: Context): Result<Card> {
            // Validar PAN (número do cartão)
            if (!isValidPan(card.pan)) {
                return Result.failure(Exception("Número de cartão inválido"))
            }

            // Validar data de validade
            if (!isValidExpiryDate(card.validDate)) {
                return if (card.validDate.isNullOrBlank()) {
                    Result.failure(Exception("Preencher data de validade"))
                } else {
                    Result.failure(Exception(context.getString(R.string.data_invalida)))
                }
            }

            // Validar CVM (método de verificação)
            if (!isValidCvm(card.cvm)) {
                return Result.failure(Exception(context.getString(R.string.metodo_nao_suportado)))
            }

            if (!isValidCvv(card.cvv)) {
                return if (card.cvv.isNullOrBlank()) {
                    Result.failure(Exception(context.getString(R.string.preencher_cvv)))
                } else {
                    Result.failure(Exception("Cvv inválido"))
                }
            }

            // Se todas as validações passaram, retorna sucesso
            return Result.success(card)
        }


        /**
         * Valida o número do cartão (PAN) usando o algoritmo de Luhn
         * O Pan   4556737586899855   é válido segundo Luhn
         */
        private fun isValidPan(cardNumber: String): Boolean {
            val cleanPan = cardNumber.replace("\\D".toRegex(), "")

            // Verificar comprimento (entre 13 e 19 dígitos)
            if (cleanPan.length < 13 || cleanPan.length > 19) return false

            var sum = 0
            val length = cleanPan.length

            for (i in 0 until length) {
                var digit = cleanPan[length - 1 - i] - '0' // Convert char to int

                if (i % 2 == 1) { // Dobrar cada segundo algarismo começando da direita para a esquerda
                    digit *= 2
                    if (digit > 9) {
                        digit -= 9 // Ajustar se for maior que 9
                    }
                }

                sum += digit
            }

            return sum % 10 == 0 // Checar se no fim é divisível por 10
        }

        /**
         * Valida a data de validade do cartão
         */
        private fun isValidExpiryDate(expiryDate: String): Boolean {
            return try {
                // Define o formato da data (MM/yy)
                val formatter = DateTimeFormatter.ofPattern("MM/yy")

                // Converte a string de data de validade para LocalDate
                // Adiciona "01/" para criar uma data completa (dia/mês/ano)
                val expirationYearMonth = YearMonth.parse(expiryDate, formatter)
                val expiration = expirationYearMonth.atDay(1)
                // Obtém a data atual
                val currentDate = LocalDate.now()

                // Verifica se a data de validade é igual ou posterior à data atual
                !expiration.isBefore(currentDate)
            } catch (e: DateTimeParseException) {
                // Captura exceções relacionadas ao formato de data inválido
                println("Erro: Formato de data inválido. Use o formato MM/yy.")
                false
            } catch (e: Exception) {
                // Captura outras exceções inesperadas
                println("Erro inesperado: ${e.message}")
                false
            }
        }

        /**
         * Valida o método de verificação do cartão (CVM)
         */
        private fun isValidCvm(cvm: String): Boolean {
            // Lista de métodos de verificação suportados
            val supportedMethods = listOf("PIN", "SIGNATURE", "NONE", "ONLINE", "OFFLINE", "BIOMETRIA")

            // Verificar se o CVM contém pelo menos um método suportado
            return supportedMethods.any { method ->
                cvm.contains(method, ignoreCase = true)
            }
        }

        private fun isValidCvv(cvv: String): Boolean {
            return cvv.length == 3
        }

        fun mostrarToast(message: String, context: Context) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        fun mockAuthorize(): Boolean {
            return Random.nextBoolean() // Retorna true ou false aleatoriamente
        }
    }
}

