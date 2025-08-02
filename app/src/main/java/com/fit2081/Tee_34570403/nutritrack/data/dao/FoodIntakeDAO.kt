package com.fit2081.Tee_34570403.nutritrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fit2081.Tee_34570403.nutritrack.data.models.FoodIntake
import kotlinx.coroutines.flow.Flow


// Food Intake Data Access Objects (DAO)
@Dao
interface FoodIntakeDAO {

    // Inserts a new patient into the database.
    //suspend is a coroutine function that can be paused and resumed at a later time.
    //suspend is used to indicate that the function will be called from a coroutine.
    @Insert
    suspend fun insert(patientFoodIntake: FoodIntake)

    /**
     * Retrieves a patient from the database based on their ID.
     *
     * @param userId The ID of the patient to retrieve.
     * @return The [FoodIntake] object if found,
     * null otherwise (handled by Room as per suspend function).
     */
    @Query("SELECT * FROM FoodIntake WHERE userId = :userId")
    suspend fun getFoodIntakeByUserId(userId: Int): FoodIntake

    // Updates an existing patient in the database.
    @Update
    suspend fun update(patientFoodIntake: FoodIntake)

    // Deletes a specific patient from the database.
    @Delete
    suspend fun delete(patientFoodIntake: FoodIntake)

    // Deletes all food intake from the database.
    @Query("DELETE FROM FoodIntake")
    suspend fun deleteAllUserFoodIntakes()

    // Deletes a patient from the database by ID
    @Query("DELETE FROM FoodIntake WHERE userId = :userId")
    suspend fun deleteUserById(userId: Int)

    // Retrieves all food intake from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT * FROM FoodIntake ORDER BY userId ASC")
    fun getAllFoodIntakes(): Flow<List<FoodIntake>>

}