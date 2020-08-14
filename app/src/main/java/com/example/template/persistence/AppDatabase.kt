package com.example.template.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.template.models.AccountProperties

@Database(entities = [AccountProperties::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun getAccountPropertiesDao(): AccountPropertiesDao

    companion object{
        val DATABASE_NAME: String = "template_db"
    }


}




