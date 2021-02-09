package cz.rimu.nejlevnejsi.repositories

import androidx.lifecycle.LiveData
import cz.rimu.nejlevnejsi.db.dao.FavouriteOffersDao
import cz.rimu.nejlevnejsi.db.models.FavouriteOffer
import cz.rimu.nejlevnejsi.rest.Api
import cz.rimu.nejlevnejsi.rest.models.Offers
import cz.rimu.nejlevnejsi.rest.models.OffersData
import cz.rimu.nejlevnejsi.rest.models.Tags
import java.net.ConnectException

class HomeRepository(private val api: Api, private val favouriteOffersDao: FavouriteOffersDao) {
    suspend fun getTags(): Tags = try {
        api.getTags()
    } catch (e: ConnectException) {
        Tags(listOf())
    }

    suspend fun getTopOffers(): Offers = try {
        api.getTopOffers()
    } catch (e: ConnectException) {
        Offers(listOf())
    }

    fun getFavouriteOffers(): LiveData<List<FavouriteOffer>> = favouriteOffersDao.getFavouriteOffers()

    suspend fun addToFavorites(offersData: OffersData) = favouriteOffersDao.insertFavouriteOffers(
        FavouriteOffer(name = offersData.name, description = offersData.description)
    )

    suspend fun removeFromFavorites(offersData: OffersData) = favouriteOffersDao.deleteFavouriteOffer(offersData.name)
}