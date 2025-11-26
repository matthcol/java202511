package base.math.tu;

import base.math.Euclide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class EuclideTest {

    @Test
    void testPgcd_WikipediaCase(){
        int g = Euclide.pgcd(15, 21);
        assertEquals(3, g);
    }

    @ParameterizedTest
    @CsvSource()
    @CsvSource({
            "1, 1, 1",
            "1, 227, 1",
            "227, 1, 1",
            "15, 21, 3",
            "21, 15, 3"
    })
    void testPgcd(int a, int b, int expected_pgcd){
        int actual_pgcd = Euclide.pgcd(a, b);
        assertEquals(expected_pgcd, actual_pgcd);
    }

}