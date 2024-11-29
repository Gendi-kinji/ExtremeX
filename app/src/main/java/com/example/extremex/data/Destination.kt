package com.example.extremex.data
import androidx.room.*

@Entity(tableName = "destinations")
data class Destination(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val safety_level: Int

)
