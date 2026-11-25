package edu.byu.cs.sonar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

class CustomFileReaderTest {

    private CustomFileReader sut;

    @BeforeEach
    void setUp() {
        sut = new CustomFileReader("readMe1.txt");
    }

    @Test
    void testHowManyWordsInFile() throws FileNotFoundException {
        assertEquals(4, sut.howManyWordsInFile(), "There should be 4 words in readMe1.txt");
    }

    @Test
    void testReturnThatWord() throws FileNotFoundException {
        assertEquals("I", sut.returnThatWord(1), "The first word should be I in readMe1.txt");
    }

    @Test
    void testFindNewWord() throws FileNotFoundException {
        sut.findNewWord("C");
        assertEquals("Computer ", sut.getNewSentence(), "Computer should be found when C queried");
    }

    @Test
    void testGetNewSentence() {
        assertEquals("", sut.getNewSentence(), "New sentence should be empty initially");
    }

    @Test
    void setNewSentence() {
        String betterSentence = "New Sentence.";
        sut.setNewSentence(betterSentence);
        assertEquals(betterSentence, sut.getNewSentence());
    }

    @Test
    void testToString() throws FileNotFoundException {
        sut.setNewSentence("Hello");
        sut.howManyWordsInFile();
        assertEquals("Hello 4", sut.toString(), "toString should show the sentence and word count");
    }

    @Test
    void testEqualsSameValues() {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        assertEquals(sut, other, "Readers with the same file, sentence, and count should be equal");
    }

    @Test
    void testNotEqualsDifferentSentence() {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.setNewSentence("Different");
        assertNotEquals(sut, other, "Readers with different sentences should not be equal");
    }

    @Test
    void testNotEqualsDifferentCount() throws FileNotFoundException {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        other.howManyWordsInFile();
        assertNotEquals(sut, other, "Readers with different counts should not be equal");
    }

    @Test
    void testNotEqualsDifferentPath() {
        CustomFileReader other = new CustomFileReader("readMe2.txt");
        assertNotEquals(sut, other, "Readers with different files should not be equal");
    }

    @Test
    void testHashCodeEqualForEqualReaders() {
        CustomFileReader other = new CustomFileReader("readMe1.txt");
        sut.setNewSentence("Hello");
        other.setNewSentence("Hello");
        assertEquals(sut.hashCode(), other.hashCode(), "Equal readers should have equal hash codes");
    }

    @Test
    void testHashCodeDiffersForDifferentReaders() {
        CustomFileReader other = new CustomFileReader("readMe2.txt");
        assertNotEquals(sut.hashCode(), other.hashCode(), "Different readers should usually have different hash codes");
    }
}