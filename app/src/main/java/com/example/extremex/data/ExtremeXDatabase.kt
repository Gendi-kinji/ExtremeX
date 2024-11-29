package com.example.extremex.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.extremex.data.Destination
import com.example.extremex.data.DestinationDao
import androidx.room.RoomDatabase as RoomDatabase1

@Database(entities = [Destination::class, User::class], version = 1, exportSchema = false)
abstract class ExtremeXDatabase: RoomDatabase1() {
    abstract fun destinationDao(): DestinationDao
    abstract fun userDao(): UserDao
    companion object {
        @Volatile
        private var INSTANCE: ExtremeXDatabase? = null

        fun getInstance(context: Context): ExtremeXDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ExtremeXDatabase::class.java,
                    "ExtremeXDatabase"
                )
                    .build()
                    .also { INSTANCE = it }
            }
        }

    }

}