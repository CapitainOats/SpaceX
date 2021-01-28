package cz.tomashavlicek.space_x.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.tomashavlicek.space_x.vo.Launch

/**
 * The Data Access Object for the [Launch] class.
 */
@Dao
interface LaunchesDao {
    @Query("SELECT * FROM launch")
    fun getAllLaunches(): LiveData<List<Launch>>

    @Query("SELECT * FROM launch WHERE timebase = :timebase")
    fun getLaunches(timebase: String): LiveData<List<Launch>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(launches: List<Launch>)
}
