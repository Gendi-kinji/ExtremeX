package com.example.extremex.data
import kotlinx.coroutines.flow.Flow
interface DestinationRepository  {
    suspend fun insert(destination: Destination)
    suspend fun update(destination: Destination)
    suspend fun delete(destination: Destination)
    fun getDestination(name: String): Flow<Destination>
    fun getDestination(id: Int): Flow<Destination>
    fun getAllDestinations(): Flow<List<Destination>>
    suspend fun insertUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun deleteUser(user: User)
    fun getUser(username: String): Flow<User>
    fun getAllUsers(): Flow<List<User>>

}