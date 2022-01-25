package com.example.assignment

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.assignment.repository.ProductRepository

class MainViewModelProviderFactory(val app: Application,val productRepository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app, productRepository) as T
    }
}