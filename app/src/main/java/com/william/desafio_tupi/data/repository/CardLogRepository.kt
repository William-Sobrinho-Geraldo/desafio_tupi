package com.william.desafio_tupi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.william.desafio_tupi.data.dao.CardLogDao
import com.william.desafio_tupi.data.entity.CardLog
import com.william.desafio_tupi.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardLogRepository(private val cardLogDao: CardLogDao) {
    
    val allCardLogs: LiveData<List<Card>> = cardLogDao.getAllCardLogs().map { logs ->
        logs.map { it.toCard() }
    }
    
    val authorizedCardLogs: LiveData<List<Card>> = cardLogDao.getAuthorizedCardLogs().map { logs ->
        logs.map { it.toCard() }
    }
    
    suspend fun logCard(card: Card, isAuthorized: Boolean = false): Long {
        val cardLog = CardLog(
            pan = card.pan,
            validDate = card.validDate,
            cvm = card.cvm,
            cvv = card.cvv,
            codeService = card.codeService,
            isAuthorized = isAuthorized
        )
        return withContext(Dispatchers.IO) {
            cardLogDao.insertCardLog(cardLog)
        }

//        return cardLogDao.insertCardLog(cardLog)
    }
    
    private fun CardLog.toCard(): Card {
        return Card(
            pan = this.pan,
            validDate = this.validDate,
            cvm = this.cvm,
            cvv = this.cvv,
            codeService = this.codeService
        )
    }
}

