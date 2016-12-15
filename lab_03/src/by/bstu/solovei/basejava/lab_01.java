package by.bstu.solovei.basejava;

import java.nio.charset.Charset;
import java.util.Random;
import static java.lang.Math.*;

public class lab_01 {

    /*2. Задайте 2 константы и обясните разницу между ними*/

    final int const1 = 1;
    public final int const2 = 2;
    public static final int const3 = 3;

    static int sint;

    /**Метод main
     * @throws ArrayIndexOutOfBoundsException - выход за границы массива (переполнение массива)
     * @param args - массив строк
     * */
    public static void main(String[] args) {

        System.out.println("1. Определить переменные и выполнить над ними операции:");

        /**переменная
         * @value переменная для хранения символа
         * @see variable_char
         * */
        char variable_char = '*';
        int variable_int = 13;
        double variable_double = 13.0;
        short variable_short = 13;
        byte variable_byte = 1;
        long variable_long = 13;
        boolean variable_boolean = true;

        System.out.println("String + int -> BSTU" + (variable_int));
        System.out.println("String + char -> BSTU" + (variable_char));
        System.out.println("String + double -> BSTU" + (variable_short));
        System.out.println("byte = byte + int -> " + (variable_byte + variable_int)); //сужающее преобразование - int, а затем byte
        System.out.println("int = double + long -> " + (variable_double + variable_long)); //сужающее преобразование - double, а затем int
        System.out.println("long = int + 2147483647 -> " + ((long)variable_int + 2147483647)); //при выводе значение должно быть положительным
        System.out.println("static int sint -> " + (sint)); //выведите значение без инициализации

        System.out.print("boolean = boolean && boolean -> ");
        System.out.println(variable_boolean && variable_boolean);
        System.out.print("boolean = boolean ^ boolean -> ");
        System.out.println(variable_boolean ^ variable_boolean);
        // System.out.println(variable_boolean + variable_boolean); //ERROR
        System.out.println("подберите типы для чисел 9223372036854775807 -> long - maxLong");
        System.out.println(Long.MAX_VALUE);
        System.out.println("подберите типы для чисел 0x7fff_ffff_fff -> long - sizeMax");
        System.out.println(Long.SIZE);

        char temp1 = 'a', temp2 = '\u0061', temp3 = 97;
        System.out.println("Проинициализируйте и выведите char - a; \\u0061; 97; после чего сложите все char -> " +
                (temp1 + temp2 + temp3));
        System.out.println("Проверьте результат операции 3.45 % 2.4 -> " + (3.45 % 2.4));
        System.out.println("Проверьте результат операции 1.0 / 0.0 -> " + (1.0 / 0.0));
        System.out.println("Проверьте результат операции 0.0 / 0.0 -> " + (0.0 / 0.0));
        System.out.println("Проверьте результат операции log(-345) -> " + (Math.log(-345)));
        System.out.println("Проверьте результат Float.intBitsToFloat(0x7F800000) -> " +
                (Float.intBitsToFloat(0x7F800000)));
        System.out.println("Проверьте результат Float.intBitsToFloat(0xFF800000) -> " +
                (Float.intBitsToFloat(0xFF800000)));

        System.out.println();
        System.out.println("--------------------");
        System.out.println();

        System.out.println("3. Вывести значения Math.PI, Math.E, округлить их и сгенерировать случайно число из диапазона [0,1)");

        System.out.println(Math.PI);
        System.out.println(Math.E);
        System.out.println(Math.round(Math.PI));
        System.out.println(Math.round(Math.E));
        if (Math.min(Math.PI, Math.E) == Math.PI)
            System.out.println("Math.PI");
        else if (Math.min(Math.PI, Math.E) != Math.PI)
            System.out.println("Math.E");
        else
            System.out.println("Equal");
        System.out.println(Math.random());

        System.out.println();
        System.out.println("--------------------");
        System.out.println();

        System.out.println("4. Создать объекты разных классов оболочек и выполнить над ними арифметические операции:");

        Boolean boolean_ = new Boolean("true");
        Character character_ = new Character('a');
        Integer integer_ = new Integer(32);
        Byte byte_ = new Byte("8");
        Short short_= new Short("1");
        Long long_ = new Long("85");
        Double double_ = new Double("7.1");

        System.out.print("integer_ >>> 2 -> ");
        System.out.println(integer_ >>> 2);
        System.out.print("integer_ >> 2 -> ");
        System.out.println(integer_ >> 2);
        System.out.print("~short_ -> ");
        System.out.println(~short_);
        System.out.print("byte_ * short_ -> ");
        System.out.println(byte_ * short_);
        System.out.print("long_ + double_ -> ");
        System.out.println(long_ + double_);

        System.out.println("Выведите MIN_VALUE и MAX_VALUE для Long и Double");
        System.out.println("Long.MAX_VALUE -> " + Long.MAX_VALUE);
        System.out.println("Long.MIN_VALUE -> " + Long.MIN_VALUE);
        System.out.println("Double.MAX_VALUE -> " + Double.MAX_VALUE);
        System.out.println("Double.MIN_VALUE -> " + Double.MIN_VALUE);

        System.out.println("Выполните упаковку и распаковку для типов Integer и Double");
        int intValueBox = 255;
        Integer integerValueBox = Integer.valueOf(intValueBox);
        System.out.println("Упаковка int в Integer -> " + intValueBox + " -> " + integerValueBox);
        Integer integerValueUnbox = new Integer(-255);
        int intValueUnbox = integerValueUnbox;
        System.out.println("Распаковка Integer в int -> " + integerValueUnbox + " -> " + intValueUnbox);
        byte byteValueBox = 127;
        Byte ByteValueBox = Byte.valueOf(byteValueBox);
        System.out.println("Упаковка byte в Byte -> " + byteValueBox + " -> " + ByteValueBox);
        Byte ByteValueUnbox = new Byte("0");
        byte byteValueUnbox = ByteValueUnbox;
        System.out.println("Распаковка Byte в byte -> " + ByteValueUnbox + " -> " + byteValueUnbox);

        System.out.println("Вызовите методы для Integer: parseInt, toHexString, compare, toString, bitCount, isNaN.");
        String s = "16";
        int i = Integer.parseInt(s);
        System.out.println("parseInt -> " + i);
        System.out.println("toHexString -> " + Integer.toHexString(i));
        System.out.println("compare -> " + Integer.compare(10, i));
        System.out.println("toString -> " + Integer.toString(i));
        System.out.println("bitCount -> " + Integer.bitCount(i));

        System.out.println();
        System.out.println("--------------------");
        System.out.println();

        System.out.println("5. Выполните преобразование числа типа String (String s34 = 2345)");
        String s34 = "2345";
        int i34_1 = Integer.valueOf(s34);
        int i34_2 = Integer.parseInt(s34);
        System.out.println(i34_1);
        System.out.println(i34_2);
        byte[] b34 = s34.getBytes(Charset.forName("UTF-8"));
        System.out.println(b34);
        String s34_new = new String(b34);
        System.out.println(s34_new);

        System.out.println("Преобразуйте строку в логический тип 2-мя способами");
        String s1 = "true";
        String s2 = "yes";
        boolean bool1, bool2, boolean1, boolean2;

        System.out.println("Использование метода parseBoolean");
        bool1 = Boolean.parseBoolean(s1);
        bool2 = Boolean.parseBoolean(s2);
        System.out.println(bool1);
        System.out.println(bool2);

        System.out.println("Использование метода valueOf");
        boolean1 = Boolean.valueOf(s1);
        boolean2 = Boolean.valueOf(s2);
        System.out.println(boolean1);
        System.out.println(boolean2);

        System.out.println("Сравнение строк:");
        String str1 = new String("hello");
        String str2 = new String("hello");
        if (str1 == str2)
            System.out.println("1. str1 == str2");
        else
            System.out.println("1. str1 != str2");
        if (str1.equals(str2))
            System.out.println("2. str1 == str2");
        else
            System.out.println("2. str1 != str2");
        if (str1.compareTo(str2) == 0)
            System.out.println("3. str1 == str2");
        else
            System.out.println("3. str1 != str2");

        System.out.println("Вызовите методы для String: split, contains, hashCode, indexOf, length, replace.");
        String[] newStr = str1.split("l");
        String part1 = newStr[0];
        String part2 = newStr[1];
        String part3 = newStr[2];
        System.out.println("split -> " + part1 + " " + part2 + " " + part3);
        if (str1.contains("a"))
            System.out.println("contains -> true");
        else
            System.out.println("contains -> false");
        System.out.println("hashCode -> " + str1.hashCode());
        System.out.println("indexOf -> " + str1.indexOf("o"));
        System.out.println("length -> " + str1.length());
        System.out.println("replace -> " + str1.replace("l", "L"));

        System.out.println();
        System.out.println("--------------------");
        System.out.println();

        System.out.println("6. Работа с массивами:");
        char[][] c1;
        c1 = new char[3][];
        c1[0] = new char[1];
        c1[1] = new char[2];
        c1[2] = new char[3];
        System.out.println("c1.length " + c1.length);
        System.out.println("c1[0].length " + c1[0].length);
        System.out.println("c1[1].length " + c1[1].length);
        System.out.println("c1[2].length " + c1[2].length);

        char[][] c2 = new char[2][2];
        char[][] c3 = new char[2][2];
        //c2 = c3;
        boolean comRez = c2 == c3;
        System.out.println(comRez);

        int[] array = new int[10];
        Random r = new Random(System.currentTimeMillis());
        for (int j = 0; j < array.length; j++)
        {
            array[j] = r.nextInt(10);
        }
        for (int z: array) {
            System.out.print(z + " ");
        }
        /*array[11] = 111;
        System.out.println(array[11]); //Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException*/

    }
}
