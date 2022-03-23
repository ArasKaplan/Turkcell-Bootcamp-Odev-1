package com.araskaplan.e_ticaret_urun_akisi

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.araskaplan.e_ticaret_urun_akisi.databinding.ActivityProductListBinding

var sum=0.0
class ProductListActivity : AppCompatActivity() {

    lateinit var catList:ArrayList<String>
    lateinit var prodList:ArrayList<Product>
    lateinit var binding:ActivityProductListBinding
    var isLoggedIn=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareData()
        //isLoggedIn=true
        prepComponents()
    }

    override fun onResume() {
        super.onResume()
        updateCartPrice()
    }

    fun prepareData(){
        prodList= injectProds()
        catList= injectCatList()
        isLoggedIn=intent.getBooleanExtra("isLogged",false)
    }

    fun prepComponents(){
        prepareProdRCV()
        prepareCatRCV()
        configureBackButton()
        configureCart()
        updateCartPrice()
    }
    fun prepareProdRCV(){
        binding.productRecV.apply {
            layoutManager=GridLayoutManager(applicationContext,4)
            adapter=CardAdapter(prodList.filter {it.category.equals(catList[0])}as ArrayList<Product>,isLoggedIn,binding.productListCartprice)
        }
    }
    fun prepareCatRCV(){
        binding.categoryRecView.apply {
            layoutManager=LinearLayoutManager(applicationContext).apply {
                orientation=LinearLayoutManager.HORIZONTAL
            }
            adapter=Category_Rec_Adapter(catList,prodList,binding.productRecV,isLoggedIn,binding.productListCartprice)

        }
    }
    fun updateCartPrice(){
        binding.productListCartprice.text="₺${sum.toString()}"
    }

    fun injectCatList():ArrayList<String>{
        return arrayListOf<String>("Su","Gazli Icecek","Maden Suyu","Meyve Suyu","Ayran","Kahve","Yakinda","Favoriler")
    }
    fun configureBackButton(){
        binding.prodListBackButton.setOnClickListener {
            finish()
        }
    }
    fun configureCart(){
        if (!isLoggedIn){
            binding.productListCartprice.visibility=View.GONE
            binding.prodListCartIcon.visibility=View.GONE
        }
    }
    fun injectProds():ArrayList<Product>{
        return arrayListOf<Product>(
            Product("Kuzeyden","0.5 Litre",2.85,"Basketbol Milli Takimlar ve Voleybol Milli Takimlar Resmi Su Sponsoru",R.drawable.su1,"Su"),
            Product("Hayat","0.5 Litre",3.15,"Description",R.drawable.su2,"Su"),
            Product("Erikli","0.5 Litre",3.49,"Description",R.drawable.su3,"Su"),
            Product("Hamidiye","0.5 Litre",2.95,"Description",R.drawable.su4,"Su"),
            Product("Saka","0.5 Litre",2.95,"Description",R.drawable.su4,"Su"),
            Product("Pepsi","Pepsi Max 0.25 Litre",4.95,"Description",R.drawable.pepsi,"Gazli Icecek"),
            Product("Coca-Cola","Coca Cola Klasik 0.25 Litre",7.19,"Description",R.drawable.cocacola,"Gazli Icecek"),
            Product("Kizilay","6 x 0.3 Litre",9.26,"Description",R.drawable.kizilay,"Maden Suyu"),
            Product("Sirma","6 x 0.3 Litre",12.75,"Description",R.drawable.sirma,"Maden Suyu"),
            Product("Juss","Visne 1 Litre",8.95,"Description",R.drawable.juss,"Meyve Suyu"),
            Product("Eker","1 Litre",10.59,"Description",R.drawable.eker,"Ayran"),
            Product("Sutas","250 mL",4.21,"Description",R.drawable.sutas,"Ayran"),
            Product("Nescafe","Black Roast 250 mL",13.15,"Description",R.drawable.nescafe,"Kahve"),
        )

    }

}