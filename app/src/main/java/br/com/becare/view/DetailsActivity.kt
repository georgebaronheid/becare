package br.com.becare.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.becare.R
import br.com.becare.databinding.ActivityDetailsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()
    }

    override fun onMapReady(p0: GoogleMap?) {
        TODO("Not yet implemented")
    }
}
