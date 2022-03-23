package com.araskaplan.e_ticaret_urun_akisi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.araskaplan.e_ticaret_urun_akisi.databinding.LoginActBinding

class LogInActivity : AppCompatActivity() {
    lateinit var binding:LoginActBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= LoginActBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepComponents()
    }

    fun prepComponents(){
        prepPassword()
        prepNoSignIn()
        prepSignIn()
    }
    fun prepPassword(){
        binding.loginPassword.setOnClickListener{
            binding.loginPassword.text?.clear()
        }
    }
    fun prepSignIn(){
        binding.loginSigninButton.setOnClickListener {
            if (binding.loginPhonenum.text.toString().equals("01111111111") && binding.loginPassword.text.toString().equals("#e&it1m")){
                val intent=Intent(this,ProductListActivity::class.java)
                intent.putExtra("isLogged",true)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"Kullanıcı Bulunamadı",Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun prepNoSignIn(){
        binding.loginNosigninButton.setOnClickListener {
            val intent=Intent(this,ProductListActivity::class.java)
            intent.putExtra("isLogged",false)
            startActivity(intent) //Aktiviteler icinde default value false oldugu icin extra koymaya gerek yok
        }
    }
}