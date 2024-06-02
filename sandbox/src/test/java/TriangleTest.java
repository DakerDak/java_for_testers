import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculatePerimeter () {
        var p = new Triangle(3,5,7);
        double result = p.calculateSemiPerimeter();
        Assertions.assertEquals (7.5, result);
    }

    @Test
    void canCalculateArea () {
        var s = new Triangle(3,5,7);
        double result = s.calculateAreaTriangle ();
        Assertions.assertEquals(6.49519052838329, result);
    }
}
