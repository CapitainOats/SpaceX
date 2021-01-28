package cz.tomashavlicek.space_x.repository

import androidx.lifecycle.LiveData
import cz.tomashavlicek.space_x.AppExecutors
import cz.tomashavlicek.space_x.api.ApiSuccessResponse
import cz.tomashavlicek.space_x.api.SpaceService
import cz.tomashavlicek.space_x.db.LaunchesDao
import cz.tomashavlicek.space_x.util.RateLimiter
import cz.tomashavlicek.space_x.vo.Launch
import cz.tomashavlicek.space_x.vo.Resource
import cz.tomashavlicek.space_x.vo.Timebase
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LaunchesRepository @Inject constructor(
    private val spaceService: SpaceService,
    private val appExecutors: AppExecutors,
    private val launchesDao: LaunchesDao
) {

    private val launchesRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun loadLaunches(timebase: Timebase): LiveData<Resource<List<Launch>>> {
        return object : NetworkBoundResource<List<Launch>, List<Launch>>(appExecutors) {
            override fun loadFromDb(): LiveData<List<Launch>> {
                return if (timebase == Timebase.ALL) {
                    launchesDao.getAllLaunches()
                } else {
                    launchesDao.getLaunches(timebase.string)
                }
            }

            override fun shouldFetch(data: List<Launch>?) =
                data == null || data.isEmpty() || launchesRateLimit.shouldFetch("launches")

            override fun createCall() = spaceService.getLaunches(timebase.string)

            override fun processResponse(response: ApiSuccessResponse<List<Launch>>): List<Launch> {
                for (launch in response.body) {
                    launch.timebase = timebase.string
                }
                return super.processResponse(response)
            }

            override fun saveCallResult(item: List<Launch>) {
                launchesDao.insertAll(item)
            }

            override fun onFetchFailed() {
                launchesRateLimit.reset("launches")
            }
        }.asLiveData()
    }
}
