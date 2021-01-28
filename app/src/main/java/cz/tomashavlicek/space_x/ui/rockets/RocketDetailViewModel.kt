package cz.tomashavlicek.space_x.ui.rockets

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.inject.assisted.AssistedInject
import cz.tomashavlicek.space_x.repository.RocketsRepository

/**
 * The ViewModel used in [RocketDetailFragment].
 */
class RocketDetailViewModel @ViewModelInject constructor(
    rocketsRepository: RocketsRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val rocket = rocketsRepository.loadRocket(savedStateHandle.get<String>("rocketId")!!)
}
