package br.com.becare.view

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import br.com.becare.R
import br.com.becare.entities.dtos.Hospital
import java.time.Duration
import java.time.LocalTime

class ListAdapter(
    val dataSet: Array<Hospital>
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(
        view: View
    ) :
        RecyclerView.ViewHolder(view) {
        val rootCard = view.findViewById<CardView>(R.id.root_card)
        val headerListCard = view.findViewById<TextView>(R.id.header_list_card)
        val subtitleListCard = view.findViewById<TextView>(R.id.subtitle_list_card)

        //        val distanceTextView = view.findViewById<TextView>(R.id.distance_text_view)
        val averageTime = view.findViewById<TextView>(R.id.average_time_text)
        val colorHolder: View =
            view.findViewById(R.id.time_constraint_holder)


    }

    //    Create new views - invoked by the layout manager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//    Create new individual view which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_card, parent, false)

        return ViewHolder(view)
    }

    //    Replace the contents of a view
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//    Get element from a dataset at this positino and repĺace its contents with the same
//    position of the dataSet list
        holder.rootCard.setOnClickListener {
            Intent(it.context, DetailsActivity::class.java).apply {
                putExtra("HospitalEntity", dataSet[holder.layoutPosition])
                it.context.startActivity(this)
            }
        }
        holder.headerListCard?.text = dataSet[position].nome
        holder.subtitleListCard?.text = dataSet[position].logradouro
        holder.averageTime?.text = LocalTime.MIN.plus(
            Duration.ofMinutes(
                dataSet[position].fila.toLong()
            )
        ).toString()
        if (dataSet[position].fila.toDouble() <= 60) holder.colorHolder.setBackgroundColor(
            Color.parseColor(
                "#20BFF8"
            )
        ) else holder.colorHolder.setBackgroundColor(
            Color.parseColor(
                "#FFF80C0C"
            )
        )

    }

    override fun getItemCount(): Int = dataSet.count()

}
