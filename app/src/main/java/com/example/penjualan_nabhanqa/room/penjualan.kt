package com.example.penjualan_nabhanqa.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [tbbarang::class],
    version = 1
)
abstract class penjualan : RoomDatabase(){
    abstract fun TBBRGDAO(): tbbarangDao

    companion object {

        @Volatile private var instance : penjualan? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            penjualan::class.java,
            "id_db"
        ).build()

    }

}
