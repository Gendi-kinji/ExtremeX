package com.example.extremex.data
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DestinationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(destination: Destination)

    @Update
    suspend fun update(destination: Destination)

    @Delete
    suspend fun delete(destination: Destination)

    @Query("Select * from destinations where id = :id")
    fun getDestination(id: Int): Flow<Destination>
    @Query("Select * from destinations where name = :name")
    fun getDestination(name: String): Flow<Destination>

    @Query("Select * from destinations order by name asc")
    fun getAllDestinations():Flow<List<Destination>>

    @Query("Select * from destinations order by safety_level asc")
    fun getAllDestinationsBySafety():Flow<List<Destination>>



}