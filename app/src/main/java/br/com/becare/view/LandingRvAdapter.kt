package br.com.becare.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import br.com.becare.R
import br.com.becare.entities.dtos.LandingCard
import java.util.*

class LandingRvAdapter
    (
    private val landingCard: ArrayList<LandingCard>
) : RecyclerView.Adapter<LandingRvAdapter.ViewHolder>() {

    private var parentContext: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_main_card, parent, false)
        viewHolder.setOnClickListener { }
        return ViewHolder(viewHolder)
        this.parentContext = parent.context
    }

    override fun getItemCount(): Int {
        return landingCard.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.header?.text = landingCard[position].header
        holder.detail?.text = landingCard[position].subTitle
        holder.image?.setBackgroundResource(R.drawable.ic_baseline_local_hospital_24)


        holder.itemView.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    Toast.makeText(v?.context, "${holder.header?.text} clicked", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        )
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card = itemView.findViewById<CardView>(R.id.main_card)
        val header = itemView.findViewById<TextView>(R.id.landing_card_header_text)
        val detail = itemView.findViewById<TextView>(R.id.landing_card_subtitle_text)
        val image = itemView.findViewById<ImageView>(R.id.landing_card_image)


    }


}


