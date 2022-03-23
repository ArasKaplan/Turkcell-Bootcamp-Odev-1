package com.araskaplan.e_ticaret_urun_akisi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(var list: ArrayList<Product>,var isLogged:Boolean,var sumPriceTextView: TextView):RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.product_card,parent,false)
        return CardViewHolder(v,isLogged)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindData(list[position])
        holder.prodButton.setOnClickListener {
            Toast.makeText(holder.itemView.context,"DENEME",Toast.LENGTH_SHORT).show()
            sum+=list[position].price
            sumPriceTextView.text=sum.toString()
        }
        holder.prodImage.setOnClickListener {
            var intent=Intent(holder.itemView.context,ProductDetailsActivity::class.java)
            intent.putExtra("name",list[position].prod_name)
            intent.putExtra("brand",list[position].brand)
            intent.putExtra("price",list[position].price)
            intent.putExtra("details",list[position].description)
            intent.putExtra("image",list[position].image)
            intent.putExtra("isLogged",isLogged)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = list.size
}


class CardViewHolder(itemView:View,isLogged: Boolean):RecyclerView.ViewHolder(itemView){
    var prodImage:ImageView
    var prodBrand:TextView
    var prodName:TextView
    var prodPrice:TextView
    var prodButton:Button

    init {
        prodBrand=itemView.findViewById(R.id.card_brand)
        prodImage=itemView.findViewById(R.id.card_img)
        prodName=itemView.findViewById(R.id.card_prodName)
        prodPrice=itemView.findViewById(R.id.card_price)
        prodButton=itemView.findViewById(R.id.prod_card_button)
        if (!isLogged){
            prodButton.visibility=View.GONE
        }
    }

    fun bindData(product: Product){
        prodPrice.text="₺${product.price.toString()}"
        prodName.text=product.prod_name
        prodBrand.text=product.brand
        prodImage.setImageResource(product.image)
    }

}