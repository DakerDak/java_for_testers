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

        return Math.sqrt(this.calculateSemiPerimeter() * (this.calculateSemiPerimeter() - this.side1) * (this.calculateSemiPerimeter() - this.side2) * (this.calculateSemiPerimeter() - this.side3));
    }
}
