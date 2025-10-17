
//======================= DATA CLASS AMINO_ACID_SEQUENCE =========================//
data class AminoAcidSequence(
    val name: String,
    val sequence: String
)


//======================= CLASS DNA_ANALYZER ===========================//
class DnaAnalyzer(dna_sequence: String) {

    private val validate_dna : String
    private val rna_sequence : String
    val mino_acid_list = mutableListOf<AminoAcidSequence>()
    var counter_proteins =0

    init{
        validate_dna = dna_sequence.uppercase()
        validationDNA()
        rna_sequence=TranscriptDNAToRNA()
    }


    fun KeepOrfToList(i:Int) :String{
        val protein_builder = StringBuilder()
        for (k in i until rna_sequence.length-3 step 3){
            val codon = rna_sequence.substring(k,k+3)
            if (CodonTable.table[codon]=='B'){
                break
            }
            protein_builder.append(CodonTable.table[codon])
        }
        return protein_builder.toString()
    }


    fun findAllOrfs(): List<AminoAcidSequence> {
        var protein_sequence:String


            for(i in 0 until rna_sequence.length-3 step 3){
                if(rna_sequence.substring(i,i+3)=="AUG"){
                    protein_sequence = KeepOrfToList(i)

                    if (protein_sequence.length >=2)
                    {
                        val protein_name = "protein_$counter_proteins"
                        mino_acid_list.add(AminoAcidSequence(protein_name, protein_sequence))
                        counter_proteins++
                    }
                }
            }

        return (mino_acid_list.sortedByDescending { it.sequence.length })
    }


    private fun validationDNA() {
        var testing_dna = validate_dna
        val stop_codon = listOf("TAA","TAG","TGA")

        if (testing_dna.length%3!=0)
            throw IllegalArgumentException("DNA length must be divisible by three")

        if (testing_dna.contains("ATG").not())
            throw IllegalArgumentException("DNA doesn't contain start-codon")

        if (stop_codon.any{testing_dna.contains(it)}.not())
            throw IllegalArgumentException("DNA doesn't contain stop-codon")

        if (testing_dna.length<12 || testing_dna.isEmpty())
            throw IllegalArgumentException("DNA can't be empty or had length < 6 and must include 2 amino acid")

        if (testing_dna.filter{it !in "AGTC"}.toSet().isNotEmpty())
            throw IllegalArgumentException("DNA consist onle A, C, G, T amino acid")
    }


    private fun TranscriptDNAToRNA():String {
        return(validate_dna.replace('T','U'))
    }


}

//========================= OBJECT CODON_TABLE ========================================//
object CodonTable {
    val table: Map<String, Char> = mapOf(
        "AUG" to 'M',

        "UAA" to 'B', "UAG" to 'B', "UGA" to 'B',

        "ACU" to 'T', "ACC" to 'T', "ACA" to 'T', "ACG" to 'T',
        "AAU" to 'N', "AAC" to 'N', "AAA" to 'K', "AAG" to 'K',
        "AGU" to 'S', "AGC" to 'S', "AGA" to 'R', "AGG" to 'R',
        "GUU" to 'V', "GUC" to 'V', "GUA" to 'V', "GUG" to 'V',
        "GCU" to 'A', "GCC" to 'A', "GCA" to 'A', "GCG" to 'A',
        "GAU" to 'D', "GAC" to 'D', "GAA" to 'E', "GAG" to 'E',
        "GGU" to 'G', "GGC" to 'G', "GGA" to 'G', "GGG" to 'G',
        "UUU" to 'F', "UUC" to 'F', "UUA" to 'L', "UUG" to 'L',
        "UCU" to 'S', "UCC" to 'S', "UCA" to 'S', "UCG" to 'S',
        "UAU" to 'Y', "UAC" to 'Y', "UGU" to 'C', "UGC" to 'C',
        "CUU" to 'L', "CUC" to 'L', "CUA" to 'L', "CUG" to 'L',
        "CCU" to 'P', "CCC" to 'P', "CCA" to 'P', "CCG" to 'P',
        "CAU" to 'H', "CAC" to 'H', "CAA" to 'Q', "CAG" to 'Q',
        "CGU" to 'R', "CGC" to 'R', "CGA" to 'R', "CGG" to 'R',
        "UGG" to 'W'


    )
}


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
