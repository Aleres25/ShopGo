package com.example.shopgo.Views

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopgo.Adapters.HorizontalAdapter
import com.example.shopgo.Adapters.VerticalAdapter
import com.example.shopgo.R
import com.example.shopgo.ViewModels.FeedViewModel
import com.example.shopgo.databinding.FragmentFeedBinding
import com.google.android.material.snackbar.Snackbar

class FeedFragment : Fragment() {
    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!

    private var viewModel = FeedViewModel()

    private val shopGoAdapter = VerticalAdapter(arrayListOf())
    private lateinit var shopGoAdapter2: HorizontalAdapter

    private var isDarkMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? { // Inflate the layout for this fragment
        _binding = FragmentFeedBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (internetVarmi(requireContext())) {
            binding.switch1.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE

            binding.switch1.setOnCheckedChangeListener { _, isChecked ->
                isDarkMode = isChecked
                updateTheme()
            }

            updateTheme()

            viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
            viewModel.refreshData()


            binding.verticalRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.verticalRecyclerView.adapter = shopGoAdapter

            binding.horizontalRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            shopGoAdapter2 = HorizontalAdapter(arrayListOf()) { secilmisKategori ->
                updateVerticalAdapter(secilmisKategori)
            }

            binding.horizontalRecyclerView.adapter = shopGoAdapter2
            observeLivedata()
        } else {
            binding.switch1.visibility = View.INVISIBLE
            binding.progressBar.visibility = View.VISIBLE

            Snackbar.make(binding.verticalRecyclerView, "İnternet Bağlantınızı Kontrol Ediniz", Snackbar.LENGTH_INDEFINITE).setAction("Kontrol Et", {
                val intent = Intent(Settings.ACTION_SETTINGS)
                startActivity(intent)
            }).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun observeLivedata() {
        viewModel.shopLivedata.observe(viewLifecycleOwner, { observer ->
            observer?.let {
                shopGoAdapter.updateList(observer)

                shopGoAdapter2.updateShopGoList(observer)
            }
        })
    }

    private fun updateVerticalAdapter(selectedCategory: String) {
        val filteredList = if (selectedCategory.isNotEmpty()) {
            viewModel.shopLivedata.value?.filter { it.category == selectedCategory } ?: emptyList()
        } else {
            viewModel.shopLivedata.value ?: emptyList()
        }
        shopGoAdapter.updateList(filteredList)
    }

    fun internetVarmi(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork?.let { connectivityManager.getNetworkCapabilities(it) }
            networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo?.isConnected == true
        }
    }

    private fun updateTheme() {
        if (isDarkMode) {
            binding.horizontalRecyclerView.setBackgroundColor(resources.getColor(android.R.color.black))
            binding.verticalRecyclerView.setBackgroundColor(resources.getColor(android.R.color.black))
            binding.switch1.setTextColor(resources.getColor(R.color.white))
            requireActivity().window.decorView.setBackgroundColor(resources.getColor(android.R.color.black))
            binding.root.setBackgroundColor(resources.getColor(android.R.color.black))
        } else {
            binding.horizontalRecyclerView.setBackgroundColor(resources.getColor(android.R.color.holo_orange_light))
            binding.verticalRecyclerView.setBackgroundColor(resources.getColor(android.R.color.white))
            binding.switch1.setTextColor(resources.getColor(R.color.black))
            requireActivity().window.decorView.setBackgroundColor(resources.getColor(android.R.color.white))
            binding.root.setBackgroundColor(resources.getColor(android.R.color.white))
        }
    }
}