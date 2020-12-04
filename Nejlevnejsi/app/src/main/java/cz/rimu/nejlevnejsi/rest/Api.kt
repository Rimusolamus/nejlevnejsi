package cz.rimu.nejlevnejsi.rest

import cz.rimu.nejlevnejsi.rest.models.Offers
import cz.rimu.nejlevnejsi.rest.models.Tags
import retrofit2.http.GET

interface Api {
    @GET("/getTopOffers")
    suspend fun getTopOffers(): Offers

    @GET("/getTags")
    suspend fun getTags(): Tags
}