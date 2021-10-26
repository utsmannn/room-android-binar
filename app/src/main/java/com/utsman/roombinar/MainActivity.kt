package com.utsman.roombinar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.utsman.roombinar.entity.Product
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val TAG = "LOG_MAIN_DATABASE"

    private val tvResult: TextView by lazy { findViewById(R.id.tv_result) }
    private val etProduct: EditText by lazy { findViewById(R.id.et_product) }
    private val btnAddProduct: Button by lazy { findViewById(R.id.btn_add_product) }
    private val btnGetProduct: Button by lazy { findViewById(R.id.btn_get_product) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val databaseHelper = DatabaseHelper(this)

        btnAddProduct.setOnClickListener {
            val productName = etProduct.text.toString()
            val product = Product(
                id = null,
                name = productName,
                stock = 1,
                color = "green"
            )

            databaseHelper.addProduct(product)
        }

        btnGetProduct.setOnClickListener {
            MainScope().launch {
                val dataProduct = databaseHelper.getProducts()
                val dataProductById = databaseHelper.getProductById(3)

                val productCount = dataProduct.size
                tvResult.text = dataProductById.name

                Log.d(TAG, "onCreate: $dataProduct")
            }
        }

    }
}