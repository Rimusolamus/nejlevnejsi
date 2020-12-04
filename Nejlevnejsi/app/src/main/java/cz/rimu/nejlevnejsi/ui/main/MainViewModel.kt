package cz.rimu.nejlevnejsi.ui.main

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import cz.rimu.nejlevnejsi.repositories.MainRepository
import cz.rimu.nejlevnejsi.rest.models.Offers
import cz.rimu.nejlevnejsi.rest.models.Tags

class MainViewModel(private val mainRepository: MainRepository) : ViewModel(),
	DefaultLifecycleObserver {
	val tags: LiveData<Tags> = liveData {
		emit(mainRepository.getTags())
	}

	val topOffers: LiveData<Offers> = liveData {
		emit(mainRepository.getTopOffers())
	}
}