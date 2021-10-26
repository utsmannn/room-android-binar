package com.utsman.roombinar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.utsman.roombinar.dao.ProductDao
import com.utsman.roombinar.entity.Product

@Database(entities = [Product::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance: ProductDatabase? = null

        private fun buildDatabase(context: Context): ProductDatabase {
            return Room.databaseBuilder(context, ProductDatabase::class.java, "product_db")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun getInstance(context: Context): ProductDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }
    }
}