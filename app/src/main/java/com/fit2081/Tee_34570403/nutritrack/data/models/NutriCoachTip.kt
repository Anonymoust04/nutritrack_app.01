package com.fit2081.Tee_34570403.nutritrack.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

// NutriCoach Tip Entity
@Entity(
    tableName = "NutriCoachTips",
    primaryKeys = ["userId", "motivationTip"])
data class NutriCoachTip (

    val userId:Int,

    val motivationTip: String,

    val creationDateTime: String,

    var isSelected: Boolean,
)