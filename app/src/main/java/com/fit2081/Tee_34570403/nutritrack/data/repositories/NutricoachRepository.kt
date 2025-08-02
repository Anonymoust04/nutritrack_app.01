package com.fit2081.Tee_34570403.nutritrack.data.repositories

import android.content.Context
import com.fit2081.Tee_34570403.nutritrack.data.databases.NutriCoachTips
import com.fit2081.Tee_34570403.nutritrack.data.models.NutriCoachTip
import kotlinx.coroutines.flow.first

class NutricoachRepository(context : Context) {

    // Get the NutricoachTipDao instance from the database
    private val nutricoachTipDAO = NutriCoachTips.getDatabase(context).nutricoachTipDAO()

    suspend fun insert(tip :NutriCoachTip){
        nutricoachTipDAO.insert(tip)
    }

    suspend fun getAllTips(userId :Int): List<NutriCoachTip> {
        return nutricoachTipDAO.getAllTips(userId).first()
    }

    suspend fun deleteTip(tip :NutriCoachTip){
        return nutricoachTipDAO.delete(tip)
    }

    suspend fun deleteAllTips(){
        nutricoachTipDAO.deleteAllTips()
    }

}