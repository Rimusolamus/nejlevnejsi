package cz.rimu.nejlevnejsi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.rimu.nejlevnejsi.db.dao.FavouriteOffersDao
import cz.rimu.nejlevnejsi.db.models.FavouriteOffer

@Database(entities = [FavouriteOffer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouriteOffersDao(): FavouriteOffersDao
}