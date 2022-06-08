package br.digitalhouse.bitdex.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.adapter.AppAdapter
import com.google.android.material.tabs.TabLayout

class AppFragment : Fragment(R.layout.fragment_app) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var adapter = AppAdapter(parentFragmentManager)
        adapter.apply { add(FavoritosFragment(), "Favorites")
            add(CryptosFragment(), "Cryptos")
            add(ConversorFragment(),"Conversor")}

        var pager = view.findViewById<ViewPager>(R.id.viewPager)
        var tabLayout = view.findViewById<TabLayout>(R.id.myTabLayout)
        pager.adapter = adapter
        tabLayout.setupWithViewPager(pager)
    }


}