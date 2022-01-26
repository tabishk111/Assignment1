package com.example.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.assignment.MainActivity
import com.example.assignment.MainViewModel
import com.example.assignment.R
import kotlinx.android.synthetic.main.fragment_description.*

class Description : Fragment(R.layout.fragment_description) {
    lateinit var viewModel: MainViewModel
    val args: DescriptionArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        val article = args.article
        Glide.with(this).load(article.productImage.thumbnailImage).into(imgProductImage)
        txtProductName.text = article.productName
        txtProductBrand.text = article.brandName
        var price = "${article.maxRetailPrice}   ${article.discount} off"
        var discountedPrice = "${article.maxRetailPrice - article.discount}"
        txtProductPrice.text = price
        txtProductDiscountedPrice.text = discountedPrice
        txtProductRating.text = article.rating.toString()
        txtAboutTheProductStatic.text = article.productShortDesc
        txtProductDesc.text = article.productDesc
    }
}