package com.test.news.db

import androidx.room.Dao
import androidx.room.Query
import com.test.news.features.login.data.model.User
import io.reactivex.Observable

/**
 * Room dao interface
 */
@Dao
interface UsersDao {

    @Query("SELECT * FROM user")
    fun getUsers(): Observable<List<User>>
}
