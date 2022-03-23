package com.araskaplan.e_ticaret_urun_akisi

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Category_Rec_Adapter(var list:ArrayList<String>,var pList:ArrayList<Product>,var linkedRCV:RecyclerView,var isLogged:Boolean,var cartPriceTextView: TextView): RecyclerView.Adapter<CatViewHolder>() {
    var selectedItemPos=0
    var lastItemSelectedPos=-1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.category_rec_view_element,parent,false)
        return CatViewHolder(v)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.bindData(list.get(position))
        holder.catName.background=AppCompatResources.getDrawable(holder.itemView.context,R.drawable.category_round_corner_shape)
        holder.catName.setTextColor(holder.itemView.context.resources.getColor(R.color.black))

        if (selectedItemPos==position){
            holder.catName.setTextColor(holder.itemView.context.resources.getColor(R.color.colorSecondary))
            holder.catName.background=AppCompatResources.getDrawable(holder.itemView.context,R.drawable.category_selected_round_corner_shape)
        }

        holder.itemView.setOnClickListener {
            lastItemSelectedPos=selectedItemPos
            selectedItemPos=position
            notifyItemChanged(lastItemSelectedPos)
            notifyItemChanged(selectedItemPos)
            linkedRCV.apply {
                layoutManager=GridLayoutManager(holder.itemView.context,4)
                adapter=CardAdapter(pList.filter {
                    it.category.equals(list[position])} as ArrayList<Product>,
                isLogged,cartPriceTextView)
            }

        }

    }

    override fun getItemCount(): Int = list.size

}


class CatViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    var catName:TextView
    init {
        catName=itemView.findViewById(R.id.cat_name)
    }

    fun bindData(name:String){
        catName.text=name
    }

}