package com.group.libraryapp.calculator

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CalcTest{

    @Test
    fun addTest(){
        // given
        val calc = Calc(5)

        // when
        calc.add(3)

        // then
        assertThat(calc.getNum()).isEqualTo(8)
    }

}