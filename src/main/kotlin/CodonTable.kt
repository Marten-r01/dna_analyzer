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

