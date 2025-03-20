package com.william.desafio_tupi

import android.app.Application
import com.william.desafio_tupi.data.database.AppDatabase
import com.william.desafio_tupi.data.repository.CardLogRepository

class CardLoggerApp : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CardLogRepository(database.cardLogDao()) }
}

