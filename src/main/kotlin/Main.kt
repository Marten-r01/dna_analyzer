package org.example

fun main(){

    println("Inpute sequence:_")
    try{
        val user_sequence: String = readLine().toString()
        val user_search = DnaAnalyzer(user_sequence)
        println("result: ${user_search.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }

}
