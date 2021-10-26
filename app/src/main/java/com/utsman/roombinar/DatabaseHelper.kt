package com.utsman.roombinar

import android.content.Context
import com.utsman.roombinar.dao.ProductDao
import com.utsman.roombinar.database.ProductDatabase
import com.utsman.roombinar.entity.Product

class DatabaseHelper(private val context: Context) {

    private val productDatabase: ProductDatabase by lazy {
        ProductDatabase.getInstance(context)
    }

    private val productDao: ProductDao by lazy {
        productDatabase.productDao()
    }

    fun getProducts(): List<Product> {
        return productDao.getProductAll()
    }

    fun getProductById(id: Int): Product {
        return productDao.getProductById(id)
    }

    fun addProduct(product: Product) {
        productDao.addProduct(product)
    }

    fun updateProduct(product: Product) {
        productDao.updateProduct(product)
    }

    fun deleteProduct(product: Product) {
        productDao.deleteProduct(product)
    }
}