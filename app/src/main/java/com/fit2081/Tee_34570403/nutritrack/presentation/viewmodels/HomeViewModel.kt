package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fit2081.Tee_34570403.nutritrack.data.AuthManager
import com.fit2081.Tee_34570403.nutritrack.data.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(context: Context) : ViewModel()  {

    /**
     * Attributes for Home View Model
     */
    val repository = PatientRepository(context = context)

    val userID = AuthManager.getUserId()!!

    var patientName by mutableStateOf("")
        private set

    var totalScore by mutableStateOf(0.0)
        private set

    /**
     * Functions for Home View Model
     */
    private fun loadDataForHomeScreen(){
        viewModelScope.launch(Dispatchers.IO){
            val patientData = repository.getPatientByUserId(userID)
            patientName = patientData.name ?: "${userID}"
            totalScore = patientData.totalHEIFAScore
        }
    }

    init{
        loadDataForHomeScreen()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class HomeViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            HomeViewModel(context) as T
    }
}