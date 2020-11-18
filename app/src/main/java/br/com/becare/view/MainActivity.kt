package br.com.becare.view

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.R
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.entities.dtos.Hospital
import br.com.becare.service.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var detailtIntent: Intent
    private var hospital: Hospital? = null
    private lateinit var keyword: String
    private lateinit var progressLoader: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()

        clickListeners()
    }

    override fun onResume() {
        super.onResume()
        progressLoader.visibility = View.GONE
    }

    private fun clickListeners() {
        er_main.setOnClickListener {
            progressLoader.visibility = View.VISIBLE
            getEntityList("EmergencyRoom")
        }

        hospital_main.setOnClickListener {
            progressLoader.visibility = View.VISIBLE
            getEntityList("Hospitals")
        }
    }


    private fun setup() {
        er_main.landing_card_header_text.text = "Pronto Socorros"
        er_main.landing_card_subtitle_text.text = "Pronto socorros próximos"
        er_main.landing_card_image.setImageResource(R.drawable.ic_logo_sus)
        hospital_main.landing_card_header_text.text = "Hospitais"
        hospital_main.landing_card_subtitle_text.text = "Hospitais próximos"
        hospital_main.landing_card_image.setImageResource(R.drawable.ic_logo_sus)
        progressLoader = progress_loader
    }

    private fun getEntityList(entityType: String) {
        val call = RetrofitFactory().hospitalService().getAllHospitals()

        call.enqueue(object : Callback<Array<Hospital>> {
            override fun onResponse(
                call: Call<Array<Hospital>>,
                response: Response<Array<Hospital>>
            ) {

                response.body()?.let {
                    Log.i("Query's size:", it.size.toString())
                    startListActivity(it, entityType)
                }
            }

            override fun onFailure(call: Call<Array<Hospital>>, t: Throwable) {
                Log.e("Erro:", t.message!!)
                Toast.makeText(this@MainActivity, "Erro do sistema", Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

    private fun startListActivity(entityList: Array<Hospital>, entityType: String) {
        Intent(this@MainActivity, SearchActivity::class.java).apply {
            putExtra("HospitalEntity", entityList as Serializable)
            putExtra("EntityType", entityType)
            startActivity(this)
        }
    }

}
