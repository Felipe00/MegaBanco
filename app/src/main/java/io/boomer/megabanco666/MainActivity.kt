package io.boomer.megabanco666

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import io.boomer.megabanco666.models.Conta
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_deposito.*
import kotlinx.android.synthetic.main.include_saldo.*
import kotlinx.android.synthetic.main.include_saque.*

class MainActivity : AppCompatActivity() {

    private lateinit var conta: Conta

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_saldo -> {
                view_flipper.displayedChild = 0
                // atualizar o saldo
                valor_saldo.text = "R$ ${conta.saldo}"
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_deposito -> {
                view_flipper.displayedChild = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_saque -> {
                view_flipper.displayedChild = 2
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        conta = Conta(600.00)
        valor_saldo.text = "R$ ${conta.saldo}"

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        btn_confirmar_deposito.setOnClickListener {
            conta.depositar(valor_deposito.text.toString().toDouble())
            Toast.makeText(this@MainActivity, "Depósito realizado com sucesso", Toast.LENGTH_LONG).show()
            navigation.selectedItemId = R.id.nav_saldo
        }

        btn_confirmar_saque.setOnClickListener {
            if (conta.tentarSacar(valor_saque.text.toString().toDouble())) {
                Toast.makeText(this@MainActivity, "Saque realizado com sucesso", Toast.LENGTH_LONG).show()
                navigation.selectedItemId = R.id.nav_saldo
            } else {
                Toast.makeText(this@MainActivity, "Valor indisponível pra saque", Toast.LENGTH_LONG).show()
            }
        }
    }
}
