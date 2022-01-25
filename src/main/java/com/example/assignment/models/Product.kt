package com.example.assignment.models

import com.example.assignment.models.Color
import com.example.assignment.models.ProductImage
import java.io.Serializable

data class Product(
    val brandName: String,
    val colorList: List<Color>,
    val discount: Double,
    val discountAvailable: Boolean,
    val discountPrice: Double,
    val maxRetailPrice: Double,
    val newArrival: Boolean,
    val productCode: String,
    val productDesc: String,
    val productId: Int,
    val productImage: ProductImage,
    val productName: String,
    val productShortDesc: String,
    val rating: Double,
    val review: Int,
    val sellingPrice: Double,
    val sizeList: List<String>,
    val sku: String,
    val stockAvailable: Boolean,
    val wishlist: Boolean
) : Serializable