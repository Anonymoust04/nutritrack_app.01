package com.fit2081.Tee_34570403.nutritrack.presentation.viewmodels

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fit2081.Tee_34570403.nutritrack.data.AuthManager
import com.fit2081.Tee_34570403.nutritrack.data.models.FoodIntake
import com.fit2081.Tee_34570403.nutritrack.data.repositories.FoodIntakeRepository
import com.fit2081.Tee_34570403.nutritrack.data.repositories.PatientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuestionnaireViewModel(context: Context) : ViewModel() {

    /**
     * Attributes for Questionnaire View Model
     */
    var repository = FoodIntakeRepository(context = context)

    var patientRepository = PatientRepository(context = context)

    var userId = AuthManager.getUserId()!!

    var devoteeShowDialog = mutableStateOf(false)
        private set

    var mindfulShowDialog = mutableStateOf(false)
        private set

    var wellnessShowDialog = mutableStateOf(false)
        private set

    var balanceShowDialog = mutableStateOf(false)
        private set

    var procrastinatorShowDialog = mutableStateOf(false)
        private set

    var carefreeShowDialog = mutableStateOf(false)
        private set

    var fruits = mutableStateOf(false)
        private set

    var vegetables = mutableStateOf(false)
        private set

    var grains = mutableStateOf(false)
        private set

    var redMeat = mutableStateOf(false)
        private set

    var seafood = mutableStateOf(false)
        private set

    var poultry = mutableStateOf(false)
        private set

    var fish = mutableStateOf(false)
        private set

    var eggs = mutableStateOf(false)
        private set

    var nutsNSeeds = mutableStateOf(false)
        private set

    var persona = mutableStateOf("")
        private set

    var personaExpansion = mutableStateOf(false)

    var biggestMealTime = mutableStateOf("")
        private set

    var sleepTime = mutableStateOf("")
        private set

    var wakeUpTime = mutableStateOf("")
        private set

    var biggestMealTimeDialogExpose = mutableStateOf(false)
        private set

    var sleepTimeDialogExpose = mutableStateOf(false)
        private set

    var wakeTimeDialogExpose = mutableStateOf(false)
        private set

    // Check whether the time format is correct or the user inputs the time.
    var biggestMealTimeError by mutableStateOf(false)
        private set

    var sleepTimeError by mutableStateOf(false)
        private set

    var wakeUpTimeError by mutableStateOf(false)
        private set


    /**
     * Functions for Questionnaire View Model
     */
    fun updateDevoteeShowDialog(newDevoteeShowDialog :Boolean){
        devoteeShowDialog.value = newDevoteeShowDialog
    }

    fun updateMindfulShowDialog(newMindfulShowDialog :Boolean){
        mindfulShowDialog.value = newMindfulShowDialog
    }

    fun updateWellnessShowDialog(newWellnessShowDialog :Boolean){
        wellnessShowDialog.value = newWellnessShowDialog
    }

    fun updateBalanceShowDialog(newBalanceShowDialog :Boolean){
        balanceShowDialog.value = newBalanceShowDialog
    }

    fun updateProcrastinatorShowDialog(newProcrastinatorShowDialog :Boolean){
        procrastinatorShowDialog.value = newProcrastinatorShowDialog
    }

    fun updateCarefreeShowDialog(newCarefreeShowDialog :Boolean){
        carefreeShowDialog.value = newCarefreeShowDialog
    }

    fun updateFruits(newFruits: Boolean){
        fruits.value = newFruits
    }

    fun updateVegetables(newVegetables: Boolean){
        vegetables.value = newVegetables
    }

    fun updateGrains(newGrains: Boolean){
        grains.value = newGrains
    }

    fun updateRedMeat(newRedMeat: Boolean){
        redMeat.value = newRedMeat
    }

    fun updateSeafood(newSeafood: Boolean){
        seafood.value = newSeafood
    }

    fun updatePoultry(newPoultry: Boolean){
        poultry.value = newPoultry
    }

    fun updateFish(newFish: Boolean){
        fish.value = newFish
    }

    fun updateEggs(newEggs: Boolean){
        eggs.value = newEggs
    }

    fun updateNutsNSeeds(newNutsNSeeds: Boolean){
        nutsNSeeds.value = newNutsNSeeds
    }

    fun updatePersona(newPersona: String){
        persona.value = newPersona
    }

    fun updatePersonaExpansion(newPersonaExpansion: Boolean){
        personaExpansion.value = newPersonaExpansion
    }

    fun updateBiggestMealTime(newTime :String){

        biggestMealTime.value = newTime
        if (isValidTime(biggestMealTime.value)){
            biggestMealTimeError = false
        }
    }

    fun updateBiggestMealTimeExpose(newExpose :Boolean){
        biggestMealTimeDialogExpose.value = newExpose
    }

    fun updateSleepTime(newTime :String){

        sleepTime.value = newTime
        if (isValidTime(sleepTime.value)){
            sleepTimeError = false
        }
    }

    fun updateSleepTimeExpose(newExpose :Boolean){
        sleepTimeDialogExpose.value = newExpose
    }

    fun updateWakeUpTime(newTime :String){

        wakeUpTime.value = newTime
        if (isValidTime(wakeUpTime.value)){
            wakeUpTimeError = false
        }
    }

    fun updateWakeUpTimeExpose(newExpose :Boolean){
        wakeTimeDialogExpose.value = newExpose
    }

    fun updateTimeFormatErr(){
        biggestMealTimeError = !isValidTime(biggestMealTime.value)
        sleepTimeError = !isValidTime(sleepTime.value)
        wakeUpTimeError = !isValidTime(wakeUpTime.value)
    }

    // Check whether the time format is valid
    fun isValidTime(time: String): Boolean {
        // If the time format has a length of 5.
        if(time == "" || time.length != 5) return false
        var count = 0
        time.forEach { char ->
            if (count == 2){
                if (char != ':'){
                    return false
                }
            }
            else{
                if (!char.isDigit()){
                    return false
                }
            }
            count += 1
        }
        return true
    }

    fun saveFoodIntakeData(){
        val savedFoodIntakeData = FoodIntake(
            userId,
            fruits.value,
            vegetables.value,
            grains.value,
            redMeat.value,
            seafood.value,
            poultry.value,
            fish.value,
            eggs.value,
            nutsNSeeds.value,
            persona.value,
            biggestMealTime.value,
            sleepTime.value,
            wakeUpTime.value,
        )

        viewModelScope.launch(Dispatchers.IO){
            if(repository.getFoodIntakeByUserId(userId) == null){
                repository.insertFoodIntake(savedFoodIntakeData)
            } else {
                repository.updateFoodIntake(savedFoodIntakeData)
            }
        }
    }

    fun loadFoodIntake(){
        viewModelScope.launch(Dispatchers.IO){
            val foodIntake = repository.getFoodIntakeByUserId(userId)
            if (foodIntake != null) {
                fruits.value = foodIntake.fruits
                vegetables.value = foodIntake.vegetables
                grains.value = foodIntake.grains
                redMeat.value = foodIntake.redMeat
                seafood.value = foodIntake.seafood
                poultry.value = foodIntake.poultry
                fish.value = foodIntake.fish
                eggs.value = foodIntake.eggs
                nutsNSeeds.value = foodIntake.nutsNSeeds
                persona.value = foodIntake.persona
                biggestMealTime.value = foodIntake.biggestMealTime
                sleepTime.value = foodIntake.sleepTime
                wakeUpTime.value = foodIntake.wakeUpTime
            }
        }
    }

    fun removeCurrentUser(){
        viewModelScope.launch(Dispatchers.IO){
            patientRepository.clearCurrentUser(userId)
        }
        AuthManager.logout()
    }

    init {
        loadFoodIntake()
    }

    //a view model factory that sets the context for the viewmodel
    //The ViewModelProvider.Factory interface is used to create view models.
    class QuestionnaireViewModelFactory(context: Context) : ViewModelProvider.Factory {
        private val context = context.applicationContext

        override fun <T : ViewModel> create(modelClass: Class<T>): T =
            QuestionnaireViewModel(context) as T
    }
}