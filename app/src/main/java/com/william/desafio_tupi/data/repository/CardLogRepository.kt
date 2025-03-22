package com.william.desafio_tupi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.william.desafio_tupi.data.dao.CardLogDao
import com.william.desafio_tupi.data.entity.CardLog
import com.william.desafio_tupi.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardLogRepository(private val cardLogDao: CardLogDao) {

    val allCardLogs: LiveData<List<Card>> = cardLogDao.getAllCardLogs()

    val authorizedCardLogs: LiveData<List<Card>> = cardLogDao.getAuthorizedCardLogs()

    //    suspend fun logCard(card: Card, isAuthorized: Boolean = false): Long {
//        return withContext(Dispatchers.IO) { cardLogDao.insertCardLog(card) }
//    }
    suspend fun logCard(card: Card): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) { cardLogDao.insertCardLog(card) }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
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

