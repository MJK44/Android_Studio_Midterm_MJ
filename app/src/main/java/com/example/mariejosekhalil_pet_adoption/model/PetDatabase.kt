package com.example.mariejosekhalil_pet_adoption

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mariejosekhalil_pet_adoption.model.Pet
import com.example.mariejosekhalil_pet_adoption.model.PetDao

@Database(entities = [Pet::class], version = 1, exportSchema = false)
abstract class PetDatabase : RoomDatabase() {
    abstract fun petDao(): PetDao

    companion object {
        @Volatile
        private var INSTANCE: PetDatabase? = null

        fun getDatabase(context: Context): PetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PetDatabase::class.java,
                    "pet_database"
                )
                    .fallbackToDestructiveMigration() // Handles version mismatch gracefully
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
