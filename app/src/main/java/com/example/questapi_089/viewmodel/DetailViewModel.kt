package com.example.questapi_089.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.questapi_089.modeldata.DataSiswa
import com.example.questapi_089.repositori.RepositoryDataSiswa
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

sealed interface StatusUIDetail {
    data class Success(val satusiswa: DataSiswa) : StatusUIDetail
    object Error : StatusUIDetail
    object Loading : StatusUIDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])
    var statusUIDetail: StatusUIDetail by mutableStateOf(StatusUIDetail.Loading)
        private set

    init {
        getSatuSiswa()
    }

    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUIDetail = StatusUIDetail.Loading
            statusUIDetail = try {
                StatusUIDetail.Success(satusiswa = repositoryDataSiswa.getSatuSiswa(idSiswa))
            } catch (e: IOException) {
                StatusUIDetail.Error
            } catch (e: HttpException) {
                StatusUIDetail.Error
            }
        }
    }

    @SuppressLint("SuspiciousIndentation")
    suspend fun hapusSatuSiswa() {
        val resp: Response<Void> = repositoryDataSiswa.hapusSatuSiswa(idSiswa)

        if (resp.isSuccessful) {
            println("Sukses Hapus Data : ${resp.message()}")
        } else {
            println("Gagal Hapus Data : ${resp.errorBody()}")
        }
    }
}