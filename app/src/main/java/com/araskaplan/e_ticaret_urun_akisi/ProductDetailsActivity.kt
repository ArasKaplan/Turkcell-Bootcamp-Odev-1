package com.araskaplan.e_ticaret_urun_akisi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.araskaplan.e_ticaret_urun_akisi.databinding.ActivityProductDetailsBinding


class ProductDetailsActivity : AppCompatActivity() {
    lateinit var binding:ActivityProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        configureButtons()


    }
    fun getData(){
        binding.prodeuctDetailsActBrand.text=intent.getStringExtra("brand")
        binding.prodeuctDetailsActDetails.text=intent.getStringExtra("details")
        binding.prodeuctDetailsActPrice.text="₺${intent.getDoubleExtra("price",0.1).toString()}"
        binding.prodeuctDetailsActImage.setImageResource(intent.getIntExtra("image",0))
        binding.prodeuctDetailsActName.text=intent.getStringExtra("name")
    }
    fun configureButtons(){
        binding.prodeuctDetailsActBackButton.setOnClickListener {
            finish()
        }
        if (!intent.getBooleanExtra("isLogged",false)){
            binding.prodeuctDetailsActButton.visibility=View.GONE
            binding.productDetailsCartPrice.visibility=View.GONE
            binding.productDetailsCarticon.visibility=View.GONE
        }else{
            prepareCartPrice()
        }
        binding.prodeuctDetailsActButton.setOnClickListener {
            sum+=intent.getDoubleExtra("price",0.1)
            prepareCartPrice()
        }
    }
    fun prepareCartPrice(){
        binding.productDetailsCartPrice.text="₺${sum}"
    }
}