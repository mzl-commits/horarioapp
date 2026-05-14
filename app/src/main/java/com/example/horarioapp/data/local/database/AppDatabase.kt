package com.example.horarioapp.data.local.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.horarioapp.data.local.dao.ScheduleDao
import com.example.horarioapp.data.local.dao.UserDao
import com.example.horarioapp.data.local.entity.ScheduleEntity
import com.example.horarioapp.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ScheduleEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun scheduleDao(): ScheduleDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "horarioapp_database"
                )
                    .fallbackToDestructiveMigration() // Para desarrollo
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}