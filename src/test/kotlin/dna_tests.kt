package org.example  // или ваш пакет

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeEach

class DnaAnalyzerTest {

    @Test
    @DisplayName("Тест 1: валидная DNA последовательность ATGGCTAGTTGA")
    fun test1_ValidDnaSequence_ShouldFindOrfs() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCTAGTTGA")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isNotEmpty(), "Результат не должен быть пустым")
    }

    @Test
    @DisplayName("Тест 2: валидная DNA последовательность ATGGCTAGTTGAATGGAT")
    fun test2_ValidDnaSequenceWithMultipleOrfs_ShouldFindAllOrfs() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCTAGTTGAATGGAT")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isNotEmpty(), "Должны быть найдены ORF")
    }

    @Test
    @DisplayName("Тест 3: невалидная DNA последовательность ATGXYZ - должна выбрасывать исключение")
    fun test3_InvalidDnaSequence_ShouldThrowException() {
        // given & when & then
        val exception = assertThrows(IllegalArgumentException::class.java) {
            DnaAnalyzer("ATGXYZ")
        }
        
        assertTrue(exception.message?.contains("invalid", ignoreCase = true) == true)
    }

    @Test
    @DisplayName("Тест 4: короткая DNA последовательность ATGGCT")
    fun test4_ShortDnaSequence_ShouldHandleCorrectly() {
        // given
        val dnaAnalyzer = DnaAnalyzer("ATGGCT")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
    }

    @Test
    @DisplayName("Тест 5: DNA последовательность без старт-кодона GCTAGTTGA")
    fun test5_DnaWithoutStartCodon_ShouldReturnEmptyOrNoOrfs() {
        // given
        val dnaAnalyzer = DnaAnalyzer("GCTAGTTGA")
        
        // when
        val result = dnaAnalyzer.findAllOrfs()
        
        // then
        assertNotNull(result)
        assertTrue(result.isEmpty(), "Для последовательности без старт-кодона должен возвращаться пустой результат")
    }
}
