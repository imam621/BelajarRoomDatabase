package com.example.belajarroomdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Siswa::class], version = 4, exportSchema = false)
abstract class BelajarRoomDB : RoomDatabase(){

    abstract fun SiswaDAO():SiswaDAO
    companion object {
        @Volatile
        private var INSTANCE :BelajarRoomDB?=null
        private var key = Any()
        operator fun invoke(context: Context) = INSTANCE ?: synchronized(key){
            INSTANCE ?: buildDatabase(context).also {
                INSTANCE= it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, BelajarRoomDB::class.java, "biodatadb"
        ).fallbackToDestructiveMigration().build()
    }
}