package com.example.shakeitup.core.model

import com.google.gson.annotations.SerializedName

class ResponseWrapper<T>(
    @SerializedName("drinks") val drinks: List<T>
)