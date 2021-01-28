package cz.tomashavlicek.space_x.ui.launches

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import cz.tomashavlicek.space_x.repository.LaunchesRepository
import cz.tomashavlicek.space_x.vo.Launch
import cz.tomashavlicek.space_x.vo.Resource
import cz.tomashavlicek.space_x.vo.Timebase

class LaunchesViewModel @ViewModelInject constructor(
    private val launchesRepository: LaunchesRepository
) : ViewModel() {

    // TODO handle loading

    private val _timebase = MutableLiveData<Timebase>()
    private val timebase: LiveData<Timebase>
        get() = _timebase

    init {
        _timebase.postValue(Timebase.ALL)
    }

    val launches: LiveData<Resource<List<Launch>>> = timebase.switchMap {
        launchesRepository.loadLaunches(it)
    }

    fun updateFilter(timebase: Timebase) {
        _timebase.postValue(timebase)
    }
}
