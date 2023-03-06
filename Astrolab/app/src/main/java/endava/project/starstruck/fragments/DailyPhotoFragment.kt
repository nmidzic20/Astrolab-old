package endava.project.starstruck.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import endava.project.starstruck.R
import endava.project.starstruck.ws.WsDaily
import endava.project.starstruck.ws.WsDailyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DailyPhotoFragment : Fragment() {

    private lateinit var loadingCircle: ProgressBar
    private  lateinit var tvTitle : TextView
    private  lateinit var tvDescription : TextView
    private  lateinit var tvDateCopyright : TextView
    private val ws = WsDaily.dailyService

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daily_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadingCircle = view.findViewById(R.id.pb_daily_photo_loading)
        tvTitle = view.findViewById(R.id.tv_title)
        tvDescription = view.findViewById(R.id.tv_description)
        tvDateCopyright = view.findViewById(R.id.tv_date_copyright)

        loadDailyPhoto()
    }

    private fun loadDailyPhoto()
    {
        loadingCircle.isVisible = true

        ws.getDaily().enqueue(
            object : Callback<WsDailyResponse> {
                override fun onResponse(call: Call<WsDailyResponse>?, response: Response<WsDailyResponse>?) {
                    if (response?.isSuccessful == true) {
                        val responseBody = response.body()
                        tvTitle.text = responseBody.title
                        tvDescription.text = responseBody.explanation
                        tvDateCopyright.text = responseBody.date + " " + responseBody.copyright
                        loadingCircle.isVisible = false
                    } else {
                        displayWebServiceErrorMessage()
                    }
                    loadingCircle.isVisible = false
                }
                override fun onFailure(call: Call<WsDailyResponse>?, t: Throwable?) {
                    displayWebServiceErrorMessage()
                    loadingCircle.isVisible = false
                }
            }
        )
    }

    private fun displayWebServiceErrorMessage() {
        Toast.makeText(
            context,
            getString(R.string.daily_ws_err_msg),
            Toast.LENGTH_LONG
        ).show()
    }


}