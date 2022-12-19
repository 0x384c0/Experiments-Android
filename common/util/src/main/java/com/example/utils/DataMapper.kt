package com.example.utils

// to utils module
interface DataMapper<IN, OUT> {
    fun map(input: IN): OUT
}