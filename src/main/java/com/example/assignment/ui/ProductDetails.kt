package com.example.assignment.ui

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment.MainActivity
import com.example.assignment.MainViewModel
import com.example.assignment.R
import com.example.assignment.adapters.ProductAdapter
import com.example.assignment.resource.Resource
import com.example.assignment.models.Response as ResponseData
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
                putSerializable("product",it)
            }
            findNavController().navigate(R.id.action_products_to_description,bundle)
        }

        viewModel.productDetails.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let { productResponse ->
                        productAdapter.differ.submitList(productResponse.data.productList.toList())
                        val totalPages = productResponse.pagination.totalPages / 20 + 2
                        isLastPage = viewModel.productDetailsPage == totalPages
                        if(isLastPage){
                            allProducts.setPadding(0,0,0,0)
                        }

                    }
                }
                is Resource.Error->{
                    hideProgressBar()
                    response.message?.let { message->

                        Toast.makeText(activity,"An Error Occured $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading->{
                    showProgressBar()
                }
            }

        })

    }



    private fun hideProgressBar(){
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar(){
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
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