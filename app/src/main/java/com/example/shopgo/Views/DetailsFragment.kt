package com.example.shopgo.Views

import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.shopgo.R
import com.example.shopgo.databinding.FragmentDetailsBinding
import java.util.Locale

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        val currentLocale = resources.configuration.locale
        binding.languageSwitch.isChecked = currentLocale.language == "tr"

        binding.languageSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                setLocale("tr")
            } else {
                setLocale("en")
            }
            updateUI()
        }
        updateUI()
        return view
    }

    private fun setLocale(language: String) {
        val config = Configuration()
        config.setLocale(Locale(language))
        resources.updateConfiguration(config, resources.displayMetrics)
        requireActivity().recreate()
    }

    private fun updateUI() {
        binding.categorytextview.text = getString(R.string.category)
        binding.titletextview.text = getString(R.string.title)
        binding.ratingtextview.text = getString(R.string.rating)
        binding.descriptiontextview.text = getString(R.string.description)
        binding.fiyattextview.text = getString(R.string.price)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val gelenTitle = DetailsFragmentArgs.fromBundle(it).shopgo.title
            val gelenImage = DetailsFragmentArgs.fromBundle(it).shopgo.image
            val gelenDescription = DetailsFragmentArgs.fromBundle(it).shopgo.description
            val gelenPrice = DetailsFragmentArgs.fromBundle(it).shopgo.price
            val gelenCategory = DetailsFragmentArgs.fromBundle(it).shopgo.category
            val gelenRating = DetailsFragmentArgs.fromBundle(it).rating.rate

            binding.textView5.text = gelenPrice.toString()
            binding.textView4.text = gelenCategory
            binding.textView2.text = gelenTitle
            binding.textView3.text = gelenDescription
            binding.textView7.text=gelenRating.toString()
            binding.ratingBar.rating = gelenRating.toFloat()

            Glide.with(requireContext())
                .load(gelenImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: com.bumptech.glide.request.target.Target<Drawable>, isFirstResource: Boolean): Boolean {
                        e?.logRootCauses("GlideError")
                        Log.e("hata", e?.message.toString())
                        return false
                    }

                    override fun onResourceReady(resource: Drawable, model: Any, target: com.bumptech.glide.request.target.Target<Drawable>?, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                        Log.e("başarılı", "Başarılı")
                        return false
                    }
                })
                .into(binding.imageView)
        }

        binding.backImageView.setOnClickListener {
            val actions = DetailsFragmentDirections.actionDetailsFragmentToFeedFragment()
            Navigation.findNavController(it).navigate(actions)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
