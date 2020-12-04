package cz.rimu.nejlevnejsi

import android.app.Application
import cz.rimu.nejlevnejsi.di.networkModule
import cz.rimu.nejlevnejsi.repositories.MainRepository
import cz.rimu.nejlevnejsi.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Application: Application(){
    override fun onCreate(){
        super.onCreate()
        // start Koin!
        startKoin {
            // Android context
            androidContext(this@Application)
            // modules
            modules(listOf(networkModule, viewModelModule, mainRepositoryModule))
        }
    }

    private val viewModelModule = module {
        viewModel {
            MainViewModel(get())
        }
    }

    private val mainRepositoryModule = module {
        factory { MainRepository(get()) }
    }

}