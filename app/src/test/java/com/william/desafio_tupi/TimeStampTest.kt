package com.william.desafio_tupi


import com.william.desafio_tupi.utility.Utility
import org.junit.Assert.assertEquals
import org.junit.Test

class TimeStampTest {

    @Test
    fun `deve formatar timestamp corretamente no formato ddMMyyyy HHmm`() { // Formato desejado  dd/MM/yyyy HH:mm
        // Exemplo de timestamp (em milissegundos) - Representa 22/03/2025 15:30 no fuso horário de São Paulo
        val timestamp: Long = 1742738364000 // Pode gerar o timestamp desejado com a data e hora no formato desejado

        // O valor esperado no formato dd/MM/yyyy HH:mm
        val expectedDate = "23/03/2025 10:59"

        // Chama a função
        val result = Utility.formatTimestampToDate(timestamp)

        // Verifica se o resultado é o esperado
        assertEquals(expectedDate, result)
    }


    @Test
    fun `deve retornar o formato correto para timestamp zero`() {
        // Timestamp zero representa 01/01/1970 00:00 em UTC
        val timestamp: Long = 0

        // Data esperada para timestamp zero
        val expectedDate = "31/12/1969 21:00"

        // Chama a função
        val result = Utility.formatTimestampToDate(timestamp)

        // Verifica se o resultado é o esperado
        assertEquals(expectedDate, result)
    }
}
