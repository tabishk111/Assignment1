package com.example.assignment.models

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("pageNo"      ) val pageNo      : Int?      = null,
    @SerializedName("pageSize"    ) val pageSize    : Int?      = null,
    @SerializedName("searchValue" ) val searchValue : String?   = null,
    @SerializedName("filterBy"    ) val filterBy    : FilterBy? = FilterBy()
)
/*
* “pageNo”: 1,
“pageSize”: 20,
“searchValue”: “”,
“filterBy”: {
“productMinPrice”: null,
“productMaxPrice”: null,
“brandIds”: [],
“categoryIds”: [],
“subCategoryIds”: [],
“sizes”: [],
*}
*
* */