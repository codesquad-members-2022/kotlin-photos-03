package com.example.photos

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class StoreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val recyclerView= findViewById<RecyclerView>(R.id.recycler_doodle_view)
        val button = findViewById<ImageButton>(R.id.button)
        val buttonBack = findViewById<ImageButton>(R.id.button_back)
        val adapter = DoodleAdapter(JSonDiffCallback())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, 4)
        CoroutineScope(Dispatchers.Main).launch {
            val json = withContext(Dispatchers.IO) { // changeJson 값을 대입받는다, 코루틴중에 통신기능은 IO내부에서 해야함.. io는 뭐지?
                changeJson(networking())
            }
            json.let{
                adapter.submitList(it)
            }
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }
}

fun networking(): String {
    var str = ""
    try {
        val urlText = URL("https://public.codesquad.kr/jk/doodle.json")

        val urlConnection = urlText.openConnection() as HttpURLConnection
        urlConnection.requestMethod = "GET"
        if (urlConnection.responseCode == HttpURLConnection.HTTP_OK) {
            val streamReader = InputStreamReader(urlConnection.inputStream)
            val bufferd = BufferedReader(streamReader)

            val content = StringBuilder()
            while (true) {
                val line = bufferd.readLine() ?: break
                str = content.append(line).toString()

                bufferd.close()
                urlConnection.disconnect()
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return str
}

suspend fun changeJson(json: String): List<JsonImage> {
    val photolist = mutableListOf<JsonImage>()
    val jsonArray = JSONArray(json)

    for (index in 0 until jsonArray.length()) {
        val jsonObject = jsonArray.getJSONObject(index)

        val title = jsonObject.getString("title")
        val image = jsonObject.getString("image").toUri()

        photolist.add(JsonImage(title, image))
        Log.d("josondata", "$title $image")

    }
    return photolist
}


