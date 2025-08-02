package com.fit2081.Tee_34570403.nutritrack.data.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fit2081.Tee_34570403.nutritrack.data.dao.NutriCoachTipDAO
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip

@Database(entities = [NutriCoachTip::class], version = 1, exportSchema = false)
abstract class NutriCoachTips : RoomDatabase() {

    /**
     * Provides access to the NutriCoachTipDAO interface for performing
     * database operations on NutriCoachTip entities.
     * @return NutricoachTipDao instance.
     */
    abstract fun nutricoachTipDAO(): NutriCoachTipDAO

    companion object {
        // Singleton instance of the database
        @Volatile
        private var Instance: NutriCoachTips? = null

        /**
         * Retrieves the singleton instance of the database.
         * If an instance already exists, it returns the existing
         * instance. Otherwise, it creates a new instance of the database.
         * @param context The context of the application.
         * @return The singleton instance of NutritrackDatabase.
         */
        fun getDatabase(context: Context): NutriCoachTips {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, NutriCoachTips::class.java, "NutriCoachTips")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}