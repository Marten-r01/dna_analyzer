

fun main(){
    println("======TESTING=======")
    try {
        val test_1 = DnaAnalyzer("ATGGCTAGTTGA")
        println("test_1 : dna = ATGGCTAGTTGA \n result: ${test_1.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }
    try {
        val test_2 = DnaAnalyzer("ATGGCTAGTTGAATGGAT")
        println("test_2 : dna = ATGGCTAGTTGAATGGAT \n result: ${test_2.findAllOrfs()} ")

    } catch (e: IllegalArgumentException)
    {
        println("output error : ${e.message}")
    }
    try {
        val test_3 = DnaAnalyzer("ATGXYZ")
        println("test_3 : dna = ATGXYZ \nresult: ${test_3.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }
    try {
        val test_4 = DnaAnalyzer("ATGGCT")
        println("test_4 : dna = ATGGCT \nresult: ${test_4.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }
    try{
        val test_5 = DnaAnalyzer("GCTAGTTGA")
        println("test_5 : dna = GCTAGTTGA \nresult: ${test_5.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }

    println("Inpute sequence:_")
    try{
        val user_sequence: String = readLine().toString()
        val user_search = DnaAnalyzer(user_sequence)
        println("result: ${user_search.findAllOrfs()} ")
    }catch (e: IllegalArgumentException){
        println("output error : ${e.message}")
    }

}
