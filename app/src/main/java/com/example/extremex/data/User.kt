package com.example.extremex.data
import androidx.room.*

@Entity(tableName="users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val email: String,
    val password: String
)
