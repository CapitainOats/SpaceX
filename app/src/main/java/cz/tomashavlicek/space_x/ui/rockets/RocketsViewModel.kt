package cz.tomashavlicek.space_x.ui.rockets

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import cz.tomashavlicek.space_x.repository.RocketsRepository

class RocketsViewModel @ViewModelInject constructor(
    rocketsRepository: RocketsRepository
) : ViewModel() {

    val rockets = rocketsRepository.loadRockets()

//    val rockets: LiveData<Resource<List<Rocket>>> = rocketsRepository.loadRockets().switchMap { rocketList ->
//        if (rocketList.data != null && rocketList.data.isEmpty()) {
//            AbsentLiveData.create()
//        } else {
//            // TODO: remake
//            rocketsRepository.loadRockets()
//        }
//    }

    fun retry() {
//        rocketsRepository.loadRockets()
    }
}
