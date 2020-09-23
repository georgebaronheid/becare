package br.com.becare

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.entities.Hospital
import br.com.becare.service.RetrofitFactory
import br.com.becare.view.DetailsActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        setSearchView()
    }

    override fun onResume() {
        super.onResume()
        binding.landingSearchView.clearFocus()
    }


    private fun setSearchView() {
        binding.landingSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i("Search:", "$query")
                keyword = query as String
                binding.landingSearchView.clearFocus()
                binding.landingSearchView.setQuery("", false)
                keyword.apply { searchKeyword(keyword) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchKeyword(keyword: String) {

        val call = RetrofitFactory().hospitalService().getBySearchKeyword(keyword)

        call.enqueue(object : Callback<Array<Hospital>> {

            override fun onResponse(
                call: Call<Array<Hospital>>,
                response: Response<Array<Hospital>>
            ) {
                response.body()?.let {
                    Log.i("Match(s) for $keyword", it.size.toString())
                    if (it.size.equals(1)) createMapActivity(it[0])
                    else Toast.makeText(
                        this@MainActivity,
                        "Muitos resultados, busque com mais precisão",
                        Toast.LENGTH_LONG
                    ).show()
                } ?: Toast.makeText(this@MainActivity, "Hospital não localizado", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(call: Call<Array<Hospital>>, t: Throwable) {
                Log.e("Erro:", t.message!!)
                Toast.makeText(this@MainActivity, "Erro do sistema", Toast.LENGTH_LONG)
                    .show()
            }

        })
    }

    private fun createMapActivity(hospital: Hospital) {
        Intent(this@MainActivity, DetailsActivity::class.java).apply {
            putExtra("HospitalEntity", hospital)
            startActivity(this)
        }
    }
}
