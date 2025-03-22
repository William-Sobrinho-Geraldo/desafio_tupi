package com.william.desafio_tupi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.william.desafio_tupi.data.dao.CardLogDao
import com.william.desafio_tupi.data.entity.CardLog
import com.william.desafio_tupi.model.Card

//@Database(entities = [CardLog::class], version = 1, exportSchema = false)
@Database(entities = [Card::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardLogDao(): CardLogDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "card_logger_database"
                )
                    .fallbackToDestructiveMigration() // Add this line to handle schema changes
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

//@TypeConverters(DateConverter::class)



//    companion object {
//        @Volatile
//        private var INSTANCE: AppDatabase? = null
//
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "card_logger_database"
//                )
//                    .fallbackToDestructiveMigration()
//                    .build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }

}

