package com.fit2081.Tee_34570403.nutritrack.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip
import kotlinx.coroutines.flow.Flow

// NutriCoach Data Access Objects (DAO)
@Dao
interface NutriCoachTipDAO {

    @Insert
    suspend fun insert(tip: NutriCoachTip)

    // Retrieves all patients from the database, ordered by their ID in ascending order.
    // The return type is a Flow, which is a data stream that can be observed for changes.
    @Query("SELECT * FROM NutriCoachTips WHERE userId = :userId ORDER BY creationDateTime ASC")
    fun getAllTips(userId :Int): Flow<List<NutriCoachTip>>

    @Delete
    suspend fun delete(tip :NutriCoachTip)

    // Deletes all motivational tips from the database.
    @Query("DELETE FROM NutriCoachTips")
    suspend fun deleteAllTips()
}