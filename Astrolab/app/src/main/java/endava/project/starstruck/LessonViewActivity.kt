package endava.project.starstruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.tabs.TabLayout
import endava.project.starstruck.databinding.ActivityLessonViewBinding
import endava.project.starstruck.databinding.ActivityMainBinding
import java.io.File
import java.nio.charset.Charset

class LessonViewActivity : AppCompatActivity() {

    private val sampleHtmlString = "<!DOCTYPE html><html><body><h1>My First Heading</h1><p>My first paragraph.</p></body></html>";

    var astronomyBookDataHtmlString : String = ""

    fun readFile() : String
    {
        //from assets folder - if it were in res/raw folder, then baseContext.resources.openRawResource(R.raw.FILENAME)
        val input_stream = baseContext.assets.open("sample.html")
        var text = input_stream.readBytes().toString(Charset.defaultCharset())

        return text
    }

    lateinit var binding: ActivityLessonViewBinding
    lateinit var webView : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLessonViewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(R.layout.activity_lesson_view)

        try {
            astronomyBookDataHtmlString = readFile()
        }
        catch (e : Exception) {
            Log.i("FILEHELP", "Exception when reading file $e")
        }
        setupWebView()
    }

    private fun setupWebView() {

        //do not use binding to get webView, always blank
        webView = findViewById(R.id.webView)//binding.webView

        //ensure it doesn't open in any external web client like Chrome
        webView.webViewClient = WebViewClient()

        webView.apply {

            Log.i("ASTRO", astronomyBookDataHtmlString)
            //loadData(astronomyBookDataHtmlString, "text/html", "utf-8")
            //if not like this, CSS in style tags makes webview blank
            loadData(Base64.encodeToString(astronomyBookDataHtmlString.toByteArray(), Base64.DEFAULT), "text/html", "base64")

            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
        }
    }
}