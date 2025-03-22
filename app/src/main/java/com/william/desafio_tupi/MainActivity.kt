package com.william.desafio_tupi

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener
import com.william.desafio_tupi.adapterRecyclerView.AdapterLogs
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
    private lateinit var adapterLogs: AdapterLogs

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

        adapterLogs = AdapterLogs()
        configRecyclerLogs(adapterLogs)
        configButtonShowLogs()
        condigButtonCloseLogs()
        configButtonValidarCartao()

        configObservers()
        configMaskCardNumber()
        configMaskExpiryDate()
    }

    private fun configRecyclerLogs(adapterLogs: AdapterLogs) {
        binding.recyclerLogs.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adapterLogs
        }
    }


    private fun configButtonShowLogs() {
        binding.buttonShowLogs.setOnClickListener {
            binding.recyclerLogs.visibility = View.VISIBLE
            binding.buttonCloseLogs.visibility = View.VISIBLE
            binding.buttonShowLogs.visibility = View.GONE
        }
    }

    private fun condigButtonCloseLogs() {
        binding.buttonCloseLogs.setOnClickListener {
            binding.buttonShowLogs.visibility = View.VISIBLE
            binding.buttonCloseLogs.visibility = View.GONE
            binding.recyclerLogs.visibility = View.GONE
        }
    }

    private fun configButtonValidarCartao() {
        buttonValidate.setOnClickListener {
            val card = gerarCardComOsDadosDigitados()

            val resultadoValidacao = Utility.validateCard(card = card, context = this)
            // Tratar o resultado
            resultadoValidacao.onSuccess { cartaoValido ->   // Cartão Válido
                val transacaoAutorizada = Utility.gerarAutorizacaoAleatoria()
                cartaoValido.isAuthorized = transacaoAutorizada

                if (transacaoAutorizada) mostrarToast(getString(R.string.transacao_autorizada), this)
                else mostrarToast(getString(R.string.transacao_negada), this)

                // Logar/Inserir dados no SQLite
                cardViewModel.logCard(cartaoValido)
            }.onFailure { exception ->  // Cartão inválido ou erro na validação
                mostrarToast("${exception.message}", this)
            }
        }
    }

    private fun gerarCardComOsDadosDigitados() = Card(
        holderName = editTextCardHolderName.text.toString(),
        pan = editTextCardNumber.text.toString(),
        validDate = editTextExpiryDate.text.toString(),
        cvm = Utility.getRandomMethod(),
        cvv = editTextCvv.text.toString(),
        createdAt = System.currentTimeMillis()
    )

    private fun configObservers() {
        cardViewModel.allCardLogs.observe(this) { cardLogs ->
            adapterLogs.updateList(cardLogs)
            if (cardLogs.size == 0) {
                binding.txtSemLogs.visibility = View.VISIBLE
            } else {
                binding.txtSemLogs.visibility = View.GONE
            }
        }

        cardViewModel.successStatus.observe(this) { card ->
            mostrarToast(getString(R.string.cartao_valido) + card.pan + "Operação registrada no banco de dados! ", this)
        }

        cardViewModel.errorStatus.observe(this) { errorMessage ->
            mostrarToast("Erro: $errorMessage", this)
        }
    }

    private fun configMaskCardNumber() {
        val maskCardNumber = MaskEditTextChangedListener("#### #### #### #### #### #### ###", binding.editTextCardNumber)
        editTextCardNumber.addTextChangedListener(maskCardNumber)
    }

    private fun configMaskExpiryDate() {
        val maskExpiryDate = MaskEditTextChangedListener("##/##", binding.editTextExpiryDate)
        editTextExpiryDate.addTextChangedListener(maskExpiryDate)
    }


}