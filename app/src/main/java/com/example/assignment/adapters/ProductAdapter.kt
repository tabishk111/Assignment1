package com.example.assignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.assignment.models.Product
import com.example.assignment.R
import kotlinx.android.synthetic.main.fragment_product__preview.view.*

class ProductAdapter :RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.productId==newItem.productId
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem==newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_product__preview,parent,false
        ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.productImage.thumbnailImage).into(imgProductImage)
            txtProductName.text = article.productName
            txtProductBrand.text = article.brandName
            var price = "${article.maxRetailPrice}   ${article.discount} off"
            var p1 = article.maxRetailPrice - article.discount
            var discountedPrice = "${p1}"
            txtProductPrice.text = price
            txtProductDiscountedPrice.text  = discountedPrice
            txtProductRating.text = article.rating.toString()
            setOnClickListener{
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Product) -> Unit)? = null

    fun setOnItemClickListener(listener: (Product) -> Unit){
        onItemClickListener = listener
    }
}