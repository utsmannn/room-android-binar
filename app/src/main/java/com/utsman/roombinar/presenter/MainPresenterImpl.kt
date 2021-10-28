package com.utsman.roombinar.presenter

import android.content.Context
import com.utsman.roombinar.dao.ProductDao
import com.utsman.roombinar.database.ProductDatabase
import com.utsman.roombinar.entity.Product
import com.utsman.roombinar.view.MainView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainPresenterImpl(private val context: Context, private val mainView: MainView) : MainPresenter {

    private val productDatabase: ProductDatabase by lazy {
        ProductDatabase.getInstance(context)
    }

    private val productDao: ProductDao by lazy {
        productDatabase.productDao()
    }

    override suspend fun getProducts() = withContext(Dispatchers.IO) {
        val products = productDao.getProductAll()
        mainView.onGetProducts(products)
    }

    override suspend fun addProduct(product: Product) = withContext(Dispatchers.IO) {
        productDao.addProduct(product)
    }
}