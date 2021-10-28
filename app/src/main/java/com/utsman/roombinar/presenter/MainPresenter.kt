package com.utsman.roombinar.presenter

import com.utsman.roombinar.entity.Product

interface MainPresenter {
    suspend fun getProducts()
    suspend fun addProduct(product: Product)
}