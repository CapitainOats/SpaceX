package cz.tomashavlicek.space_x.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cz.tomashavlicek.space_x.vo.Rocket

/**
 * The Data Access Object for the [Rocket] class.
 */
@Dao
interface RocketDao {
    @Query("SELECT * FROM rocket ORDER BY name")
    fun findAll(): LiveData<List<Rocket>>

    @Query("SELECT * FROM rocket WHERE id = :id")
    fun findById(id: String): LiveData<Rocket>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(rockets: List<Rocket>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(rocket: Rocket)
}
