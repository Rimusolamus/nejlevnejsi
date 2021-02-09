package cz.rimu.nejlevnejsi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import cz.rimu.nejlevnejsi.db.models.FavouriteOffer

@Dao
interface FavouriteOffersDao {

    @Query("Select * from FavouriteOffer")
    fun getFavouriteOffers(): LiveData<List<FavouriteOffer>>

    @Insert
    suspend fun insertFavouriteOffers(favouriteOffer: FavouriteOffer)

    @Update
    suspend fun updateFavouriteOffer(favouriteOffer: FavouriteOffer)

    @Delete
    suspend fun deleteFavouriteOffer(favouriteOffer: FavouriteOffer)

}