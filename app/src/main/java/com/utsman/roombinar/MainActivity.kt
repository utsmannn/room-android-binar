package com.utsman.roombinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.utsman.roombinar.entity.Product
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "LOG_MAIN_DATABASE"

    private val rvProduct: RecyclerView by lazy { findViewById(R.id.rv_product) }
    private val btnAddProduct: Button by lazy { findViewById(R.id.btn_add_product) }
    private val btnGetProduct: Button by lazy { findViewById(R.id.btn_get_product) }

    private val productAdapter: ProductAdapter by lazy { ProductAdapter() }

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHelper = DatabaseHelper(this)

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
                databaseHelper.addProduct(product)
                counter += 1

                val products = databaseHelper.getProducts()
                println("products====")
                println(products)
                runOnUiThread {
                    productAdapter.addProduct(products)
                }
            }
        }

        /*btnGetProduct.setOnClickListener {
            MainScope().launch {

            }
        }*/

    }
}