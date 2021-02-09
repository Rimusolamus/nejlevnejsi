package cz.rimu.nejlevnejsi.db.dao

import androidx.room.*
import cz.rimu.nejlevnejsi.db.models.FavouriteOffer

@Dao
interface FavouriteOffersDao {

    @Query("Select * from FavouriteOffer")
    suspend fun getFavouriteOffers(): List<FavouriteOffer>

    @Insert
    suspend fun insertFavouriteOffers(favouriteOffer: FavouriteOffer)

    @Update
    suspend fun updateFavouriteOffer(favouriteOffer: FavouriteOffer)

    @Delete
    suspend fun deleteFavouriteOffer(favouriteOffer: FavouriteOffer)

}