package com.example.assignment.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.MainActivity
import com.example.assignment.MainViewModel
import com.example.assignment.R
import com.example.assignment.adapters.ProductAdapter
import com.example.assignment.models.Post
import com.example.assignment.resource.Resource
import kotlinx.android.synthetic.main.fragment_products.*

class ProductDetails : Fragment(R.layout.fragment_products){
    lateinit var viewModel: MainViewModel
    lateinit var productAdapter: ProductAdapter
    val TAG = "ProductDetailsFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        productAdapter.setOnItemClickListener {
            var bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(R.id.action_products_to_description, bundle)
        }

        val myPost = Post(1, 20)
        viewModel.pushPost(myPost)
        viewModel.newDetails.observe(viewLifecycleOwner, Observer { response ->
            // Log.d("Main", "${response}")
            productAdapter.differ.submitList(response.body()?.data?.productList)

        })
    }

        


    var isLoading = false
    var isLastPage = false
    var isScrolling = false


    private fun setUpRecyclerView(){
        productAdapter = ProductAdapter()
        allProducts.apply {
            adapter = productAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

}
