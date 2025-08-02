package com.fit2081.Tee_34570403.nutritrack.data.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "FoodIntake",
    foreignKeys = [
        ForeignKey(
            entity = Patient::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId")]
)
data class FoodIntake(

    @PrimaryKey
    val userId: Int,

    val fruits: Boolean,

    val vegetables: Boolean,

    val grains: Boolean,

    val redMeat: Boolean,

    val seafood: Boolean,

    val poultry: Boolean,

    val fish: Boolean,

    val eggs: Boolean,

    val nutsNSeeds: Boolean,

    val persona: String,

    val biggestMealTime: String,

    val sleepTime: String,

    val wakeUpTime: String

)