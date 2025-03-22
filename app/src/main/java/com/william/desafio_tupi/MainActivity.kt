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
import com.william.desafio_tupi.viewModel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    // Injeta o CardViewModel usando Koin
    private val cardViewModel: CardViewModel by viewModel()

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
        editTextCardHolderName = binding.editTextCardholderName
        editTextCardNumber = binding.editTextCardNumber
        editTextExpiryDate = binding.editTextExpiryDate
        editTextCvv = binding.editTextCvv
        buttonValidate = binding.buttonValidate

        // Configurar botão de validação
        buttonValidate.setOnClickListener {
            val card = Card(
                pan = editTextCardNumber.text.toString(),
                validDate = editTextExpiryDate.text.toString(),
                cvm = "PIN",
                cvv = editTextCvv.text.toString(),
            )

            val result = Utility.validateCard(card = card, context = this)
            // Tratar o resultado
            result.onSuccess { validatedCard ->
                // Cartão válido
                mostrarToast(getString(R.string.cartao_valido) + validatedCard.pan, this)
                val transacaoAutorizada = Utility.mockAuthorize()
                validatedCard.isAuthorized = transacaoAutorizada

                if (transacaoAutorizada) mostrarToast(getString(R.string.transacao_autorizada), this)
                else mostrarToast(getString(R.string.transacao_negada), this)


                //logar dados no banco de dados
                cardViewModel.logCard(validatedCard)
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
        editTextExpiryDate.addTextChangedListener(maskExpiryDate)
    }

    private fun configMaskCardNumber() {
        val maskCardNumber = MaskEditTextChangedListener("#### #### #### #### #### #### ###", binding.editTextCardNumber)
        editTextCardNumber.addTextChangedListener(maskCardNumber)
    }


}