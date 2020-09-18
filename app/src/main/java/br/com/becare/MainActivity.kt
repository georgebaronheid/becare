package br.com.becare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.view.LandingFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()


        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_main_constraint, LandingFragment())
    }
}
