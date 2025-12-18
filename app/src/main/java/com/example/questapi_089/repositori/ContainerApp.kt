package com.example.questapi_089.repositori

import okhttp3.logging.HttpLoggingInterceptor

interface ContainerApp{
    val repositorySiswa: RepositoryDataSiswa
}


class DefaultContainerApp : ContainerApp{
    private val baseurl = "http://10.0.2.2/umyTI/"

    val logging = HttpLoggingInterceptor().apply {
        level= HttpLoggingInterceptor.Level.BODY

    }
}