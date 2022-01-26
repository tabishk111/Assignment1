package com.example.assignment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.assignment.models.Post
import com.example.assignment.repository.ProductRepository
import com.example.assignment.resource.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import com.example.assignment.models.ResponseData
import java.io.IOException

class MainViewModel(app: Application,val productRepository: ProductRepository) : AndroidViewModel(app) {

    var myResponse: MutableLiveData<Response<ResponseData>> = MutableLiveData()
    val newDetails: MutableLiveData<Response<ResponseData>> = MutableLiveData()
    fun pushPost(post: Post) {
        viewModelScope.launch {
            val response = productRepository.getDetails(post)
            newDetails.value = response
        }
    }



    val productDetails: MutableLiveData<Resource<ResponseData>> = MutableLiveData()
    var productDetailsPage = 1;

    var productDetailsResponse: ResponseData? = null

    val mypost = Post(1,20)

    init {

        getProductDetails(mypost)
    }

    fun getProductDetails(post: Post) = viewModelScope.launch {
        safeProductDetailsCall(post)

    }

    private suspend fun safeProductDetailsCall(post: Post){
        productDetails.postValue(Resource.Loading())
        try {
            if (hasInternetConnection()) {
                val response = productRepository.getDetails(post)
                productDetails.postValue(handleProductDetailsResponse(response))
            }else{
                productDetails.postValue(Resource.Error("No Internet Connection"))
            }
        }
        catch (t: Throwable){
            when(t){
                is IOException-> productDetails.postValue(Resource.Error("Network Failure"))
                else-> productDetails.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleProductDetailsResponse(response: Response<ResponseData>): Resource<ResponseData> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                productDetailsPage++
                if (productDetailsResponse==null){
                    productDetailsResponse = resultResponse
                }
                else{
                    val oldArticles = productDetailsResponse?.data?.productList
                    val newArticles = productDetailsResponse!!.data.productList
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(productDetailsResponse?:resultResponse)}
        }
        return Resource.Error(response.message())
    }

    private fun hasInternetConnection():Boolean{
        val connectivityManager = getApplication<ProductApplication>().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            val activeNework = connectivityManager.activeNetwork?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNework)?: return false
            return when{
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)->true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)->true
                else-> false
            }
        }
        else{
            connectivityManager.activeNetworkInfo?.run{
                return when(type){
                    ConnectivityManager.TYPE_WIFI ->true
                    ConnectivityManager.TYPE_MOBILE ->true
                    ConnectivityManager.TYPE_ETHERNET ->true
                    else -> false
                }
            }
        }
        return false

    }

}