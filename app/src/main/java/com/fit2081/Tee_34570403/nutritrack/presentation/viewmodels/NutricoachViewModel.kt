package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fit2081.Tee_34570403.nutritrack.data.repositories.FruityViceRepository
import com.fit2081.Tee_34570403.nutritrack.presentation.UiState
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import com.fit2081.Tee_34570403.nutritrack.BuildConfig
import com.fit2081.Tee_34570403.nutritrack.data.AuthManager
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip
import com.fit2081.Tee_34570403.nutritrack.data.network.NetworkConnection
import com.fit2081.Tee_34570403.nutritrack.data.repositories.FoodIntakeRepository
import com.fit2081.Tee_34570403.nutritrack.data.repositories.NutricoachRepository
import com.fit2081.Tee_34570403.nutritrack.data.repositories.PatientRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NutricoachViewModel(context: Context) : ViewModel() {

    /**
     * Attributes for Nutricoach View Model
     */
    var nutricoachTipRepo = NutricoachRepository(context)

    var foodIntakeRepo = FoodIntakeRepository(context)

    var networkConnection = NetworkConnection(context)

    var userId = AuthManager.getUserId()!!

    var patientRepo = PatientRepository(context)

    var fruityViceRepo = FruityViceRepository()

    var emptyFruitName by mutableStateOf(true)

    var fruitName by mutableStateOf("")
        private set

    var fruitExist by mutableStateOf(true)
        private set

    var allTipsSelected by mutableStateOf(false)
        private set

    private val _allTips = MutableStateFlow<List<NutriCoachTip>>(emptyList())

    val allTips : StateFlow<List<NutriCoachTip>>
        get() = _allTips.asStateFlow()

    var showAllTipsDialog by mutableStateOf(false)
        private set

    var clearAllTasksDialogExposed by mutableStateOf(false)
        private set

    var optimalFruitIntake by mutableStateOf(true)
        private set

    var nutriCoachPrompt by mutableStateOf("")
        private set

    var nutritionData by mutableStateOf(mapOf(
        "family" to "Musaceae",
        "calories" to "96",
        "fat" to "0.2",
        "sugar" to "17.2",
        "carbohydrates" to "22.0",
        "protein" to "1.0")
    )
        private set

    private val _uiState: MutableStateFlow<UiState> =
        MutableStateFlow(UiState.Initial)

    val uiState: StateFlow<UiState> =
        _uiState.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    /**
     * Functions for Nutricoach View Model
     */
    fun updateFruitName(newFruitName :String){
        fruitName = newFruitName
        if (fruitName == ""){
            emptyFruitName = true
        } else{
            emptyFruitName = false
        }
    }

    fun updateShowAllTipsDialog(newShowAllTipsDialog :Boolean){
        showAllTipsDialog = newShowAllTipsDialog
    }

    fun updateClearAllTasksDialog(newClearAllTasksDialogExposed :Boolean){
        clearAllTasksDialogExposed = newClearAllTasksDialogExposed
        showAllTipsDialog  = false
        showAllTipsDialog = true
    }

    fun obtainFruitInfo(){
        viewModelScope.launch(Dispatchers.IO){
            val nutriDataModel = fruityViceRepo.getFruitInfo(fruitName)
            if (nutriDataModel != null) {
                nutritionData = mapOf(
                    "family" to nutriDataModel.family,
                    "calories" to nutriDataModel.nutritions.calories,
                    "fat" to nutriDataModel.nutritions.fat,
                    "sugar" to nutriDataModel.nutritions.sugar,
                    "carbohydrates" to nutriDataModel.nutritions.carbohydrates,
                    "protein" to nutriDataModel.nutritions.protein,
                )
                fruitExist = true
            }
            else{
                fruitExist = false
            }
        }
    }

    fun getPrompt(){
        viewModelScope.launch(Dispatchers.IO){
            val patientData = patientRepo.getPatientByUserId(userId)
            val patientIntakeData = foodIntakeRepo.getFoodIntakeByUserId(userId)
            val prompt =
                """
                Provide a short but concise advice and motivation (and emojis also) to improve their food intake and nutrition based on their values
                ---------------------------------------------HEIFA Scores----------------------------------------
                Discretionary HEIFA Score: ${patientData.discretionaryServeSize}
                Vegetables HEIFA Score: ${patientData.vegetableHEIFAScore}
                Fruit HEIFA Score: ${patientData.fruitHEIFAScore}
                Grains and Cereals HEIFA Score: ${patientData.grainsNCerealsHEIFAScore}
                Whole Grains HEIFA Score: ${patientData.wholeGrainsHEIFAScore}
                Meat and Alternatives HEIFA Score: ${patientData.meatNAlternativesHEIFAScore}
                Dairy and Alternatives HEIFA Score: ${patientData.dairyNAlternativesHEIFAScore}
                Sodium HEIFA Score: ${patientData.sodiumHEIFAScore}
                Alcohol HEIFA Score: ${patientData.alcoholHEIFAScore}
                Water HEIFA Score: ${patientData.waterHEIFAScore}
                Sugar HEIFA Score: ${patientData.sugarHEIFAScore}
                Saturated Fat HEIFA Score: ${patientData.saturatedFatHEIFAScore}
                Unsaturated Fat HEIFA Score: ${patientData.unsaturatedFatHEIFAScore}
                Total HEIFA Score: ${patientData.totalHEIFAScore}

                -----------------------------Food Intake Questionnaire Result-------------------------------------
                -----------------------------Categories that the Patient can Eat----------------------------------
                Fruits: ${patientIntakeData.fruits}
                Vegetables: ${patientIntakeData.fruits}
                Grains: ${patientIntakeData.fruits}
                Red Meat: ${patientIntakeData.fruits}
                Seafood: ${patientIntakeData.fruits}
                Poultry: ${patientIntakeData.fruits}
                Fish: ${patientIntakeData.fruits}
                Eggs: ${patientIntakeData.fruits}
                Nuts/ Seeds: ${patientIntakeData.fruits}
                ------------------------------Food Preference Persona--------------------------------------------
                Food Preference Persona: ${patientIntakeData.persona}
                ------------------------------Timings (24 Hour Timing Convention)--------------------------------
                Approximate Biggest Meal Time: ${patientIntakeData.biggestMealTime}
                Approximate Sleep Time: ${patientIntakeData.sleepTime}
                Approximate Wake Up: ${patientIntakeData.wakeUpTime}
                """
            nutriCoachPrompt = prompt
        }

    }

    fun selectAll(){
        allTipsSelected = !allTipsSelected
        _allTips.value = _allTips.value.map { tip ->  tip.copy(isSelected = allTipsSelected)}.toMutableStateList()
    }

    fun unselectAll(){
        allTipsSelected = false
        _allTips.value = _allTips.value.map { tip ->  tip.copy(isSelected = allTipsSelected)}.toMutableStateList()
    }

    fun toggleSelection(userId :Int, tip :String){
        _allTips.value = _allTips.value.map {
            if (it.userId == userId && it.motivationTip == tip) it.copy(isSelected = !it.isSelected) else it
        }
    }

    fun sendPrompt() {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            if (!networkConnection.isNetworkAvailable()){
                _uiState.value = UiState.Error("No Internet Connection")
                return@launch
            }
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(nutriCoachPrompt)
                    }
                )
                response.text?.let { outputContent ->
                    _uiState.value = UiState.Success(outputContent)
                    storeTip(outputContent)
                }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "")
            }
        }
    }

    fun storeTip(tip: String){
        val currentDateTime = LocalDateTime.now()
        val formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        val newTip = NutriCoachTip(
            userId,
            tip,
            formattedDateTime,
            false
        )
        viewModelScope.launch(Dispatchers.IO){
            nutricoachTipRepo.insert(newTip)
        }
        _allTips.value += newTip
    }

    fun loadPatientData(){
        viewModelScope.launch(Dispatchers.IO){
            val patientData = patientRepo.getPatientByUserId(userId)
            optimalFruitIntake = patientData.fruitServeSize >= 2 && patientData.fruitVariationScore >= 2
        }
    }

    fun loadAllTips(){
        viewModelScope.launch(Dispatchers.IO){
            _allTips.value = nutricoachTipRepo.getAllTips(userId).toMutableStateList()
        }
    }

    fun deleteTips(){
        val selected_tips = allTips.value.filter { tip ->  tip.isSelected }
        viewModelScope.launch(Dispatchers.IO){
            selected_tips.forEach{ tip ->
                nutricoachTipRepo.deleteTip(tip)
            }
        }
        _allTips.value = allTips.value.filter { tip ->  !tip.isSelected }.toMutableStateList()
    }

    fun countNumSelected() :Int{
        return allTips.value.count { tip -> tip.isSelected }
    }


    // Initialiser for the view model
    init {
        loadPatientData()
        loadAllTips()
        getPrompt()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class NutricoachViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            NutricoachViewModel(context) as T
    }
}
