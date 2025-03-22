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

    private val _successStatus = MutableLiveData<Card>()
    val successStatus: LiveData<Card> = _successStatus

    private val _errorStatus = MutableLiveData<String>()
    val errorStatus: LiveData<String> = _errorStatus

    // LiveData for authorized card logs
    val authorizedCardLogs: LiveData<List<Card>> = repository.authorizedCardLogs

    // Status of the last operation
    private val _operationStatus = MutableLiveData<OperationStatus>()
    val operationStatus: LiveData<OperationStatus> = _operationStatus


    fun logCard(card: Card) {
        viewModelScope.launch {
            repository.logCard(card)
                .onSuccess {
                    _successStatus.value = card // Notifica o sucesso

//                    _operationStatus.value = OperationStatus.SUCCESS
                }
                .onFailure {
//                    _operationStatus.value = OperationStatus.ERROR
                    _errorStatus.value = it.message // Notifica o erro

                }
        }
    }
//    fun logCard(card: Card) {
////    fun logCard(card: CardLog) {
//        viewModelScope.launch {
//            try {
//                repository.logCard(card)
//                _operationStatus.value = OperationStatus.SUCCESS
//            } catch (e: Exception) {
//                _operationStatus.value = OperationStatus.ERROR
//            }
//        }
//    }


    // Operation status enum
    enum class OperationStatus {
        SUCCESS,
        ERROR,
    }

}

