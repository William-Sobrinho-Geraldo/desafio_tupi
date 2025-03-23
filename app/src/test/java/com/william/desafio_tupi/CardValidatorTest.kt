package com.william.desafio_tupi

import com.william.desafio_tupi.utility.Utility
import com.william.desafio_tupi.utility.Utility.Companion.isValidCvm
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CardValidatorTest {
    /**  =====================================================================
     *   Validando número do cartão PAN
    ===================================================================== */
    @Test
    fun `Número do cartão deve ser válido`() {
        val validCard = "4556737586899855" // Válido
        assertTrue(Utility.isValidPan(validCard))
    }

    @Test
    fun `Número do cartão deve ser inválido`() {
        val invalidCard = "4556737586899885"
        assertFalse(Utility.isValidPan(invalidCard))
    }

    @Test
    fun `Número do cartão muito curto deve ser inválido`() {
        val invalidCard = "12345"
        assertFalse(Utility.isValidPan(invalidCard))
    }

    @Test
    fun `Número do cartão muito longo deve ser inválido`() {
        val invalidCard = "123456789012345678901"
        assertFalse(Utility.isValidPan(invalidCard))
    }

    @Test
    fun `Número do cartão com caracteres inválidos deve ser inválido`() {
        val caracteresInvalidos = "4556t37586899855"
        assertFalse(Utility.isValidPan(caracteresInvalidos))
    }

    /**  =====================================================================
     *   Validando data de expiracao
    =====================================================================*/

    @Test
    fun `Data de validade válida no futuro deve ser aceita`() {
        val futureDate = YearMonth.now().plusMonths(1).format(DateTimeFormatter.ofPattern("MM/yy"))
        assertTrue(Utility.isValidExpiryDate(futureDate))
    }

    @Test
    fun `Data de validade no mês atual deve ser rejeitada`() {
        val currentDate = YearMonth.now().format(DateTimeFormatter.ofPattern("MM/yy"))
        assertFalse(Utility.isValidExpiryDate(currentDate))
    }

    @Test
    fun `Data de validade no passado deve ser rejeitada`() {
        val pastDate = YearMonth.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM/yy"))
        assertFalse(Utility.isValidExpiryDate(pastDate))
    }

    @Test
    fun `Formato de data inválido deve ser rejeitado`() {
        val invalidDate = "13/2025" // Formato errado
        assertFalse(Utility.isValidExpiryDate(invalidDate))
    }

    @Test
    fun `Data com mês inválido deve ser rejeitada`() {
        val invalidMonth = "15/25" // Mês inválido
        assertFalse(Utility.isValidExpiryDate(invalidMonth))
    }

    @Test
    fun `Data com caracteres inválidos deve ser rejeitada`() {
        val invalidChars = "ab/cd"
        assertFalse(Utility.isValidExpiryDate(invalidChars))
    }

    /**  =====================================================================
     *   Validando Método de verificação - CVM
    =====================================================================*/

    @Test
    fun `getRandomMethod deve retornar um método válido`() {
        // Lista de métodos suportados
        val supportedMethods = listOf("PIN", "SIGNATURE", "NONE", "ONLINE", "OFFLINE", "BIOMETRIA")

        // Chamada da função para obter o valor aleatório
        val method = Utility.getRandomMethod()

        // Verifica se o valor retornado está dentro dos valores suportados
        assertTrue("Método retornado inválido: $method", supportedMethods.contains(method))
    }

    @Test
    fun `CVM válido deve retornar true para PIN`() {
        val cvm = "PIN"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve retornar true para SIGNATURE`() {
        val cvm = "SIGNATURE"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve retornar true para NONE`() {
        val cvm = "NONE"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve retornar true para ONLINE`() {
        val cvm = "ONLINE"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve retornar true para OFFLINE`() {
        val cvm = "OFFLINE"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve retornar true para BIOMETRIA`() {
        val cvm = "BIOMETRIA"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM inválido deve retornar false`() {
        val cvm = "INVALID_METHOD"
        assertFalse(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve funcionar com letras minúsculas`() {
        val cvm = "pin"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM válido deve funcionar com mistura de maiúsculas e minúsculas`() {
        val cvm = "pIn"
        assertTrue(isValidCvm(cvm))
    }

    @Test
    fun `CVM inválido com string vazia deve retornar false`() {
        val cvm = ""
        assertFalse(isValidCvm(cvm))
    }

    /**  =====================================================================
     *   Validando Código de segurança - CVV
    =====================================================================*/
    @Test
    fun `CVV com 3 dígitos deve ser válido`() {
        assertTrue(Utility.isValidCvv("123"))
    }

    @Test
    fun `CVV com menos de 3 dígitos deve ser inválido`() {
        assertFalse(Utility.isValidCvv("12"))
    }

    @Test
    fun `CVV com mais de 3 dígitos deve ser inválido`() {
        assertFalse(Utility.isValidCvv("1234"))
    }

    @Test
    fun `CVV vazio deve ser inválido`() {
        assertFalse(Utility.isValidCvv(""))
    }

}