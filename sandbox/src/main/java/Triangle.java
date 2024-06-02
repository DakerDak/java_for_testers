public class Triangle {

    public double side1;
    public double side2;
    public double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public static void main(String[] args) {

    }


    public double calculateSemiPerimeter() {
        return ((this.side1 + this.side2 + this.side3) / 2);
    }


    public double calculateAreaTriangle() {
        var s = new Triangle(side1, side2, side3);
        return Math.sqrt(s.calculateSemiPerimeter() * (s.calculateSemiPerimeter() - this.side1) * (s.calculateSemiPerimeter() - this.side2) * (s.calculateSemiPerimeter() - this.side3));
    }
}
