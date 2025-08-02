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

class InsightsViewModel (context : Context): ViewModel() {

    /**
     * Attributes for Insights View Model
     */
    val repository = PatientRepository(context = context)

    val userID = AuthManager.getUserId()!!

    var patientName by mutableStateOf("")
        private set

    var discretionaryScore by mutableStateOf(0.0)
        private set

    var vegetableScore by mutableStateOf(0.0)
        private set

    var fruitScore by mutableStateOf(0.0)
        private set

    var cerealScore by mutableStateOf(0.0)
        private set

    var wholeGrainScore by mutableStateOf(0.0)
        private set

    var meatScore by mutableStateOf(0.0)
        private set

    var dairyScore by mutableStateOf(0.0)
        private set

    var sodiumScore by mutableStateOf(0.0)
        private set

    var alcoholScore by mutableStateOf(0.0)
        private set

    var waterScore by mutableStateOf(0.0)
        private set

    var sugarScore by mutableStateOf(0.0)
        private set

    var saturatedFatScore by mutableStateOf(0.0)
        private set

    var unsaturatedFatScore by mutableStateOf(0.0)
        private set

    var totalScore by mutableStateOf(0.0)
        private set

    /**
     * Functions for Insights View Model
     */
    fun loadDataForInsightsScreen(){
        viewModelScope.launch(Dispatchers.IO){
            val patientData = repository.getPatientByUserId(userID)
            patientName = patientData.name ?: "${userID}"
            discretionaryScore = patientData.discretionaryHEIFAScore
            vegetableScore = patientData.vegetableHEIFAScore
            fruitScore = patientData.fruitHEIFAScore
            cerealScore = patientData.grainsNCerealsHEIFAScore
            wholeGrainScore = patientData.wholeGrainsHEIFAScore
            meatScore = patientData.meatNAlternativesHEIFAScore
            dairyScore = patientData.dairyNAlternativesHEIFAScore
            sodiumScore = patientData.sodiumHEIFAScore
            alcoholScore = patientData.alcoholHEIFAScore
            waterScore = patientData.waterHEIFAScore
            sugarScore = patientData.sugarHEIFAScore
            saturatedFatScore = patientData.saturatedFatHEIFAScore
            unsaturatedFatScore = patientData.unsaturatedFatHEIFAScore
            totalScore = patientData.totalHEIFAScore
        }
    }

    fun getFoodCategoryMap(): Map<String, Double>{
        val foodCategory = mapOf(
            "Discretionary Foods" to discretionaryScore,
            "Vegetables" to vegetableScore,
            "Fruits" to fruitScore,
            "Grains and Cereals" to cerealScore,
            "Whole Grains" to wholeGrainScore,
            "Meat & Alternatives" to meatScore,
            "Dairy" to dairyScore,
            "Sodium" to sodiumScore,
            "Alcohol" to alcoholScore,
            "Water" to waterScore,
            "Sugar" to sugarScore,
            "Saturated Fats" to saturatedFatScore,
            "Unsaturated Fats" to unsaturatedFatScore,
        )
        return foodCategory
    }

    fun checkCategoryForMaxVal(category: String): Int {
        if (
            category == "Grains and Cereals"
            || category == "Whole Grains"
            || category == "Water"
            || category == "Alcohol"
            || category == "Saturated Fats"
            || category == "Unsaturated Fats"
        )
            return 5
        else
            return 10
    }

    init{
        loadDataForInsightsScreen()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class InsightsViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            InsightsViewModel(context) as T
    }
}