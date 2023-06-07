package com.ozgurbaykal.firstkotlinapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.ozgurbaykal.firstkotlinapplication.databinding.FragmentTutorialPage2Binding

class TutorialPage2 : Fragment(R.layout.fragment_tutorial_page1) {

    private val TAG = "_TutorialPage1"

    private var _binding: FragmentTutorialPage2Binding? = null
    private val binding get() = _binding!!

    private lateinit var continueButton : Button


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorialPage2Binding.inflate(inflater, container, false)
        val view = binding.root

        continueButton = binding.continueButton

        continueButton.setOnClickListener {
            Log.i(TAG, "continueButton clicked")

            val intent = Intent (getActivity(), MainActivity::class.java)
            startActivity(intent)
            getActivity()?.finish() // Finish the current Activity

        }

        val myItems = listOf(
            MyItem("Show All", R.drawable.skincare_icon),
            MyItem("Perfume", R.drawable.perfume_icon),
            MyItem("Moisturizer", R.drawable.moisturizer_icon),
            MyItem("Shampoo", R.drawable.shampoo_icon),
            MyItem("Gift Cards", R.drawable.gift_card_icon),
            MyItem("Toner", R.drawable.toner_icon),
            MyItem("Face oils", R.drawable.serum_icon),
            MyItem("Foundation", R.drawable.foundation_icon),
            MyItem("Suncare", R.drawable.sun_cream_icon),
            MyItem("Tools", R.drawable.face_roller_icon),

        )

        val recyclerView = binding.myRecyclerView
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2) // This will make the list 2 columns
        recyclerView.adapter = MyAdapter(myItems)


        return view
    }


}

data class MyItem(val text: String, val iconResId: Int)

class MyAdapter(private val items: List<MyItem>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView = view.findViewById(R.id.card_view)
        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_list_item_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.imageView.setImageResource(item.iconResId)
        holder.textView.text = item.text

        // Add random margin
        val margin = (10..50).random()
        val marginTop = (30..50).random()

        val layoutParams = holder.cardView.layoutParams as RecyclerView.LayoutParams
        layoutParams.setMargins(margin, marginTop, margin, layoutParams.bottomMargin)
        holder.cardView.layoutParams = layoutParams


        holder.cardView.setOnClickListener {
            val colorGreen = ContextCompat.getColor(holder.cardView.context, R.color.app_main_color_green)
            val colorWhite = ContextCompat.getColor(holder.cardView.context, R.color.white)
            val colorBlack = ContextCompat.getColor(holder.cardView.context, R.color.black)

            if (holder.cardView.cardBackgroundColor.defaultColor == colorGreen) {
                holder.cardView.setCardBackgroundColor(colorWhite)
                holder.textView.setTextColor(colorBlack)

            } else {
                holder.cardView.setCardBackgroundColor(colorGreen)
                holder.textView.setTextColor(colorWhite)

            }
        }
    }


    override fun getItemCount() = items.size
}

