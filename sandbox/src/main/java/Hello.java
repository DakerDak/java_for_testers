public class Hello {
    public static void main(String[] args) {

        try {
            var x = 1;
            var y = 0;
            int z = divide(x, y);
            System.out.println(z);
            System.out.println("Hello, world!");
        }
       catch (ArithmeticException exception) {
           System.out.println("Деление на ноль запрещенно");

       }


    }

    private static int divide(int x, int y) {
        var z = x / y;
        return z;
    }
}
