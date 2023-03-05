package endava.project.starstruck.ws

import retrofit2.Call
import retrofit2.http.GET

interface WsDailyService {
    @GET("apod?api_key=yAzp7ZztVhXKiRqBTPok6gV1fIfpbHu3hBFamjgD")
    fun getDaily(): Call<WsDailyResponse>

}