import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculateHalfPerimeter() {
        var p = new Triangle(3, 5, 7);
        double result = p.calculateSemiPerimeter();
        Assertions.assertEquals(7.5, result);
    }

    @Test
    void canCalculateArea() {
        var s = new Triangle(3, 5, 7);
        double result = s.calculateAreaTriangle();
        Assertions.assertEquals(6.49519052838329, result);
    }

    @Test
    void cannotCreateWithNegativeSide1() {
        try {
            new Triangle(-5, 9, 8);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void cannotCreateWithNegativeSide2() {
        try {
            new Triangle(5, -9, 8);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void checkingСorrectnessSidesTriangle() {
        try {
            new Triangle(6, 5, 66);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void equalityTriangles() {
        var t1 = new Triangle(3.0, 5.0, 6.0);
        var t2 = new Triangle(3.0, 5.0, 6.0);
        Assertions.assertEquals(t1, t2);
    }

    @Test
    void equalityTriangles2() {
        var t1 = new Triangle(3.0, 5.0, 6.0);
        var t2 = new Triangle(5.0, 3.0, 6.0);
        Assertions.assertTrue(t1.equals(t2));
    }

    @Test
    void equalityTriangles3() {
        var t1 = new Triangle(3.0, 5.0, 6.0);
        var t2 = new Triangle(6.0, 3.0, 5.0);
        Assertions.assertTrue(t1.equals(t2));
    }
}
