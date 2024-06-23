package com.dhruv.retrofit1.networking

import com.dhruv.retrofit1.model.Pincode
import retrofit2.http.GET

interface ApiService {

    @GET("api.php?action=seach_pincode")
    suspend fun getPincodeData() : List<Pincode>

}