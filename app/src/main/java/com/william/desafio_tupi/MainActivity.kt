package com.william.desafio_tupi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener
import com.william.desafio_tupi.model.Card
import com.william.desafio_tupi.databinding.ActivityMainBinding
import com.william.desafio_tupi.utility.Utility
import com.william.desafio_tupi.utility.Utility.Companion.mostrarToast

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var editTextCardHolderName: EditText
    private lateinit var editTextCardNumber: EditText
    private lateinit var editTextExpiryDate: EditText
    private lateinit var editTextCvv: EditText
    private lateinit var buttonValidate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar views
        editTextCardHolderName = findViewById(R.id.editTextCardholderName)
        editTextCardNumber = findViewById(R.id.editTextCardNumber)
        editTextExpiryDate = findViewById(R.id.editTextExpiryDate)
        editTextCvv = findViewById(R.id.editTextCvv)
        buttonValidate = findViewById(R.id.buttonValidate)

        // Configurar botão de validação
        buttonValidate.setOnClickListener {
            val card = Card(
                pan = binding.editTextCardNumber.text.toString(),
                validDate = binding.editTextExpiryDate.text.toString(),
                cvm = "PIN",
                cvv = binding.editTextCvv.text.toString(),
            )

            val result = Utility.validateCard(card = card, context = this)
            // Tratar o resultado
            result.onSuccess { validatedCard ->
                // Cartão válido
                mostrarToast(getString(R.string.cartao_valido) + validatedCard.pan, this)
                val transacaoAutorizada = Utility.mockAuthorize()
                if (transacaoAutorizada) mostrarToast(getString(R.string.transacao_autorizada), this)
                else mostrarToast(getString(R.string.transacao_negada), this)

            }.onFailure { exception ->
                // Cartão inválido ou erro na validação
                mostrarToast("${exception.message}", this)
            }
        }

        configMaskCardNumber()
        configMaskExpiryDate()
    }

    private fun configMaskExpiryDate() {
        val maskExpiryDate = MaskEditTextChangedListener("##/##", binding.editTextExpiryDate)
        binding.editTextExpiryDate.addTextChangedListener(maskExpiryDate)
    }

    private fun configMaskCardNumber() {
        val maskCardNumber = MaskEditTextChangedListener("#### #### #### #### #### #### ###", binding.editTextCardNumber)
        binding.editTextCardNumber.addTextChangedListener(maskCardNumber)
    }


}