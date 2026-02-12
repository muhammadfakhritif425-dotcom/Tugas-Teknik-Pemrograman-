import java.util.Scanner;
public class DataTypes {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Jumlah Test Case : ");
        int T = input.nextInt();


        for (int i = 0; i < T; i++){
        try{
            long angka = input.nextLong();
            System.out.println(angka + " can be fitted in:");

        if (angka >= Byte.MIN_VALUE && angka <= Byte.MAX_VALUE) {
            System.out.println("* Byte");
        }
        if (angka >= Short.MIN_VALUE && angka <= Short.MAX_VALUE) {
            System.out.println("* Short");
        }
        if (angka >= Integer.MIN_VALUE && angka <= Integer.MAX_VALUE) {
            System.out.println("* Int");
        }
        if (angka >= Long.MIN_VALUE && angka <= Long.MAX_VALUE) {
            System.out.println("* Long");
        }
        } catch (Exception e) {
        System.out.println(input.next() + " Can't be fitted anywhere");
        }
    }
}}