package reader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.Test;

class WordTest {

    @ParameterizedTest
    @ValueSource(strings = { "Computer Science", "    Computer Science     ", "Computer Science     "
    , "     Computer Science"})
    void checkCases(String word){
        Word test = new Word(word);
        test.deleteSpaces();
        assertEquals("Computer Science",test.Word);
    }

    @Test
    public void checkExeption() {
        Word test = new Word();

        assertThrows(StringIndexOutOfBoundsException.class,
                ()->{
            test.deleteSpaces();
                });
    }
}
