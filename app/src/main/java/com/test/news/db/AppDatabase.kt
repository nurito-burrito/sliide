package com.test.news.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.news.features.login.data.model.User

/**
 * Main database description.
 */
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usersDao(): UsersDao
}
