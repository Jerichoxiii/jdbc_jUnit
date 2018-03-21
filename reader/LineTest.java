package reader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import org.junit.jupiter.api.Test;
public class LineTest {

    @ParameterizedTest
    @ValueSource(strings = { "one|two|tree|four", "one  |two |tree    |four   ", " one   |  two  |  tree|   four  "
            , "one   |two  |  tree|  four" , "  one|  two|tree  |four  " ,"one |two  |tree  |four "})
    void checkCases(String line){
        Line test = new Line('|',4);
        test.setLine(line);
        test.extractWords();
        assertEquals("one",test.getWord(0));
        assertEquals("two",test.getWord(1));
        assertEquals("tree",test.getWord(2));
        assertEquals("four",test.getWord(3));
    }

    @Test
    public void checkExeption() {
        Line test = new Line('|',4);
        test.setLine("");
        assertThrows(StringIndexOutOfBoundsException.class,
                ()->{
                    test.extractWords();
                });
    }

    @Test
    public void checkExeption2() {
        Line test = new Line('|',4);
        assertThrows( NullPointerException.class,
                ()->{
                    test.extractWords();
                });
    }
}
