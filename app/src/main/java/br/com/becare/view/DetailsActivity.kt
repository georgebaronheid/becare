package br.com.becare.view

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.R
import br.com.becare.databinding.ActivityDetailsBinding
import br.com.becare.entities.Hospital
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class DetailsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var map: GoogleMap
    private lateinit var hospitalObject: Hospital

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        setMap()
        activitySettings()

    }

    private fun setMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun activitySettings() {
        hospitalObject = intent.getSerializableExtra("HospitalEntity") as Hospital
        with(hospitalObject) {
            binding.titleCard.text = nome
            binding.address.text = logradouro
            binding.hintTextView.text = "Selecione a marcação para acessar rota"
            binding.phonenumber.text = telefone
            binding.expectedTime.text = fila
        }
        binding.backArrow.setOnClickListener{
            finish()
        }
    }

    override fun onMapReady(p0: GoogleMap) {
        Log.d("GDP", "OnMapReady")
        map = p0




        val location = LatLng(hospitalObject.latitude, hospitalObject.longitude)
        map.addMarker(
            MarkerOptions()
                .position(location)
                .title(hospitalObject.nome)
                .snippet(hospitalObject.logradouro)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        ).showInfoWindow()
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.5F))

        map.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(p0: Marker?): View? {
                return null
            }

            override fun getInfoContents(marker: Marker?): View {
                val info = LinearLayout(applicationContext)
                info.orientation = LinearLayout.VERTICAL

                //--Título
                val title = TextView(applicationContext)
                title.setTextColor(Color.BLACK)
                title.gravity = Gravity.START
                title.setTypeface(null, Typeface.BOLD)
                title.text = marker!!.title

                //--Complemento
                val snippet = TextView(applicationContext)
                snippet.setTextColor(Color.GRAY)
                snippet.text = marker.snippet

                //--Adiciona o titulo e o complemento na marca
                info.addView(title)
                info.addView(snippet)

                return info
            }

        })

    }

}
