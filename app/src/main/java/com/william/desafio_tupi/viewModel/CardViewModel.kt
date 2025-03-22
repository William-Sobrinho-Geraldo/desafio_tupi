package com.william.desafio_tupi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.william.desafio_tupi.data.entity.CardLog
import com.william.desafio_tupi.data.repository.CardLogRepository
import com.william.desafio_tupi.model.Card
import com.william.desafio_tupi.utility.Utility
import kotlinx.coroutines.launch

class CardViewModel(private val repository: CardLogRepository) : ViewModel() {
    
    // LiveData for all card logs
    val allCardLogs: LiveData<List<Card>> = repository.allCardLogs
    
    // LiveData for authorized card logs
    val authorizedCardLogs: LiveData<List<Card>> = repository.authorizedCardLogs
    
    // Status of the last operation
    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus
    
    // Log a card
    fun logCard(card: Card) {
//    fun logCard(card: CardLog) {
        viewModelScope.launch {
            try {
                repository.logCard(card)
                _operationStatus.value = OperationStatus.SUCCESS
            } catch (e: Exception) {
                _operationStatus.value = OperationStatus.ERROR
            }
        }
    }
    
    // Process a transaction and log it if authorized

    // Operation status enum
    enum class OperationStatus {
        SUCCESS,
        ERROR,
    }


//    fun processTransaction(card: Card) {
//        viewModelScope.launch {
//            try {
//                val transacaoAutorizada = Utility.mockAuthorize()
//                if (transacaoAutorizada) {
//                    repository.logCard(card, isAuthorized = true)
//                    _operationStatus.value = OperationStatus.TRANSACTION_AUTHORIZED
//                } else {
//                    _operationStatus.value = OperationStatus.TRANSACTION_DECLINED
//                }
//            } catch (e: Exception) {
//                _operationStatus.value = OperationStatus.ERROR
//            }
//        }
//    }

    // Factory for creating the ViewModel
    class CardViewModelFactory(private val repository: CardLogRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

