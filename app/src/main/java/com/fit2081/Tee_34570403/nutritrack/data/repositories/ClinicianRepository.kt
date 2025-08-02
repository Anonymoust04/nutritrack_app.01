package com.fit2081.Tee_34570403.nutritrack.data.repositories

import android.content.Context
import com.fit2081.Tee_34570403.nutritrack.BuildConfig
import com.fit2081.Tee_34570403.nutritrack.data.databases.NutritrackDatabase
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

class ClinicianRepository(context :Context) {

    // Get the PatientDao instance from the database
    private val patientDAO = NutritrackDatabase.getDatabase(context).patientDAO()

    suspend fun getAvgHEIFAMaleScore(): Double{
        return patientDAO.getAvgMaleHEIFAScore()
    }

    suspend fun getAvgHEIFAFemaleScore(): Double{
        return patientDAO.getAvgFemaleHEIFAScore()
    }

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.apiKey
    )

    // Function to prepare data and send to AI (Gemini)
    suspend fun analyseHealthData(): GenerateContentResponse { // Returns the AI patterns as a String
        val allPatientData = patientDAO.getAllPatients().first() // Get a snapshot of data

        // Convert to JSON string (using Gson or kotlinx.serialization)
        val gson = Gson() // Or use Json from kotlinx.serialization
        val jsonData = gson.toJson(allPatientData)

        // Create the prompt for Gemini
        val prompt = """
                    Analyze the following health dataset to identify key patterns and insights related to HEIFA scores, water intake, and wholegrain consumption, especially considering gender differences.
                    Dataset (JSON):
                    $jsonData
        
                    ---
                    Please provide three key data patterns in a clear, structured list.
                    Example output format without any other symbols:
                    - [Pattern Title 1]: [Detailed and Comprehensive Description of pattern]
                    - [Pattern Title 2]: [Detailed and Comprehensive Description of pattern]
                    - [Pattern Title 3]: [Detailed and Comprehensive Description of pattern]
                    
                    NOTE: Do not directly mention any of the attribute names from the Patient data type (For Example, "wholeGrainsHEIFAScore" and "wholeGrainServeSize")
                    """
                    .trimIndent()

        // Call Gemini API
        return generativeModel.generateContent(prompt)
    }
}