package com.fit2081.Tee_34570403.nutritrack.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fit2081.Tee_34570403.nutritrack.data.dao.FoodIntakeDAO
import com.fit2081.Tee_34570403.nutritrack.data.dao.PatientDAO
import com.fit2081.Tee_34570403.nutritrack.data.models.FoodIntake
import com.fit2081.Tee_34570403.nutritrack.data.models.Patient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader

@Database(entities = [Patient::class, FoodIntake::class], version = 1, exportSchema = false)
abstract class NutritrackDatabase : RoomDatabase() {

    /**
     * Provides access to the PatientDAO interface for performing
     * database operations on Patient entities.
     * @return PatientDao instance.
     */
    abstract fun patientDAO(): PatientDAO

    /**
     * Provides access to the FoodIntakeDAO interface for performing
     * database operations on FoodIntake entities.
     * @return FoodIntakeDAO instance.
     */
    abstract fun foodIntakeDAO(): FoodIntakeDAO

    companion object {
        // Singleton instance of the database
        @Volatile
        private var Instance: NutritrackDatabase? = null

        private suspend fun prepopulateDatabase(context: Context) {
            val assets = context.assets
            try {
                val inputStream = assets.open("data.csv")
                val reader = BufferedReader(InputStreamReader(inputStream))
                val patientDao = Instance?.patientDAO()

                reader.useLines { lines ->
                    lines.drop(1).forEach { line ->
                        val values = line.split(",")
                        if (values.size >= 63) {
                            val phoneNumber = values[0].trim()
                            val userId = values[1].trim().toInt()
                            val sex = values[2].trim()
                            val sexNum = if (sex.equals("Male", true)) 0 else 1

                            val patient = Patient(
                                userId = userId,
                                phoneNumber = phoneNumber,
                                password = null,
                                name = null,
                                sex = sex,
                                currentLoggedInUser = false,
                                totalHEIFAScore = values[3 + sexNum].trim().toDouble(),
                                discretionaryHEIFAScore = values[5 + sexNum].trim().toDouble(),
                                discretionaryServeSize = values[7].trim().toDouble(),
                                vegetableHEIFAScore = values[8 + sexNum].trim().toDouble(),
                                vegetablesWithLegumesAllocatedServeSize = values[10].trim().toDouble(),
                                legumesAllocatedVegetables = values[11].trim().toDouble(),
                                vegetablesVariationScore = values[12].trim().toDouble(),
                                vegetablesCruciferous = values[13].trim().toDouble(),
                                vegetablesTuberAndBulb = values[14].trim().toDouble(),
                                vegetablesOther = values[15].trim().toDouble(),
                                legumes = values[16].trim().toDouble(),
                                vegetablesGreen = values[17].trim().toDouble(),
                                vegetablesRedandOrange = values[18].trim().toDouble(),
                                fruitHEIFAScore = values[19 + sexNum].trim().toDouble(),
                                fruitServeSize = values[21].trim().toDouble(),
                                fruitVariationScore = values[22].trim().toDouble(),
                                fruitPome = values[23].trim().toDouble(),
                                fruitTropicalAndSubTropical = values[24].trim().toDouble(),
                                fruitBerry = values[25].trim().toDouble(),
                                fruitStone = values[26].trim().toDouble(),
                                fruitCitrus = values[27].trim().toDouble(),
                                fruitOther = values[28].trim().toDouble(),
                                grainsNCerealsHEIFAScore = values[29 + sexNum].trim().toDouble(),
                                grainsNCerealsServeSize = values[31].trim().toDouble(),
                                grainsNCerealsNonWholeGrains = values[32].trim().toDouble(),
                                wholeGrainsHEIFAScore = values[33 + sexNum].trim().toDouble(),
                                wholeGrainsServeSize = values[35].trim().toDouble(),
                                meatNAlternativesHEIFAScore = values[36 + sexNum].trim().toDouble(),
                                meatNAlternativesWithLegumesAllocatedServeSize = values[38].trim().toDouble(),
                                meatNAlternativesLegumesAllocated = values[39].trim().toDouble(),
                                dairyNAlternativesHEIFAScore = values[40 + sexNum].trim().toDouble(),
                                dairyNAlternativesServeSize = values[42].trim().toDouble(),
                                sodiumHEIFAScore = values[43 + sexNum].trim().toDouble(),
                                sodiumMilligrams = values[45].trim().toDouble(),
                                alcoholHEIFAScore = values[46 + sexNum].trim().toDouble(),
                                alcoholStandardDrinks = values[48].trim().toDouble(),
                                waterHEIFAScore = values[49 + sexNum].trim().toDouble(),
                                water = values[51].trim().toDouble(),
                                waterTotalMl = values[52].trim().toDouble(),
                                beverageTotalMl = values[53].trim().toDouble(),
                                sugarHEIFAScore = values[54 + sexNum].trim().toDouble(),
                                sugar = values[56].trim().toDouble(),
                                saturatedFatHEIFAScore = values[57 + sexNum].trim().toDouble(),
                                saturatedFat = values[59].trim().toDouble(),
                                unsaturatedFatHEIFAScore = values[60 + sexNum].trim().toDouble(),
                                unsaturatedFatServeSize = values[62].trim().toDouble()
                            )

                            patientDao?.insert(patient)
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        /**
         * Retrieves the singleton instance of the database.
         * If an instance already exists, it returns the existing
         * instance. Otherwise, it creates a new instance of the database.
         * @param context The context of the application.
         * @return The singleton instance of NutritrackDatabase.
         */
        fun getDatabase(context: Context): NutritrackDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NutritrackDatabase::class.java, "NutritrackDatabase")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Prepopulate in a coroutine
                            CoroutineScope(Dispatchers.IO).launch {
                                prepopulateDatabase(context)
                            }
                        }
                    })
                    .build()
                    .also { Instance = it }
            }
        }
    }
}