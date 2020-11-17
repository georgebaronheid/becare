package br.com.becare.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.entities.dtos.Hospital
import br.com.becare.service.RetrofitFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var detailtIntent: Intent
    private var hospital: Hospital? = null
    private lateinit var keyword: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.supportActionBar!!.hide()
        this.supportActionBar!!.subtitle = "BeCare"


        er_main.landing_card_header_text.text = "Pronto Socorros"
        er_main.landing_card_subtitle_text.text = "Pronto socorros próximos"
        hospital_main.landing_card_header_text.text = "Hospitais"
        hospital_main.landing_card_subtitle_text.text = "Hospitais próximos"

        er_main.setOnClickListener {
            getEntityList("EmergencyRoom")
        }

        hospital_main.setOnClickListener {
            getEntityList("Hospitals")
        }
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

    //    private fun createMapActivity(hospital: Hospital) {
//        Intent(this@MainActivity, DetailsActivity::class.java).apply {
//            putExtra("HospitalEntity", hospital)
//            startActivity(this)
//        }
//    }

    //    private fun searchKeyword(keyword: String) {
//
//        val call = RetrofitFactory().hospitalService().getBySearchKeyword(keyword)
//
//        call.enqueue(object : Callback<Array<Hospital>> {
//
//            override fun onResponse(
//                call: Call<Array<Hospital>>,
//                response: Response<Array<Hospital>>
//            ) {
//                response.body()?.let {
//                    Log.i("Match(s) for $keyword", it.size.toString())
//                    if (it.size.equals(1)) createMapActivity(it[0])
//                    else Toast.makeText(
//                        this@MainActivity,
//                        "Muitos resultados, busque com mais precisão",
//                        Toast.LENGTH_LONG
//                    ).show()
//                } ?: Toast.makeText(this@MainActivity, "Hospital não localizado", Toast.LENGTH_LONG)
//                    .show()
//            }
//
//            override fun onFailure(call: Call<Array<Hospital>>, t: Throwable) {
//                Log.e("Erro:", t.message!!)
//                Toast.makeText(this@MainActivity, "Erro do sistema", Toast.LENGTH_LONG)
//                    .show()
//            }
//
//        })
//    }

//    override fun onResume() {
//        super.onResume()
//        binding.landingSearchView.clearFocus()
//    }


//    private fun setSearchView() {
//        binding.landingSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                Log.i("Search:", "$query")
//                keyword = query as String
//                binding.landingSearchView.clearFocus()
//                binding.landingSearchView.setQuery("", false)
//                keyword.apply { searchKeyword(keyword) }
//                return true
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                return false
//            }
//        })
//    }


}
