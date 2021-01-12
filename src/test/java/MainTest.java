import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MainTest {

    @Test
    public void should_return_exception_if_there_no_map() {
        //Given
        String[] args = new String[0];
        //When
        Exception exception = assertThrows(Exception.class, () ->Main.main(args));
        //Then
        assertEquals("you must pass a file as an args", exception.getMessage());
    }

    @Test
    public void should_return_exception_if_file_not_found() {
        //Given
        String[] args = {"test.txt"};
        //When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () ->Main.main(args));
        //Then
        assertEquals("file not found", runtimeException.getMessage());
    }


    // Verifier que dans le fichier la ligne du début est bien C - 5 - 6
    /*public void should_return_table_5_C_6_L() {
        Main.main(["carteTest5-6.txt",]);
    }*/


}
