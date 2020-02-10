package com.test.news.features.login.data

import com.test.news.db.UsersDao
import javax.inject.Inject

class UsersRepository @Inject constructor(
    private val usersDao: UsersDao
) {

    fun getUsers() = usersDao.getUsers()
}
