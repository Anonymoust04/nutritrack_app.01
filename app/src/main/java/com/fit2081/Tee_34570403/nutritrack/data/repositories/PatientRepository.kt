package com.fit2081.Tee_34570403.nutritrack.data.repositories

import android.content.Context
import com.fit2081.Tee_34570403.nutritrack.data.databases.NutritrackDatabase
import com.fit2081.Tee_34570403.nutritrack.data.models.Patient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class PatientRepository(context: Context) {

    // Get the PatientDao instance from the database
    private val patientDAO = NutritrackDatabase.getDatabase(context).patientDAO()

    /**
     * Inserts a new patient into the database.
     * @param patient The patient object to be inserted.
     */
    suspend fun insertPatient(patient: Patient) {
        patientDAO.insert(patient)
    }

    /**
     * Sets a new patient name into the database.
     * @param userId The patient userID.
     * @param name the patient new name.
     */
    suspend fun setNewName(userId: Int, name: String) {
        patientDAO.setName(userId, name)
    }

    /**
     * Sets a new patient phone number into the database.
     * @param userId The patient userID.
     * @param phoneNumber the patient new phone number.
     */
    suspend fun setNewPhoneNumber(userId: Int, phoneNumber: String) {
        patientDAO.setPhoneNumber(userId, phoneNumber)
    }

    /**
     * Sets a new patient into the database.
     * @param userId The patient userID.
     * @param password the patient new password.
     */
    suspend fun setNewPassword(userId: Int, password: String) {
        patientDAO.setPassword(userId, password)
    }

    /**
     * Deletes a patient inside the database using user ID.
     * @param patient The patient object to be inserted.
     */
    suspend fun deletePatientByUserID(userId: Int) {
        patientDAO.deleteUserById(userId)
    }

    /**
     * Retrieves a patient from the database by their ID.
     * @param userId The ID of the patient to retrieve.
     * @return The Patient object with the given ID.
     */
    suspend fun getPatientByUserId(userId: Int): Patient {
        return patientDAO.getPatientByUserId(userId)
    }

    suspend fun setCurrentUser(userId: Int){
        patientDAO.setCurrentUser(userId)
    }

    suspend fun clearCurrentUser(userId: Int){
        patientDAO.clearCurrentUser(userId)
    }

    suspend fun getCurrentUser(): List<Int> {
        return patientDAO.getCurrentUser().first()
    }

    suspend fun clearCurrentUsers() {
        return patientDAO.clearCurrentUsers()
    }

    /**
     * Retrieves all patients from the database as a Flow.
     * This allows observing changes to the patient list over time.
     * @return A Flow emitting a list of all users.
     */
    fun getAllPatients(): Flow<List<Patient>> = patientDAO.getAllPatients()

    suspend fun getAllUserIDs(): List<Int> {
        return patientDAO.getAllUserIDs().first()
    }

    /**
     * Retrieves all registered patient IDs from the database as a Flow.
     * This allows observing changes to the patient list over time.
     * @return A Flow emitting a list of all users.
     */
    suspend fun getAllRegisteredUserIDs(): List<Int> {
        return patientDAO.getAllRegisteredUserIDs().first()
    }

    /**
     * Retrieves all unregistered patient IDs from the database as a Flow.
     * This allows observing changes to the patient list over time.
     * @return A Flow emitting a list of all users.
     */
    suspend fun getAllUnregisteredUserIDs(): List<Int>{
        return patientDAO.getAllUnregisteredUserIDs().first()
    }

    suspend fun deleteAllPatients(){
        patientDAO.deleteAllPatients()
    }
}