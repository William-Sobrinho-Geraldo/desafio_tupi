package com.william.desafio_tupi

import android.app.Application
import com.william.desafio_tupi.data.database.AppDatabase
import com.william.desafio_tupi.data.repository.CardLogRepository
import com.william.desafio_tupi.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {


    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { CardLogRepository(database.cardLogDao()) }

    override fun onCreate() {
        super.onCreate()

        // Inicializa o Koin
        startKoin {
            androidContext(this@MyApp) // Fornece o contexto do Android
            modules(appModule) // Carrega os módulos de injeção de dependências
        }
    }
}

