package cz.rimu.nejlevnejsi.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import cz.rimu.nejlevnejsi.R
import cz.rimu.nejlevnejsi.ui.addOffer.AddOfferActivity
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {

	companion object {
		fun newInstance() = MainFragment()
	}

	private val mainViewModel: MainViewModel by viewModel()

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
							  savedInstanceState: Bundle?): View {
		val view = inflater.inflate(R.layout.main_fragment, container, false)
		val offerList = view.findViewById<RecyclerView>(R.id.offers)

		val tags = view.findViewById<ChipGroup>(R.id.tags)
		mainViewModel.tags.observe(viewLifecycleOwner) {
			tags.removeAllViews()
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
				tagView.text = tag
				//add onclick events for tags
				tags.addView(tagView)
			}
		}

		// top offers
		val offersAdapter = OffersAdapter()
		offerList.layoutManager = LinearLayoutManager(context)
		offerList.adapter = offersAdapter
		mainViewModel.topOffers.observe(viewLifecycleOwner) {
			offersAdapter.updateOffers(it.offers)
			offersAdapter.notifyDataSetChanged()
		}

		return view
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		view?.findViewById<FloatingActionButton>(R.id.addOffer)?.setOnClickListener {
			val intent = Intent(context, AddOfferActivity::class.java)
			startActivity(intent)
			activity?.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}
	}

}