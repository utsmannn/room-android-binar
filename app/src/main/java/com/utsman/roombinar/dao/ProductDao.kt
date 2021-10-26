package com.utsman.roombinar.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.utsman.roombinar.entity.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM PRODUCT")
    fun getProductAll(): List<Product>

    @Query("SELECT * FROM PRODUCT WHERE :id = id")
    fun getProductById(id: Int): Product

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(product: Product)

    @Update
    fun updateProduct(product: Product)

    @Delete
    fun deleteProduct(product: Product)
}