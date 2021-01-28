package cz.tomashavlicek.space_x.repository

import androidx.lifecycle.LiveData
import cz.tomashavlicek.space_x.AppExecutors
import cz.tomashavlicek.space_x.api.SpaceService
import cz.tomashavlicek.space_x.util.ROCKETS
import cz.tomashavlicek.space_x.util.RateLimiter
import cz.tomashavlicek.space_x.vo.Resource
import cz.tomashavlicek.space_x.vo.Rocket
import cz.tomashavlicek.space_x.db.RocketDao
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RocketsRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val spaceService: SpaceService,
    private val rocketsDao: RocketDao
) {

    private val rocketsRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun loadRockets(): LiveData<Resource<List<Rocket>>> {
        return object : NetworkBoundResource<List<Rocket>, List<Rocket>>(appExecutors) {
            override fun loadFromDb() = rocketsDao.findAll()

            override fun shouldFetch(data: List<Rocket>?): Boolean {
                return data == null || data.isEmpty() || rocketsRateLimit.shouldFetch(ROCKETS)
            }

            override fun createCall() = spaceService.getRockets()

            override fun saveCallResult(item: List<Rocket>) {
                rocketsDao.insertAll(item)
            }

            override fun onFetchFailed() {
                rocketsRateLimit.reset(ROCKETS)
            }
        }.asLiveData()
    }

    fun loadRocket(id: String): LiveData<Resource<Rocket>> {
        return object : NetworkBoundResource<Rocket, Rocket>(appExecutors) {
            override fun loadFromDb() = rocketsDao.findById(id)

            override fun shouldFetch(data: Rocket?) = data == null

            override fun createCall() = spaceService.getRocket(id)

            override fun saveCallResult(item: Rocket) {
                rocketsDao.insert(item)
            }
        }.asLiveData()
    }
}
