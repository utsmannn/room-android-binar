package com.utsman.roombinar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.utsman.roombinar.entity.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList: MutableList<Product> = mutableListOf()

    inner class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(product: Product) = itemView.run {
            val tvItemTitle: TextView = findViewById(R.id.tv_item_title)
            val tvItemStock: TextView = findViewById(R.id.tv_item_stock)
            val tvItemColor: TextView = findViewById(R.id.tv_item_color)

            tvItemTitle.text = product.name
            tvItemStock.text = "Stock: ${product.stock}"
            tvItemColor.text = "Color: ${product.color}"
        }
    }

    fun addProduct(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = productList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}