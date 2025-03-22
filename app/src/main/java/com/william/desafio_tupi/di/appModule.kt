package com.william.desafio_tupi.di

import com.william.desafio_tupi.data.database.AppDatabase
import com.william.desafio_tupi.data.repository.CardLogRepository
import com.william.desafio_tupi.viewModel.CardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Singleton do AppDatabase (Room)
    single {
        AppDatabase.getDatabase(androidContext())
    }

    // Singleton do CardLogDao
    single {
        get<AppDatabase>().cardLogDao()
    }

    // Singleton do CardLogRepository
    single {
        CardLogRepository(get())
    }

    // ViewModel do CardViewModel
    viewModel {
        CardViewModel(get())
    }
}