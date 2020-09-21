package br.com.becare.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.becare.R
import br.com.becare.databinding.LandingFragmentBinding

class LandingFragment : Fragment() {

    private lateinit var binding: LandingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LandingFragmentBinding.inflate(layoutInflater)

        return inflater.inflate(R.layout.landing_fragment, container, false)
    }

}
