package dk.deepak.weather

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


class MainActivity : AppCompatActivity() {


    lateinit var cityName : TextView
    lateinit var btnStart : EditText
    lateinit var button : ImageButton
    lateinit var back : ImageView
    lateinit var weather: TextView
    lateinit var temp : TextView
    lateinit var max_temp : TextView
    lateinit var min_temp : TextView
    lateinit var pressure : TextView
    lateinit var humidity : TextView
    lateinit var wind : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         cityName= findViewById(R.id.cityName)
        btnStart = findViewById(R.id.enterCity)
        button= findViewById(R.id.ibButton)
        back = findViewById(R.id.back)
        weather = findViewById(R.id.weather)
        temp = findViewById(R.id.temp)
        max_temp = findViewById(R.id.max_temp)
        min_temp = findViewById(R.id.min_temp)
        pressure  = findViewById(R.id.pressure)
        humidity = findViewById(R.id.humidity)
        wind = findViewById(R.id.wind)


        val lat = intent.getStringExtra("lat")
        val long = intent.getStringExtra("long")

        if (btnStart.text.isEmpty()) {
           getJsonData(lat, long)




            button.setOnClickListener {
                if (btnStart.text.isEmpty()) {
                    Toast.makeText(this, "Please enter your city", Toast.LENGTH_LONG).show()
                } else {
                    var city = btnStart.getText().toString()

                    window.statusBarColor = Color.parseColor("#1383c3")
                    getJsonData(lat, long, city)

                }
            }
        }


    }

    private fun getJsonData(lat: String?, long: String?) {
        val queue = Volley.newRequestQueue(this)
        val url ="https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${long}&appid=87bdb7bf670488a4aa0d60f71df46c3d"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                setValues(response = response)

            },
            { Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show() })

        queue.add(jsonRequest)
    }


    private fun getJsonData(lat: String?, long: String?, city: String) {

        val queue = Volley.newRequestQueue(this)
        val url ="https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=87bdb7bf670488a4aa0d60f71df46c3d"
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                setValues(response = response)
            },
            { Toast.makeText(this, "Please Enter Valid City", Toast.LENGTH_LONG).show() })

        queue.add(jsonRequest)
    }


    @SuppressLint("SuspiciousIndentation")
    private fun setValues(response: JSONObject) {
        cityName.text = response.getString("name")

        weather.text = response.getJSONArray("weather").getJSONObject(0).getString("main")

        var tempr = response.getJSONObject("main").getString("temp")
        tempr = ((((tempr).toFloat() - 273.15)).toInt()).toString()
        temp.text = "${tempr}°C"

        var mintemp = response.getJSONObject("main").getString("temp_min")
        mintemp = ((((mintemp).toFloat() - 273.15)).toInt()).toString()
        min_temp.text = "Min: " + mintemp + "°C"
        var maxtemp = response.getJSONObject("main").getString("temp_max")
        maxtemp = ((((maxtemp).toFloat() - 273.15)).toInt()).toString()
        max_temp.text = "Max: " + maxtemp + "°C"

        pressure.text = response.getJSONObject("main").getString("pressure")
        humidity.text = response.getJSONObject("main").getString("humidity") + "%"
        wind.text = response.getJSONObject("wind").getString("speed")+" km/h"

        var isDay = response.getJSONArray("weather").getJSONObject(0).getString("icon")
               if (isDay == "01d") {
            back.setBackgroundResource(R.drawable.a01d)
        } else if(isDay == "01n") {
            back.setBackgroundResource(R.drawable.cloudy)
        }else if(isDay == "02d") {
                   back.setBackgroundResource(R.drawable.a02d)
        } else if(isDay == "02n") {
            back.setBackgroundResource(R.drawable.a02n)
        } else if(isDay == "03d") {
            back.setBackgroundResource(R.drawable.a03d)
        } else if(isDay == "03n") {
            back.setBackgroundResource(R.drawable.a03d)
        } else if(isDay == "04d") {
            back.setBackgroundResource(R.drawable.a04d)
        } else if(isDay == "04n") {
            back.setBackgroundResource(R.drawable.a04d)
        }else if(isDay == "09d") {
            back.setBackgroundResource(R.drawable.a09d)
        } else if(isDay == "09n") {
            back.setBackgroundResource(R.drawable.a09d)
        }else if(isDay == "10d") {
            back.setBackgroundResource(R.drawable.a10d)
        } else if(isDay == "10n") {
            back.setBackgroundResource(R.drawable.a10d)
        } else if(isDay == "11d") {
            back.setBackgroundResource(R.drawable.a11d)
        } else if(isDay == "11n") {
            back.setBackgroundResource(R.drawable.a11d)
        }else if(isDay == "13d") {
            back.setBackgroundResource(R.drawable.a13d)
        } else if(isDay == "13n") {
            back.setBackgroundResource(R.drawable.a13d)
        } else if(isDay == "50d") {
            back.setBackgroundResource(R.drawable.a50n)
        }else if(isDay == "50n") {
            back.setBackgroundResource(R.drawable.a50n)
        }
    }
//        icon(isDay);
//        val condition =
//            response.getJSONObject("current").getJSONObject("condition").getString("text")
//        val conditionIcon =
//            response.getJSONObject("current").getJSONObject("condition").getString("icon")
//        Picasso.get().load("http:$conditionIcon").into(iconIV)
//        conditionTV.setText(condition)

    }


//    private fun icon(isDay: Int) {
//        if (isDay == 1) {
////           return "logo";
//            back.setBackgroundResource(R.drawable.day)
////            Picasso.get().load("https://unsplash.com/photos/uKJRK38KrBE").into(back)
//        } else {
////            return "logo";
//            back.setBackgroundResource(R.drawable.night)
////            Picasso.get().load("https://unsplash.com/photos/ilfsT5p_qvA").into(back)
//        }
//    }

