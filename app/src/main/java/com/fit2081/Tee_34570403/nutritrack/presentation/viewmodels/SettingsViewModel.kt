package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fit2081.Tee_34570403.nutritrack.data.AuthManager
import com.fit2081.Tee_34570403.nutritrack.data.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Settings View Model
class SettingsViewModel(context :Context) : ViewModel() {

    /**
     * Attributes for Settings View Model
     */
    val patientRepository = PatientRepository(context = context)

    val userID = AuthManager.getUserId()!!

    var patientName by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var newPatientName by mutableStateOf("")
        private set

    var newPhoneNumber by mutableStateOf("")
        private set

    var patientCurrPassword by mutableStateOf("")

    var oldPassword by mutableStateOf("")
        private set

    var newPassword by mutableStateOf("")
        private set

    var confirmNewPassword by mutableStateOf("")
        private set

    var correctNewPatientName by mutableStateOf(true)
        private set

    var correctNewPhoneNumber by mutableStateOf(true)
        private set

    var correctOldPassword by mutableStateOf(true)
        private set

    var correctConfirmNewPassword by mutableStateOf(true)
        private set

    var oldPassVisibility by mutableStateOf(false)
        private set

    var newPassVisibility by mutableStateOf(false)
        private set

    var confirmNewPassVisibility by mutableStateOf(false)
        private set

    /**
     * Functions for Settings View Model
     */
    fun updateNewPatientName(updatedPatientName :String){
        newPatientName = updatedPatientName
        if(newPatientName.isNotEmpty()){
            correctNewPatientName = true
        }
    }

    fun updateNewPhoneNumber(updatedPhoneNumber :String){
        newPhoneNumber = updatedPhoneNumber
        if (newPhoneNumber.isNotEmpty() && (newPhoneNumber.count() in 10..15)){
            correctNewPhoneNumber = true
        }
    }

    fun updateOldPassword(updatedPassword :String){
        oldPassword = updatedPassword
    }

    fun updateNewPassword(updatedPassword :String){
        newPassword = updatedPassword
    }

    fun updateConfirmNewPassword(updatedPassword :String){
        confirmNewPassword = updatedPassword
    }

    fun updateOldPasswordVisible(newVisibility :Boolean){
        oldPassVisibility = newVisibility
    }

    fun updateNewPasswordVisible(newVisibility: Boolean){
        newPassVisibility = newVisibility
    }

    fun updateConfirmNewPasswordVisible(newVisibility: Boolean){
        confirmNewPassVisibility = newVisibility
    }

    fun changePatientDetailsValidation(){
        correctNewPatientName = newPatientName.isNotEmpty()
        correctNewPhoneNumber = newPhoneNumber.isNotEmpty() && (newPhoneNumber.count() in 10..15) && newPhoneNumber.isDigitsOnly()
    }

    fun changePasswordValidation(){
        correctOldPassword = (oldPassword == patientCurrPassword) && oldPassword.isNotEmpty()
        correctConfirmNewPassword = (newPassword == confirmNewPassword) && newPassword.isNotEmpty() && confirmNewPassword.isNotEmpty()
    }

    fun removeCurrentUser(){
        viewModelScope.launch(Dispatchers.IO){
            patientRepository.clearCurrentUser(userID)
        }
        AuthManager.logout()
    }

    fun setNewPatientDetails(){
        viewModelScope.launch(Dispatchers.IO){
            patientRepository.setNewName(userID, newPatientName)
            patientRepository.setNewPhoneNumber(userID, newPhoneNumber)
        }
        patientName = newPatientName
        phoneNumber = newPhoneNumber
    }

    fun setNewPassword(){
        viewModelScope.launch(Dispatchers.IO){
            patientRepository.setNewPassword(userID, newPassword)
        }
        patientCurrPassword = newPassword
    }

    private fun loadDataForSettings(){
        viewModelScope.launch(Dispatchers.IO){
            val patientData = patientRepository.getPatientByUserId(userID)
            patientName = patientData.name ?: "Rosie Huxford"
            newPatientName = patientData.name ?: "Rosie Huxford"
            phoneNumber = patientData.phoneNumber
            newPhoneNumber = patientData.phoneNumber
            patientCurrPassword = patientData.password.toString()
        }
    }

    init{
        loadDataForSettings()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class SettingsViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            SettingsViewModel(context) as T
    }
}