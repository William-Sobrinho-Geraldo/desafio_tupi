package com.william.desafio_tupi.data.repository

import androidx.lifecycle.LiveData
import com.william.desafio_tupi.data.dao.CardLogDao
import com.william.desafio_tupi.model.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardLogRepository(private val cardLogDao: CardLogDao) {

    val allCardLogs: LiveData<List<Card>> = cardLogDao.getAllCardLogs()

    val authorizedCardLogs: LiveData<List<Card>> = cardLogDao.getAuthorizedCardLogs()

    suspend fun logCard(card: Card): Result<Unit> {
        return try {
            withContext(Dispatchers.IO) { cardLogDao.insertCardLog(card) }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}

