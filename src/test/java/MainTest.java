import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MainTest {

    @Test
    void should_return_exception_if_there_no_map() {
        //Given
        String[] args = new String[0];
        //When
        Exception exception = assertThrows(Exception.class, () ->Main.main(args));
        //Then
        assertEquals("you must pass a file as an args", exception.getMessage());
    }

    @Test
    void should_return_exception_if_file_not_found() {
        //Given
        String[] args = {"test.txt"};
        Main main = new Main();
        //When
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () ->main.createGame(args));
        //Then
        assertEquals("file not found", runtimeException.getMessage());
    }

    @Test
    void should_return_table_5_Lines_6_Collumns_if_C_5_6() {
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
    void adventure_should_do_not_exceed_the_limits_carte_4_4() {
        //Given
        String[] args = {"carteTest4-4.txt"};
        int axeH = 4;
        int axeV = 4;
        Main main = new Main();
        Mountain mountain = new Mountain(0,0);
        Adventurer adventurer = new Adventurer("Bob", 1,3,"S", "AAAAAAA");
        Object[][] actual ={
                {mountain, ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", adventurer, ".", "."}
        };
        // When
        Object[][] expected = main.createGame(args);

        //Then
        assertAll("test all carte",
                () -> assertEquals(actual.length, axeH),
                () -> assertEquals(actual[0].length, axeV),
                () -> assertTrue(actual[1][3].equals(expected[1][3])),
                () -> assertTrue(actual[0][0].equals(expected[0][0])),
                () -> assertTrue(actual[3][3].equals(expected[3][3]))
        );

    }
    @Test
    void adventure_should_not_pass_mountain() {
        // Given
        String[] args = {"carteTest5-5.txt"};
        int axeH = 5;
        int axeV = 5;
        Main main = new Main();
        Mountain mountain = new Mountain(1,4);
        Treasure treasure = new Treasure(1,2,1);
        Adventurer adventurer = new Adventurer("Lea", 1,3,"N", "AAAAAGG");
        adventurer.setNbTreasures(1);
        Object[][] actual ={
                {".", ".", ".", ".", "."},
                {".", ".", ".", ".", "."},
                {".", treasure, ".", ".", "."},
                {".", adventurer, ".", ".", "."},
                {".", mountain, ".", ".", "."}
        };
        // When
        Object[][] expected = main.createGame(args);

        // Then
        Adventurer adventurerExpected = (Adventurer) actual[3][1];
        assertAll("test all carte and adventurer",
                () -> assertEquals(actual.length, axeH),
                () -> assertEquals(actual[0].length, axeV),
                () -> assertEquals(actual[2][1],treasure),
                () -> assertEquals(adventurerExpected.getOrientation(),"N"),
                () -> assertEquals(adventurerExpected.getNbTreasures(),1),
                () -> assertTrue(actual[0][0].equals(expected[0][0])),
                () -> assertTrue(actual[1][2].equals(expected[1][2]))
        );
    }
}
