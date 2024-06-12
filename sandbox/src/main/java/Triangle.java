import java.util.Objects;

public class Triangle {

    public double side1;
    public double side2;
    public double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        if (side1 < 0 || side2 < 0 || side3 < 0) {
            throw new IllegalArgumentException("Сторона прямоугольника не  может быть отрицательной");
        }
        if ((side1 + side2) < side3 || (side1 + side3) < side2 || (side2 + side3) < side1) {
            throw new IllegalArgumentException("Cумма двух любых сторон должна быть не меньше третьей стороны");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(triangle.side1, this.side1) == 0 && Double.compare(triangle.side2, this.side2) == 0 && Double.compare(triangle.side3, this.side3) == 0)
                || (Double.compare(triangle.side1, this.side1) == 0 && Double.compare(triangle.side2, this.side3) == 0 && Double.compare(triangle.side3, this.side2) == 0)
                || (Double.compare(triangle.side1, this.side2) == 0 && Double.compare(triangle.side2, this.side1) == 0 && Double.compare(triangle.side3, this.side3) == 0)
                || (Double.compare(triangle.side1, this.side2) == 0 && Double.compare(triangle.side2, this.side3) == 0 && Double.compare(triangle.side3, this.side1) == 0)
                || (Double.compare(triangle.side1, this.side3) == 0 && Double.compare(triangle.side2, this.side1) == 0 && Double.compare(triangle.side3, this.side2) == 0)
                || (Double.compare(triangle.side1, this.side3) == 0 && Double.compare(triangle.side2, this.side2) == 0 && Double.compare(triangle.side3, this.side1) == 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(side1, side2, side3);
    }

    public static void main(String[] args) {

    }


    public double calculateSemiPerimeter() {
        return ((this.side1 + this.side2 + this.side3) / 2);
    }


    public double calculateAreaTriangle() {

        return Math.sqrt(this.calculateSemiPerimeter() * (this.calculateSemiPerimeter() - this.side1) * (this.calculateSemiPerimeter() - this.side2) * (this.calculateSemiPerimeter() - this.side3));
    }
}
