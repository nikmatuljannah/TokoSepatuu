package com.example.tokosepatu.application

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tokosepatu.dao.ShoeDao
import com.example.tokosepatu.model.Shoe

@Database(entities = [Shoe::class], version = 1, exportSchema = false)
abstract class ShoeDatabase: RoomDatabase() {
    abstract fun ShoeDao(): ShoeDao

    companion object {
        private var INSTANCE:ShoeDatabase? = null

        fun getDatabase(context: Context): ShoeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoeDatabase::class.java,
                    "shoe_database"
                )
                    .allowMainThreadQueries()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}