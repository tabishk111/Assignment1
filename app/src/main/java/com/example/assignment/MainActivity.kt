package com.example.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.repository.ProductRepository

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val productRepository = ProductRepository()
        val viewModelProviderFactory = MainViewModelProviderFactory(application, productRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(MainViewModel::class.java)
    }
}