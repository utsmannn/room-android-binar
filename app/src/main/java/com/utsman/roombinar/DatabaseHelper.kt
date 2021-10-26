package com.utsman.roombinar

import android.content.Context
import com.utsman.roombinar.dao.ProductDao
import com.utsman.roombinar.database.ProductDatabase
import com.utsman.roombinar.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DatabaseHelper(private val context: Context) {

    private val productDatabase: ProductDatabase by lazy {
        ProductDatabase.getInstance(context)
    }

    private val productDao: ProductDao by lazy {
        productDatabase.productDao()
    }

    suspend fun getProducts(): List<Product> = withContext(Dispatchers.IO) {
        return@withContext productDao.getProductAll()
    }

    suspend fun getProductById(id: Int): Product = withContext(Dispatchers.IO) {
        return@withContext productDao.getProductById(id)
    }

    suspend fun addProduct(product: Product) = withContext(Dispatchers.IO) {
        productDao.addProduct(product)
    }

    fun updateProduct(product: Product) = GlobalScope.launch {
        productDao.updateProduct(product)
    }

    fun deleteProduct(product: Product) = GlobalScope.launch {
        productDao.deleteProduct(product)
    }
}