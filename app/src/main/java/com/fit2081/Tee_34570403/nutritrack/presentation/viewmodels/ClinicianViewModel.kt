package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fit2081.Tee_34570403.nutritrack.data.network.NetworkConnection
import com.fit2081.Tee_34570403.nutritrack.data.repositories.ClinicianRepository
import com.fit2081.Tee_34570403.nutritrack.presentation.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClinicianViewModel(context: Context) :ViewModel() {

    /**
     * Attributes for Clinician View Model
     */
    var clinicianRepository = ClinicianRepository(context = context)

    var clinicianKey by mutableStateOf("")
        private set

    var correctClinicianKey by mutableStateOf(true)
        private set

    var avgHEIFAMaleScore by mutableStateOf(0.0)
        private set

    var avgHEIFAFemaleScore by mutableStateOf(0.0)
        private set

    var networkConnection = NetworkConnection(context)

    var analysisPrompt by mutableStateOf("")
        private set

    var mutableOutputMap by mutableStateOf(mapOf("" to ""))
        private set

    var clinicKeyVisibility by mutableStateOf(false)
        private set

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)

    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()


    /**
     * Functions for Clinician View Model
     */
    fun updateClinicianKey(newClinicianKey: String){
        clinicianKey = newClinicianKey
    }

    fun updateClinicKeyVisible(newVisibility :Boolean){
        clinicKeyVisibility = newVisibility
    }

    fun authoriseClinicianLogin(){
        if (clinicianKey == "dollar-entry-apples") {
            correctClinicianKey = true
        } else{
            correctClinicianKey = false
        }
    }

    fun loadDataForClinicianScreen(){
        viewModelScope.launch(Dispatchers.IO){
            avgHEIFAMaleScore = clinicianRepository.getAvgHEIFAMaleScore()
            avgHEIFAFemaleScore = clinicianRepository.getAvgHEIFAFemaleScore();
        }
    }

    fun getPrompt(){
        CoroutineScope(Dispatchers.IO).launch{
            val prompt =
                """

                """
            analysisPrompt = prompt
        }
    }

    fun sendPrompt() {
        _uiState.value = UiState.Loading

        getPrompt()

        CoroutineScope(Dispatchers.IO).launch {
            if (!networkConnection.isNetworkAvailable()){
                _uiState.value = UiState.Error("No Internet Connection")
                return@launch
            }
            try {
                val response = clinicianRepository.analyseHealthData()
                response.text?.let { outputContent ->
                    val titleTOContent = emptyMap<String, String>().toMutableMap()
                    outputContent.split("\n\n").forEach { insightStr ->
                        val split_lst = insightStr.split(':')
                        val title = split_lst[0].trim().trim('-').trim()
                        val content = split_lst[1].trim()
                        titleTOContent[title] = content
                    }
                    mutableOutputMap = titleTOContent
                    _uiState.value = UiState.Success(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }

    init {
        loadDataForClinicianScreen()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class ClinicianViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            ClinicianViewModel(context) as T
    }
}