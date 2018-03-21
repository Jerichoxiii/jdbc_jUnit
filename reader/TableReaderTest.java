package reader;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TableReaderTest {

    @Test
    void checkException(){

        assertThrows(FileNotFoundException.class,
                ()->{
                    TableReader test = new TableReader("DoesNotExist.data",'|',4);
                });
    }

    @Test
    void checkReadingLine() throws  IOException {

        TableReader test = new TableReader("Order.data",'|',3);
        test.readNext();
        test.readNext();
        assertEquals(test.line.getLine(),"10|2000|1" );
    }

}
