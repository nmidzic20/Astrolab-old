package endava.project.starstruck.ws

import com.google.gson.annotations.SerializedName

data class WsDailyResponse (
    var copyright: String?,
    var date: String?,
    var explanation: String?,
    var hdurl: String?,
    @SerializedName("media_type") var mediaType: String?,
    @SerializedName("service_version") var serviceVersion: String?,
    var title: String?,
    var url: String?
)
