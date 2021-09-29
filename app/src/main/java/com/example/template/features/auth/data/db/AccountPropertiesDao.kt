package com.example.template.features.auth.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.template.features.auth.data.db.models.AccountProperties

@Dao
interface AccountPropertiesDao {

    @Query("SELECT * FROM account_properties WHERE email = :email")
    suspend fun searchByEmail(email: String): AccountProperties?

    @Query("SELECT * FROM account_properties WHERE pk = :pk")
    fun searchByPrimaryKey(pk: Int): LiveData<AccountProperties>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAndReplace(accountProperties: AccountProperties): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnore(accountProperties: AccountProperties): Long

    @Query("UPDATE account_properties SET email = :email, username = :username WHERE pk = :pk")
    fun updateAccountProperties(pk: Int, email: String, username: String)
}