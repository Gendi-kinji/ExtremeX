package com.example.extremex.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)

    @Query("Select * from users where username= :username")
    fun getUser(username: String): Flow<User>
    @Query("Select * from users order by username asc")
    fun getAllUsers(): Flow<List<User>>

}