import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.example.DnaAnalyzer
import org.example.AminoAcidSequence
class DnaAnalyzerTest {

    @Test
    @DisplayName("Валидная DNA последовательность ATGGCTAGTTGA")
    fun `valid dna sequence ATGGCTAGTTGA should find ORFs`() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCTAGTTGA")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isNotEmpty(), "Результат не должен быть пустым")
    }

    @Test
    @DisplayName("Валидная DNA последовательность ATGGCTAGTTGAATGGAT")
    fun `valid dna sequence ATGGCTAGTTGAATGGAT should find all ORFs`() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCTAGTTGAATGGAT")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isNotEmpty(), "Должны быть найдены ORF")
    }

   /* @Test
    @DisplayName("Невалидная DNA последовательность ATGXYZ должна выбрасывать исключение")
    fun `invalid dna sequence ATGXYZ should throw exception`() {
        // when & then
        val exception = assertThrows<IllegalArgumentException> {
            DnaAnalyzer("ATGXYZ")
        }
        
        assertTrue(exception.message?.contains("invalid", ignoreCase = true) == true)
    }*/

   /* @Test
    @DisplayName("Короткая DNA последовательность ATGGCT")
    fun `short dna sequence ATGGCT should handle correctly`() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCT")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
    }*/

 /*   @Test
    @DisplayName("DNA последовательность без старт-кодона GCTAGTTGA")
    fun `dna without start codon GCTAGTTGA should return empty result`() {
        // given
        val dnaAnalyzer = DnaAnalyzer("GCTAGTTGA")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isEmpty(), "Для последовательности без старт-кодона должен возвращаться пустой результат")
    }*/

    @Test
    @DisplayName("Пустая DNA последовательность должна выбрасывать исключение")
    fun `empty dna sequence should throw exception`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            DnaAnalyzer("")
        }
    }

    @Test
    @DisplayName("DNA последовательность с недопустимыми символами")
    fun `dna with invalid characters should throw exception`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            DnaAnalyzer("ATG123ATCG")
        }
    }
}
