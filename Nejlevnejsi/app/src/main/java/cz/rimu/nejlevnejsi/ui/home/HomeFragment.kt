package cz.rimu.nejlevnejsi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import cz.rimu.nejlevnejsi.R
import cz.rimu.nejlevnejsi.databinding.MainFragmentBinding
import cz.rimu.nejlevnejsi.rest.models.OffersData
import cz.rimu.nejlevnejsi.ui.addOffer.AddOfferActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

	companion object {
		fun newInstance() = HomeFragment()
	}

	private val mainViewModel: MainViewModel by viewModel()

	lateinit var viewBinding: MainFragmentBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View {
		viewBinding = MainFragmentBinding.inflate(inflater, container, false)
		val view = viewBinding.root

		// tags
		mainViewModel.tags.observe(owner = viewLifecycleOwner) {
			viewBinding.tags.removeAllViews()
			for (tag in it.tags) {
				val tagView = Chip(context)
				val chipDrawable = context?.let { it1 ->
					ChipDrawable.createFromAttributes(
						it1,
						null,
						0,
						R.style.Widget_MaterialComponents_Chip_Choice
					)
				}
				if (chipDrawable != null) {
					tagView.setChipDrawable(chipDrawable)
				}
				tagView.text = tag.tagName
				//add onclick events for tags
				viewBinding.tags.addView(tagView)
			}
		}

		// user favorites
		val favouriteOfferAdapter = OffersAdapter(::addToFavorites)
		viewBinding.favouriteOffers.layoutManager = LinearLayoutManager(context)
		viewBinding.favouriteOffers.adapter = favouriteOfferAdapter
		mainViewModel.favouriteOffers.observe(owner = viewLifecycleOwner) {
			favouriteOfferAdapter.updateOffers(it)
			favouriteOfferAdapter.notifyDataSetChanged()
		}

		// top offers
		val offersAdapter = OffersAdapter(::addToFavorites)
		viewBinding.offers.layoutManager = LinearLayoutManager(context)
		viewBinding.offers.adapter = offersAdapter
		mainViewModel.topOffers.observe(owner = viewLifecycleOwner) {
			offersAdapter.updateOffers(it.offers)
			offersAdapter.notifyDataSetChanged()
		}

		return view
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		viewBinding.addOffer.setOnClickListener {
			val intent = Intent(context, AddOfferActivity::class.java)
			startActivity(intent)
			activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
		}
	}

	private fun addToFavorites(offersData: OffersData) {
		mainViewModel.addToFavorites(offersData)
	}

}