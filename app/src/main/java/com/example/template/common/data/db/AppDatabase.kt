package com.example.template.common.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.template.features.auth.data.db.AccountPropertiesDao
import com.example.template.features.auth.data.db.models.AccountProperties

@Database(entities = [AccountProperties::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object{
        val DATABASE_NAME: String = "template_db"
    }


}




