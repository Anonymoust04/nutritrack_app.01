package com.fit2081.Tee_34570403.nutritrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fit2081.Tee_34570403.nutritrack.data.models.Patient
import kotlinx.coroutines.flow.Flow

// This interface defines the data access object (DAO) for the User entity.
@Dao
interface PatientDAO {
    // Inserts a new patient into the database.
    //suspend is a coroutine function that can be paused and resumed at a later time.
    //suspend is used to indicate that the function will be called from a coroutine.
    @Insert
    suspend fun insert(patient: Patient)

    /**
     * Retrieves a patient from the database based on their ID.
     *
     * @param userId The ID of the patient to retrieve.
     * @return The [Patient] object if found,
     * null otherwise (handled by Room as per suspend function).
     */
    @Query("SELECT * FROM Patient WHERE userId = :userId")
    suspend fun getPatientByUserId(userId: Int): Patient

    // Updates an existing patient in the database.
    @Update
    suspend fun update(patient: Patient)

    // Updates an existing patient in the database.
    @Query("UPDATE Patient SET name = :name WHERE userId = :userId")
    suspend fun setName(userId: Int, name: String)

    // Updates an existing patient in the database.
    @Query("UPDATE Patient SET phoneNumber = :phoneNumber WHERE userId = :userId")
    suspend fun setPhoneNumber(userId: Int, phoneNumber: String)

    // Updates an existing patient in the database.
    @Query("UPDATE Patient SET password = :password WHERE userId = :userId")
    suspend fun setPassword(userId: Int, password: String)

    @Query("UPDATE Patient SET currentLoggedInUser = true WHERE userId = :userId")
    suspend fun setCurrentUser(userId: Int)

    @Query("UPDATE Patient SET currentLoggedInUser = false WHERE userId = :userId")
    suspend fun clearCurrentUser(userId: Int)

    @Query("UPDATE Patient SET currentLoggedInUser = false WHERE currentLoggedInUser = true")
    suspend fun clearCurrentUsers()

    @Query("SELECT userId FROM Patient WHERE currentLoggedInUser = true")
    fun getCurrentUser(): Flow<List<Int>>

    // Deletes a specific patient from the database.
    @Delete
    suspend fun delete(patient: Patient)

    // Deletes all patients from the database.
    @Query("DELETE FROM Patient")
    suspend fun deleteAllPatients()

    // Deletes a patient from the database by ID
    @Query("DELETE FROM Patient WHERE userId = :userId")
    suspend fun deleteUserById(userId: Int)

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT AVG(totalHEIFAScore) FROM Patient WHERE upper(sex) = upper('Male')")
    suspend fun getAvgMaleHEIFAScore(): Double

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT AVG(totalHEIFAScore) FROM Patient WHERE upper(sex) = upper('Female')")
    suspend fun getAvgFemaleHEIFAScore(): Double

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT * FROM Patient ORDER BY userId ASC")
    fun getAllPatients(): Flow<List<Patient>>

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT userId FROM Patient WHERE password IS NOT NULL ORDER BY userId ASC")
    fun getAllRegisteredUserIDs(): Flow<List<Int>>

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT userId FROM Patient WHERE password IS NULL ORDER BY userId ASC")
    fun getAllUnregisteredUserIDs(): Flow<List<Int>>

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    //The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT userId FROM Patient ORDER BY userId ASC")
    fun getAllUserIDs(): Flow<List<Int>>
}