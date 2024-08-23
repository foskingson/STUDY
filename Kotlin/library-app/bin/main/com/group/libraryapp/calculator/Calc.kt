package com.group.libraryapp.calculator

class Calc(private var num: Int) {

    fun getNum(): Int {
        return num
    }

    fun add(num2: Int){
        this.num += num2
    }

    fun minus(num2: Int){
        this.num-=num2
    }

    fun multiply(num2: Int){
        this.num*=num2
    }

    fun divide(num2: Int){
        if (num2==0){
            throw IllegalArgumentException("0으로 못 나눔")
        }
        this.num /= num2
    }

}