import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
        Main main = new Main();
        //When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () ->main.createGame(args));
        //Then
        assertEquals("file not found", runtimeException.getMessage());
    }

    @Test
    public void should_return_table_5_Lines_6_Collumns_if_C_5_6() {
        //Given
        String[] args = {"carteTest5-6.txt"};
        Main main = new Main();
        Object[][] actual ={
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."}
        };
        // When
        Object[][] expected = main.createGame(args);

        //Then
        assertArrayEquals(actual,expected);
    }

    @Test
    public void adventure_should_do_not_exceed_the_limits_carte_4_4() {
        //Given
        String[] args = {"carteTest4-4.txt"};
        Main main = new Main();
        Mountain mountain = new Mountain(0,0);
        Adventurer adventurer = new Adventurer("Bob", 1,3,"S", "AAAAAAA");
        Object[][] actual ={
                {mountain, ".", ".", "."},
                {".", ".", ".", "."},
                {".", ".", ".", "."},
                {".", adventurer, ".", "."}
        };
        // When
        Object[][] expected = main.createGame(args);

        //Then
        assertTrue(actual[1][3].equals(expected[1][3]));
    }
}
