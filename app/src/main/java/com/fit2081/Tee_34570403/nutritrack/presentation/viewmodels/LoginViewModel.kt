package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.fit2081.Tee_34570403.nutritrack.data.AuthManager
import com.fit2081.Tee_34570403.nutritrack.data.models.FoodIntake
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip
import com.fit2081.Tee_34570403.nutritrack.data.models.Patient
import com.fit2081.Tee_34570403.nutritrack.data.repositories.FoodIntakeRepository
import com.fit2081.Tee_34570403.nutritrack.data.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel(context: Context) : ViewModel() {

    /**
     * Attributes for Login View Model
     */
    val repository = PatientRepository(context = context)

    val foodIntakeRepository = FoodIntakeRepository(context = context)

    val loginPlaceholder = "012345".toInt()

    /**
     * Private mutable state flow that stores the current list of posts.
     * Using StateFlow provides a way to observe changes to the data over time.
     */
    var loginUserId by mutableStateOf(loginPlaceholder)
        private set

    var registerUserId by mutableStateOf(loginPlaceholder)
        private set

    var forgotPasswordUserId by mutableStateOf(loginPlaceholder)
        private set

    var name by mutableStateOf("")
        private set

    var phoneNumber by mutableStateOf("")
        private set

    var forgotPasswordPhoneNumber by mutableStateOf("")
        private set

    var loginPassword by mutableStateOf("")
        private set

    var registerPassword by mutableStateOf("")
        private set

    var forgotPasswordNewPassword by mutableStateOf("")
        private set

    var confirmRegisterPassword by mutableStateOf("")
        private set

    var confirmForgotPasswordNewPassword by mutableStateOf("")
        private set

    var loginIDOptionsExpanded by mutableStateOf(false)
        private set

    var registerIDOptionsExpanded by mutableStateOf(false)
        private set

    var forgotPasswordIDOptionsExpanded by mutableStateOf(false)
        private set

    var correctLoginUserId by mutableStateOf(true)
        private set

    var correctRegisterUserId by mutableStateOf(true)
        private set

    var correctForgotPasswordUserId by mutableStateOf(true)
        private set

    var correctLoginPassword by mutableStateOf(true)
        private set

    var correctPhoneNumber by mutableStateOf(true)
        private set

    var correctForgotPasswordPhoneNumber by mutableStateOf(true)

    var correctConfirmRegisterPassword by mutableStateOf(true)
        private set

    var correctConfirmForgotPassNewPassword by mutableStateOf(true)

    var accountRegistered by mutableStateOf(true)
        private set

    var notEmptyName by mutableStateOf(true)
        private set

    var loginPassVisibility by mutableStateOf(false)
        private set

    var regPhoneNumVisibility by mutableStateOf(false)
        private set

    var regPassVisibility by mutableStateOf(false)
        private set

    var regConPassVisibility by mutableStateOf(false)
        private set

    var forPhoneNumberVisibility by mutableStateOf(false)
        private set

    var forPassVisibility by mutableStateOf(false)
        private set

    var forConPassVisibility by mutableStateOf(false)
        private set

    val _registeredUserIds = MutableLiveData<List<Int>>()

    val registeredUserIds: LiveData<List<Int>> = _registeredUserIds

    val _unregisteredUserIds = MutableLiveData<List<Int>>()

    val unregisteredUserIds: LiveData<List<Int>> = _unregisteredUserIds

    /**
     * Functions for Login View Model
     */
    fun updateLoginUserId(newLoginUserId: Int) {
        loginUserId = newLoginUserId
    }

    fun updateForgotPassUserId(newUserId: Int) {
        forgotPasswordUserId = newUserId
    }

    fun updateRegisterUserId(newRegisterUserId: Int) {
        registerUserId = newRegisterUserId
    }

    fun updateName(newName: String) {
        name = newName
    }

    fun updatePhoneNumber(newPhoneNumber: String) {
        phoneNumber = newPhoneNumber
    }

    fun updateForgotPassPhoneNumber(newPhoneNumber: String) {
        forgotPasswordPhoneNumber = newPhoneNumber
    }

    fun updateLoginIDOptionsExpanded(newLoginIDOptionsExpanded: Boolean) {
        loginIDOptionsExpanded = newLoginIDOptionsExpanded
    }

    fun updateRegisterIDOptionsExpanded(newRegisterIDOptionsExpanded: Boolean) {
        registerIDOptionsExpanded = newRegisterIDOptionsExpanded
    }

    fun updateForgotPassIDOptionsExpanded(newIDOptionsExpanded: Boolean) {
        forgotPasswordIDOptionsExpanded = newIDOptionsExpanded
    }

    fun updateCorrectLoginUserId(newCorrectLoginUserId: Boolean) {
        correctLoginUserId = newCorrectLoginUserId
    }

    fun updateCorrectRegisterUserId(newCorrectRegisterUserId: Boolean) {
        correctRegisterUserId = newCorrectRegisterUserId
    }

    fun updateCorrectForgotPassUserId(newUserId: Boolean) {
        correctForgotPasswordUserId = newUserId
    }

    fun updateLoginPassword(newLoginPassword: String) {
        loginPassword = newLoginPassword
    }

    fun updateRegisterPassword(newRegisterPassword: String) {
        registerPassword = newRegisterPassword
    }

    fun updateForgotPassNewPass(newPassword: String) {
        forgotPasswordNewPassword = newPassword
    }

    fun updateConfirmRegisterPassword(newConfirmRegisterPassword: String) {
        confirmRegisterPassword = newConfirmRegisterPassword
    }

    fun updateForgotPassConfirmNewPass(newConfirmPassword: String) {
        confirmForgotPasswordNewPassword = newConfirmPassword
    }

    fun updateCorrectLoginPassword(newCorrectLoginPassword: Boolean) {
        correctLoginPassword = newCorrectLoginPassword
    }

    fun updateCorrectPhoneNumber(newCorrectPhoneNumber: Boolean) {
        correctPhoneNumber = newCorrectPhoneNumber
    }

    fun updateCorrectForgotPassPhoneNum(newCorrectPhoneNumber: Boolean) {
        correctForgotPasswordPhoneNumber = newCorrectPhoneNumber
    }

    fun updateCorrectConfirmRegisterPassword(newCorrectConfirmRegisterPassword: Boolean) {
        correctConfirmRegisterPassword = newCorrectConfirmRegisterPassword
    }

    fun updateCorrectConfirmForgotPassword(newCorrectConfirmForgotPassword: Boolean) {
        correctConfirmForgotPassNewPassword = newCorrectConfirmForgotPassword
    }

    fun updateAccountRegistered(newAccountRegistered: Boolean) {
        accountRegistered = newAccountRegistered
    }

    fun updateNotEmptyName(newNotEmptyName: Boolean) {
        notEmptyName = newNotEmptyName
    }

    fun updateLoginPassVisible(newVisibility :Boolean){
        loginPassVisibility = newVisibility
    }

    fun updateRegPhoneNumVisible(newVisibility :Boolean){
        regPhoneNumVisibility = newVisibility
    }

    fun updateRegPassVisible(newVisibility :Boolean){
        regPassVisibility = newVisibility
    }

    fun updateRegConPassVisible(newVisibility :Boolean){
        regConPassVisibility = newVisibility
    }

    fun updateForPhoneNumVisible(newVisibility :Boolean){
         forPhoneNumberVisibility = newVisibility
    }

    fun updateForPassVisible(newVisibility :Boolean){
        forPassVisibility = newVisibility
    }

    fun updateForConPassVisible(newVisibility :Boolean){
        forConPassVisibility = newVisibility
    }


    fun filledQuestionnaire() :Boolean{
        val userId = AuthManager.getUserId() ?: return false

        val filledQuestionnaire = mutableStateOf<FoodIntake?>(null)

        runBlocking {
            try {
                filledQuestionnaire.value  = foodIntakeRepository.getFoodIntakeByUserId(userId)

            } catch (e: Exception) {
                e.printStackTrace()
                filledQuestionnaire.value = null
            }
        }
        return filledQuestionnaire.value != null
    }

    fun loadCurrentUser(){
        val userLst = mutableStateOf<List<Int>>(emptyList())
        runBlocking {
            userLst.value = repository.getCurrentUser()
        }
        if (userLst.value.count() == 1){
            AuthManager.login(userLst.value[0])
            return
        }
        else if (userLst.value.count() > 1) {
            viewModelScope.launch {
                repository.clearCurrentUsers()
            }
            return
        }
    }

    fun clearCurrentUsers(){
        viewModelScope.launch {
            repository.clearCurrentUsers()
        }
    }

    fun currentUserPresent() :Boolean{
        return AuthManager.getUserId() != null
    }

    fun loadAllRegUserIds() {
        viewModelScope.launch {
            _registeredUserIds.postValue(repository.getAllRegisteredUserIDs())
        }
    }

    fun loadAllUnRegUserIds() {
        viewModelScope.launch {
            _unregisteredUserIds.postValue(repository.getAllUnregisteredUserIDs())
        }
    }

    /**
     * Retrieves a user from the database by their ID.
     *
     * This is a suspend function and should be called within
     * a coroutine or another suspend function.
     *
     * @param userId The ID of the user to retrieve.
     * @return The [Patient] object with the specified ID, or null if no such user exists.
     */
    suspend fun getPatientByUserId(userId: Int): Patient {
        return repository.getPatientByUserId(userId)
    }

    /**
     * Validates user credentials against the database
     * @param userId The user ID to authenticate
     * @param password The password to validate
     * @param viewModel ViewModel to access user data
     */
    fun authoriseLogin() {
        var aPatient: Patient

        // Use blocking call to retrieve user by ID synchronously
        runBlocking {
            var aFlowPatient: Patient = getPatientByUserId(loginUserId)
            // Commented code: if(aFlowStudent==null) return false
            aPatient = aFlowPatient
        }

        // Authentication checks
        val validUserId = if (aPatient != null && loginUserId == aPatient.userId) true else false // Student ID doesn't exist
        val nonEmptyName = if (aPatient!= null && !name.isNullOrBlank()) true else false
        val validPassword = if (aPatient != null && aPatient.password == loginPassword) true else false // Password doesn't match

        updateCorrectLoginUserId(validUserId)
        updateNotEmptyName(nonEmptyName)
        updateCorrectLoginPassword(validPassword)
    }

    /**
     * Validates user credentials against the database
     * @param userId The user ID to authenticate
     * @param password The password to validate
     * @param viewModel ViewModel to access user data
     */
    fun authoriseRegistration() {
        var aPatient: Patient

        // Use blocking call to retrieve user by ID synchronously
        runBlocking {
            var aFlowPatient: Patient = getPatientByUserId(registerUserId)
            // Commented code: if(aFlowStudent==null) return false
            aPatient = aFlowPatient
        }

        // Authentication checks
        val validUserId = if (aPatient != null && registerUserId == aPatient.userId) true else false // Student ID doesn't exist
        val validPhoneNumber = if(validUserId && aPatient.phoneNumber == phoneNumber)  true else false// Phone number doesn't match
        val accountRegistered = if (validUserId && aPatient.password != null) true else false
        val validPassword = if (validUserId && registerPassword.isNotBlank() && confirmRegisterPassword.isNotBlank() && registerPassword == confirmRegisterPassword) true else false // Password doesn't match

        updateCorrectRegisterUserId(validUserId)
        updateCorrectPhoneNumber(validPhoneNumber)
        updateAccountRegistered(accountRegistered)
        updateCorrectConfirmRegisterPassword(validPassword)
    }

    /**
     * Validates user credentials against the database
     * @param userId The user ID to authenticate
     * @param password The password to validate
     * @param viewModel ViewModel to access user data
     */
    fun authoriseForgotPassword() {
        var aPatient: Patient

        // Use blocking call to retrieve user by ID synchronously
        runBlocking {
            var aFlowPatient: Patient = getPatientByUserId(forgotPasswordUserId)
            // Commented code: if(aFlowStudent==null) return false
            aPatient = aFlowPatient
        }

        // Authentication checks
        val validUserId = if (aPatient != null && forgotPasswordUserId == aPatient.userId) true else false // Student ID doesn't exist
        val validPhoneNumber = if(validUserId && aPatient.phoneNumber == forgotPasswordPhoneNumber)  true else false// Phone number doesn't match
        val validPassword = if (validUserId && forgotPasswordNewPassword.isNotBlank() && confirmForgotPasswordNewPassword.isNotBlank() && forgotPasswordNewPassword == confirmForgotPasswordNewPassword) true else false // Password doesn't match

        updateCorrectForgotPassUserId(validUserId)
        updateCorrectForgotPassPhoneNum(validPhoneNumber)
        updateCorrectConfirmForgotPassword(validPassword)
    }

    fun setCurrentUser(login: Boolean){
        val userId = if (login) loginUserId else registerUserId
        AuthManager.login(userId)
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCurrentUsers()
            repository.setCurrentUser(userId)
        }
    }

    fun setCurrentUserForgotPassword(){
        AuthManager.login(forgotPasswordUserId)
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearCurrentUsers()
            repository.setCurrentUser(forgotPasswordUserId)
            repository.setNewPassword(forgotPasswordUserId, forgotPasswordNewPassword)
        }
    }

    fun insertNewDetails(){
        viewModelScope.launch(Dispatchers.IO){
            repository.setNewName(registerUserId, name)
            repository.setNewPassword(registerUserId, registerPassword)
        }
    }

    init {
        loadCurrentUser()
        loadAllRegUserIds()
        loadAllUnRegUserIds()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class LoginViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            LoginViewModel(context) as T
    }
}