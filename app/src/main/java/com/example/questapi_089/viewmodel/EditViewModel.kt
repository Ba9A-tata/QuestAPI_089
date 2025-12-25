package com.example.questapi_089.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.questapi_089.modeldata.DetailSiswa
import com.example.questapi_089.modeldata.UIStateSiswa
import com.example.questapi_089.modeldata.toDataSiswa
import com.example.questapi_089.modeldata.toUiStateSiswa
import com.example.questapi_089.repositori.RepositoryDataSiswa
import com.example.questapi_089.uicontroller.route.DestinasiDetail
import com.example.questapi_089.uicontroller.route.DestinasiEdit
import kotlinx.coroutines.launch
import retrofit2.Response

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryDataSiswa: RepositoryDataSiswa
) : ViewModel() {

    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private set

    private val idSiswa: Int =
        checkNotNull(savedStateHandle[DestinasiEdit.itemIdArg])

    init {
        viewModelScope.launch {
            uiStateSiswa = repositoryDataSiswa
                .getSatuSiswa(idSiswa)
                .toUiStateSiswa(true)
        }
    }

    fun updateUiState(detailsSiswa: DetailSiswa) {
        uiStateSiswa = UIStateSiswa(
            detailSiswa = detailsSiswa,
            isEntryValid = validasiInput(detailsSiswa)
        )
    }

    private fun validasiInput(
        uiState: DetailSiswa = uiStateSiswa.detailSiswa
    ): Boolean {
        return uiState.nama.isNotBlank()
                && uiState.alamat.isNotBlank()
                && uiState.telpon.isNotBlank()
    }

    suspend fun editSatuSiswa() {
        if (validasiInput()) {
            repositoryDataSiswa.editSatuSiswa(
                idSiswa,
                uiStateSiswa.detailSiswa.toDataSiswa()
            )
        }
    }
}
