@file:Suppress("ktlint")
// ======================= CLASS DNA_ANALYZER ===========================//
package org.example

class DnaAnalyzer(dna_sequence: String) {
    private val validate_dna: String
    private val rna_sequence: String
    val mino_acid_list = mutableListOf<AminoAcidSequence>()
    var counter_proteins = 0
    val length_triplet = 3

    init {
        validate_dna = dna_sequence.uppercase()
        validationDNA()
        rna_sequence = TranscriptDNAToRNA()
    }

    fun KeepOrfToList(i: Int): String  {
        val protein_builder = StringBuilder()
        for (k in i until rna_sequence.length - length_triplet step length_triplet) {
            val codon = rna_sequence.substring(k, k + length_triplet)
            if (CodonTable.table[codon] == 'B')
                {
                    break
                }
            protein_builder.append(CodonTable.table[codon])
        }
        return protein_builder.toString()
    }

    fun findAllOrfs(): List<AminoAcidSequence> {
        var protein_sequence: String

        for (i in 0 until rna_sequence.length - length_triplet step length_triplet) {
            if (rna_sequence.substring(i, i + length_triplet) == "AUG")
                {
                    protein_sequence = KeepOrfToList(i)

                    if (protein_sequence.length >= 2) {
                        val protein_name = "protein_$counter_proteins"
                        mino_acid_list.add(AminoAcidSequence(protein_name, protein_sequence))
                        counter_proteins++
                    }
                }
        }

        return (mino_acid_list.sortedByDescending { it.sequence.length })
    }

    private fun validationDNA() {
        val length_of_four_triplets = length_triplet * 4
        val multiplicity_length_triplet = length_triplet
        var testing_dna = validate_dna
        val stop_codon = listOf("TAA", "TAG", "TGA")

        if (testing_dna.filter { it !in "AGTC" }.toSet().isNotEmpty()) {
            throw IllegalArgumentException("DNA consist onle A, C, G, T amino acid")
        }

        if (testing_dna.length < length_of_four_triplets || testing_dna.isEmpty()) {
            throw IllegalArgumentException("DNA can't be empty or had length < 6 and must include 2 amino acid")
        }

        if (testing_dna.length % multiplicity_length_triplet != 0) {
            throw IllegalArgumentException("DNA length must be divisible by three")
        }

        if (testing_dna.contains("ATG").not()) {
            throw IllegalArgumentException("DNA doesn't contain start-codon")
        }

        if (stop_codon.any { testing_dna.contains(it) }.not()) {
            throw IllegalArgumentException("DNA doesn't contain stop-codon")
        }
    }

    private fun TranscriptDNAToRNA(): String {
        return(validate_dna.replace('T', 'U'))
    }
}
