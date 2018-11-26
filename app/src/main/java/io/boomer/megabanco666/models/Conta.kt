package io.boomer.megabanco666.models

data class Conta(var saldo: Double) {

    fun depositar(valorDeposito: Double) {
        saldo += valorDeposito
    } // meu comentário

    fun tentarSacar(valorSaque: Double): Boolean {
        if (valorSaque > saldo) {
            return false
        } else {
            saldo -= valorSaque
            return true
        }
    }

}
