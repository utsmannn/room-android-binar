package com.utsman.roombinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utsman.roombinar.entity.Product
import com.utsman.roombinar.presenter.MainPresenter
import com.utsman.roombinar.presenter.MainPresenterImpl
import com.utsman.roombinar.view.MainView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainView {

    private val TAG = "LOG_MAIN_DATABASE"

    private val rvProduct: RecyclerView by lazy { findViewById(R.id.rv_product) }
    private val btnAddProduct: Button by lazy { findViewById(R.id.btn_add_product) }
    private val btnGetProduct: Button by lazy { findViewById(R.id.btn_get_product) }

    private val productAdapter: ProductAdapter by lazy { ProductAdapter() }

    private var counter = 0

    private val mainPresenter: MainPresenter by lazy {
        MainPresenterImpl(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProduct.layoutManager = LinearLayoutManager(this)
        rvProduct.adapter = productAdapter

        btnAddProduct.setOnClickListener {
            val productName = "sample $counter"
            val product = Product(
                id = null,
                name = productName,
                stock = 1,
                color = "green"
            )

            MainScope().launch {
                mainPresenter.addProduct(product)
                counter += 1

                mainPresenter.getProducts()
            }
        }
    }

    override fun onGetProducts(products: List<Product>) = runOnUiThread {
        println("products====")
        println(products)
        productAdapter.addProduct(products)
    }
}