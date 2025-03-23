package com.william.desafio_tupi

import com.william.desafio_tupi.utility.Utility
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CardValidatorTest {
    /**
     *   Validando número do cartão PAN
     */
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

    /**
     *   Validando data de expiracao
     */


}