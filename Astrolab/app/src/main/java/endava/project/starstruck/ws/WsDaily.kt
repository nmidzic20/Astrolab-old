package endava.project.starstruck.ws

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WsDaily {
    const val BASE_URL = "https://api.nasa.gov/planetary/"
    private var instance: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val dailyService = instance.create(WsDailyService::class.java)
}