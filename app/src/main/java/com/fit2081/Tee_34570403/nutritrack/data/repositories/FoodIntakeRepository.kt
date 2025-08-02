package com.fit2081.Tee_34570403.nutritrack.data.repositories

import android.content.Context
import com.fit2081.Tee_34570403.nutritrack.data.databases.NutritrackDatabase
import com.fit2081.Tee_34570403.nutritrack.data.models.FoodIntake
import kotlinx.coroutines.flow.Flow

class FoodIntakeRepository(context: Context) {

    // Get the FoodIntakeDao instance from the database
    private val foodIntakeDao = NutritrackDatabase.getDatabase(context).foodIntakeDAO()

    /**
     * Inserts a new patient food intake into the database.
     * @param foodIntake The foodIntake object to be inserted.
     */
    suspend fun insertFoodIntake(foodIntake: FoodIntake) {
        foodIntakeDao.insert(foodIntake)
    }

    /**
     * Deletes a patient food intake inside the database using user ID.
     * @param foodIntake The patient food intake object to be inserted.
     */
    suspend fun deleteFoodIntakeByUserID(userId: Int) {
        foodIntakeDao.deleteUserById(userId)
    }

    /**
     * Update a patient food intake inside the database.
     * @param foodIntake The patient food intake object to be updated.
     */
    suspend fun updateFoodIntake(foodIntake: FoodIntake) {
        foodIntakeDao.update(foodIntake)
    }

    /**
     * Retrieves a patient food intake from the database by their ID.
     * @param userId The ID of the patient food intake to retrieve.
     * @return The FoodIntake object with the given ID.
     */
    suspend fun getFoodIntakeByUserId(userId: Int): FoodIntake {
        return foodIntakeDao.getFoodIntakeByUserId(userId)
    }

    /**
     * Retrieves all patient food intake from the database as a Flow.
     * This allows observing changes to the patient food intake list over time.
     * @return A Flow emitting a list of all users.
     */
    fun getAllFoodIntakes(): Flow<List<FoodIntake>> = foodIntakeDao.getAllFoodIntakes()
}