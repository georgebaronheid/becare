package br.com.becare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
    }
}
