package com.example.questapi_089.viewmodel

import com.example.questapi_089.modeldata.DataSiswa

sealed interface StatusUiSiswa{
    data class Success(val siswa: List<DataSiswa>) : StatusUiSiswa
    object Error : StatusUiSiswa
    object Loading : StatusUiSiswa
}