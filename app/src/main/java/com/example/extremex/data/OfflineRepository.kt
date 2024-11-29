package com.example.extremex.data

import kotlinx.coroutines.flow.Flow


class OfflineRepository(private val destinationDao: DestinationDao, private val userDao: UserDao) : DestinationRepository {
    override suspend fun insert(destination: Destination) = destinationDao.insert(destination)
    override suspend fun update(destination: Destination) = destinationDao.update(destination)
    override suspend fun delete(destination: Destination) = destinationDao.delete(destination)
    override fun getDestination(id: Int): Flow<Destination> = destinationDao.getDestination(id)
    override fun getAllDestinations(): Flow<List<Destination>> = destinationDao.getAllDestinations()
    override fun getDestination(name: String): Flow<Destination> = destinationDao.getDestination(name)

    override suspend fun insertUser(user: User) = userDao.insertUser(user)
    override suspend fun updateUser(user: User) = userDao.updateUser(user)
    override suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    override fun getUser(username: String): Flow<User> = userDao.getUser(username)
    override fun getAllUsers(): Flow<List<User>> = userDao.getAllUsers()

}