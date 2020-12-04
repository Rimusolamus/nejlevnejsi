package cz.rimu.nejlevnejsi.repositories

import cz.rimu.nejlevnejsi.rest.Api
import cz.rimu.nejlevnejsi.rest.models.Offers

class MainRepository(private val api: Api) {
    suspend fun getTags() = api.getTags()
    suspend fun getTopOffers(): Offers {
        return try {
            api.getTopOffers()
        }
        catch(e: Exception) {
            Offers(listOf())
        }

    }
}