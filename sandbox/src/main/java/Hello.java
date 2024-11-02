import javax.sound.midi.Soundbank;
import java.io.File;

public class Hello {

    public static void main(String[] args) {

        System.out.println("Hello, word!");

        var configFile =new File("sandbox/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());

        System.out.println(new File("").getAbsolutePath());
        System.out.println(new File(""));
    }





//        public static void main(String[] args) {
//        try {
//            var x = 1;
//            var y = 0;
//            int z = divide(x, y);
//            System.out.println(z);
//            System.out.println("Hello, world!");
//        }
//       catch (ArithmeticException exception) {
//           System.out.println("Деление на ноль запрещенно");
//
//       }
//
//    }
//    private static int divide(int x, int y) {
//        var z = x / y;
//        return z;
//    }
}
