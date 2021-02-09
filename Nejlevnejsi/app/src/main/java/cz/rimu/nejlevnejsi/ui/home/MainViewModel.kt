package cz.rimu.nejlevnejsi.ui.home

import androidx.lifecycle.*
import cz.rimu.nejlevnejsi.db.models.FavouriteOffer
import cz.rimu.nejlevnejsi.repositories.HomeRepository
import cz.rimu.nejlevnejsi.rest.models.Offers
import cz.rimu.nejlevnejsi.rest.models.OffersData
import cz.rimu.nejlevnejsi.rest.models.Tags
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

class MainViewModel(
    private val homeRepository: HomeRepository
) : ViewModel(),
    DefaultLifecycleObserver {
    val tags: LiveData<Tags> = liveData {
        emit(homeRepository.getTags())
    }

    val topOffers: LiveData<Offers> = liveData {
        emit(homeRepository.getTopOffers())
    }

    val favouriteOffers: LiveData<List<OffersData>> = Transformations.map(homeRepository.getFavouriteOffers()){
        val offers = mutableListOf<OffersData>()
        for (offer in it) {
            offers.add(OffersData(name = offer.name, description = offer.description))
        }
        return@map offers
    }

    fun addToFavorites(offersData: OffersData) {
        viewModelScope.launch {
            homeRepository.addToFavorites(offersData)
        }
    }

    fun removeFromFavourites(offersData: OffersData) {
        viewModelScope.launch {
            homeRepository.removeFromFavorites(offersData)
        }
    }
}