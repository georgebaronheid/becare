package br.com.becare

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import br.com.becare.databinding.ActivityMainBinding
import br.com.becare.view.MainActivityAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.supportActionBar!!.hide()

        binding.activityMainViewPager.adapter = MainActivityAdapter(this)

        binding.activityMainBottomNavView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_menu_home -> binding.activityMainViewPager.setCurrentItem(0, true )
                R.id.nav_menu_hospital -> binding.activityMainViewPager.setCurrentItem(1, true)
            }
            true
        }
        binding.activityMainViewPager.registerOnPageChangeCallback(object :
        ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.activityMainBottomNavView.menu.getItem(position).isChecked = true
            }
        })
    }
}
