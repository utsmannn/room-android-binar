package com.utsman.roombinar.view

import com.utsman.roombinar.entity.Product

interface MainView {
    fun onGetProducts(products: List<Product>)
}