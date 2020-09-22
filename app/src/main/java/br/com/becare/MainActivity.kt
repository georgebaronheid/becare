package br.com.becare

import android.content.Intent
import android.os.Bundle
import android.util.Log
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.supportActionBar!!.hide()

        retrofitTest()

    }

    private fun retrofitTest() {

        val keyword = "Hospital"
        val call = RetrofitFactory().hospitalService().getBySearchKeyword(keyword)

        call.enqueue(object : Callback<Array<Hospital>> {

            override fun onResponse(
                call: Call<Array<Hospital>>,
                response: Response<Array<Hospital>>
            ) {
                response.body()?.let {
                    Log.i("Match for $keyword", it[0].toString())
                    hospital = it[0]
                    Toast.makeText(this@MainActivity, it[0].toString(), Toast.LENGTH_LONG).show()
                } ?: Toast.makeText(this@MainActivity, "Hospital não localizado", Toast.LENGTH_LONG)
                    .show()
                detailtIntent = Intent(this@MainActivity, DetailsActivity::class.java).apply {
                    putExtra("HospitalEntity", hospital)
                }
                startActivity(detailtIntent)
            }

            override fun onFailure(call: Call<Array<Hospital>>, t: Throwable) {
                Log.e("Erro:", t?.message!!)
            }

        })
    }
}
