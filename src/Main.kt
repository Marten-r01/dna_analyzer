/*# Задание: Биоинформатика — Анализатор геномных последовательностей

## Цель
Разработать утилиту для анализа последовательностей ДНК с поддержкой транскрипции в РНК и трансляции в белковые последовательности.

## Контекст
В биоинформатике одна из базовых задач — поиск открытых рамок считывания (Open Reading Frames, ORF). ORF — это участок генома между старт-кодоном (AUG в мРНК) и стоп-кодоном (UAA, UAG, UGA).

**Биологический процесс:**
1. ДНК (A, T, G, C) → транскрипция → мРНК (A, U, G, C)
2. мРНК читается триплетами (кодонами по 3 нуклеотида)
3. Каждый кодон кодирует одну аминокислоту
4. Трансляция начинается со старт-кодона AUG и заканчивается на стоп-кодоне

## Ресурсы для изучения
- [Генетический код (таблица кодонов)](https://en.wikipedia.org/wiki/Genetic_code)
- [Open Reading Frame](https://en.wikipedia.org/wiki/Open_reading_frame)
- Используйте стандартную генетическую таблицу кодонов

## Требуемый интерфейс

### Data класс
```kotlin*/
data class AminoAcidSequence(
    val name: String,
    val sequence: String
){

}
/*

### Основной класс
```kotlin*/
class DnaAnalyzer(dna_sequence: String) {
    init{
        validationDNA()
    }
    private var validate_dna = dna_sequence.uppercase()
    var mino_acid_list =listOf<String>()

    fun KeepOrfToList(i:Int){
        for (k in i until validate_dna.length-3 step 3){
            mino_acid_list.
        }
    }

    fun findAllOrfs(): List<String> {

        for(i in 0 until validate_dna.length-2){
            if(validate_dna[i]=='A' && validate_dna[i+1]=='U'&& validate_dna[i+2]=='G'){
                KeepOrfToList(i)
            }
        }
        return (mino_acid_list)
            }
    fun validationDNA()
    {
        var testing_dna = validate_dna
        val stop_codon = listOf("TAA","TAG","TGA")

        if (testing_dna.length<12 || testing_dna.isEmpty())
            throw IllegalArgumentException("DNA can't be empty or had length < 6 and must include 2 amino acid")

        if (testing_dna.contains("ATG").not())
            throw IllegalArgumentException("DNA doesn't contain start-codon")

        if (stop_codon.any{testing_dna.contains(it)}.not())
            throw IllegalArgumentException("DNA doesn't contain stop-codon")


        if (testing_dna.filter{it !in "AGTC"}.toSet().isNotEmpty())
            throw IllegalArgumentException("DNA consist onle A, C, G, T amino acid")

    }

    private fun TranscriptDNAToRNA():String {
        return(validate_dna.replace('T','U'))
    }


}


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
/*

## Требования и ограничения

**Валидация:**
- ДНК последовательность может содержать только A, T, G, C
- Невалидная последовательность → `IllegalArgumentException`

**Функциональность:**
- Транскрипция: T → U (остальное без изменений)
- Поиск всех возможных ORF в последовательности (может быть несколько)
- Старт-кодон: AUG
- Стоп-кодоны: UAA, UAG, UGA (не включаются в результат)
- Минимальная длина ORF: 2 аминокислоты

**Результат:**
- Список `AminoAcidSequence`, отсортированный по убыванию длины
- Имена белков генерируются автоматически (например, `"Protein_1"`, `"ORF_<позиция>"`)
- Пустой список, если ORF не найдены

## Пример

**Вход:**
```kotlin*/
val analyzer = DnaAnalyzer("ATGGCTAGTTGA")
//val orfs = analyzer.findAllOrfs()
/*```

**Процесс:**
- ДНК: `ATGGCTAGTTGA`
- мРНК: `AUGGCUAGUUGA`
- AUG (M) → GCU (A) → AGU (S) → UGA (stop)

**Выход:**
```kotlin
[AminoAcidSequence(name="Protein_1", sequence="MAS")]
```

## Тестовые случаи для проверки

```kotlin
// 1. Простая последовательность с одним ORF
"ATGGCTAGTTGA" → "MAS"

// 2. Последовательность с несколькими ORF
"ATGGCTAGTTGAATGGAT" → ["MAS", "MD"]

// 3. Невалидная последовательность
"ATGXYZ" → IllegalArgumentException

// 4. Нет стоп-кодона (незавершенный ORF - игнорируется)
"ATGGCT" → []

// 5. Последовательность без старт-кодона
"GCTAGTTGA" → []
```

## Что проверяется
- Работа с классами (class, data class, object)
- Обработка строк и эффективность (StringBuilder)
- Работа с коллекциями (Map, List, сортировка)
- Обработка исключений
- Применение алгоритмической логики в незнакомой предметной области
*/