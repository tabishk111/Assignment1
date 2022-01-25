package com.example.assignment.models

import com.google.gson.annotations.SerializedName

data class FilterBy(
    @SerializedName("productMinPrice" ) val productMinPrice : String?           = null,
    @SerializedName("productMaxPrice" ) val productMaxPrice : String?           = null,
    @SerializedName("brandIds"        ) val brandIds        : ArrayList<String> = arrayListOf(),
    @SerializedName("categoryIds"     ) val categoryIds     : ArrayList<String> = arrayListOf(),
    @SerializedName("subCategoryIds"  ) val subCategoryIds  : ArrayList<String> = arrayListOf(),
    @SerializedName("sizes"           ) val sizes           : ArrayList<String> = arrayListOf(),
    @SerializedName("colorNames"      ) val colorNames      : ArrayList<String> = arrayListOf()
)