package com.example.questapi_089.apiservice

import retrofit2.http.GET
import retrofit2.http.POST

interface  ServiceApiSiswa {

    @GET("bacaTeman.php")
    suspend fun getSiswa() ; List<DataSiswa>


}