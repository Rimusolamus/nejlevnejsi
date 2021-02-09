package cz.rimu.nejlevnejsi.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cz.rimu.nejlevnejsi.R
import cz.rimu.nejlevnejsi.rest.models.OffersData

class OffersAdapter(private val addToFavouriteOffer: (OffersData) -> Unit) :
    RecyclerView.Adapter<OffersAdapter.OffersViewHolder>() {

    private var offers: List<OffersData> = listOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): OffersViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.offers_row_item, viewGroup, false)
        return OffersViewHolder(view, addToFavouriteOffer)
    }

    override fun getItemCount(): Int {
        return offers.size
    }

    class OffersViewHolder(view: View, val addToFavouriteOffer: (OffersData) -> Unit) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.offer_title)
        val description: TextView = view.findViewById(R.id.offer_description)
        val star: ImageView = view.findViewById(R.id.star)
    }

    override fun onBindViewHolder(holder: OffersViewHolder, position: Int) {
        holder.title.text = offers[position].name
        holder.description.text = offers[position].description
        holder.star.setOnClickListener {
            holder.addToFavouriteOffer(offers[position])
        }
    }

    fun updateOffers(newOffers: List<OffersData>) {
        this.offers = newOffers
    }
}