package cz.rimu.nejlevnejsi.di

import androidx.room.Room
import cz.rimu.nejlevnejsi.db.AppDatabase
import org.koin.dsl.module

const val DATABASE_NAME = "nejlevnejsi"

val dbModule = module {
    single<AppDatabase> {
        val builder = Room
            .databaseBuilder(
                get(),
                AppDatabase::class.java,
                DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
        builder.build()
    }

    single { get<AppDatabase>().favouriteOffersDao() }

}