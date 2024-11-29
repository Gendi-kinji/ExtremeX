package com.example.extremex.data

import android.content.Context

// Dependency container for the application
class AppContainer(context: Context) {

    // Lazy initialization of the database instance
    private val database by lazy { ExtremeXDatabase.getInstance(context) }

    // Repository that interacts with DAOs
    val repository by lazy {
        OfflineRepository(database.destinationDao(), database.userDao())
    }
}

