package com.example.template.common.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PaginationResponse <ResponseType>(

    @SerializedName("count")
    @Expose
    var productsCount: Int,

    @SerializedName("next")
    @Expose
    var nextPage: String,

    @SerializedName("previous")
    @Expose
    var previousPage: String,

    @SerializedName("results")
    @Expose
    var results: List<ResponseType>

)