package cz.tomashavlicek.space_x.vo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cz.tomashavlicek.space_x.db.LaunchesDao
import cz.tomashavlicek.space_x.db.RocketDao
import cz.tomashavlicek.space_x.util.DATABASE_NAME

@Database(entities = [Rocket::class, Launch::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun rocketsDao(): RocketDao
    abstract fun launchesDao(): LaunchesDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}
