package br.com.becare

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.entities.Hospital
import br.com.becare.entities.MedicalEntity
import br.com.becare.service.RetrofitFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

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
                    Log.i("Match for $keyword", it.get(0).toString())
                    intent.putExtra("HospitalEntity", it)
                } ?: Toast.makeText(this@MainActivity, "Hospital não localizado", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(call: Call<Array<Hospital>>, t: Throwable) {
                Log.e("Erro:", t?.message!!)
            }

        }
        )
    }
}
