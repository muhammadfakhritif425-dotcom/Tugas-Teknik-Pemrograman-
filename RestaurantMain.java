import java.util.Scanner;

public class RestaurantMain {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Restaurant menu = new Restaurant();

        menu.tambahMenuMakanan("Pizza", 250000, 20);
        menu.tambahMenuMakanan("Spaghetti", 80000, 20);
        menu.tambahMenuMakanan("Tenderloin Steak", 60000, 30);
        menu.tambahMenuMakanan("Chicken Steak", 45000, 30);

        char lagi;

        do {
            System.out.println("\n=== DAFTAR MENU ===");
            menu.tampilMenuMakanan();

            System.out.print("\nPilih nomor menu: ");
            int pilihan = input.nextInt();

            System.out.print("Masukkan jumlah: ");
            int jumlah = input.nextInt();

            menu.pesanMakanan(pilihan, jumlah);

            System.out.print("\nIngin memesan lagi? (y/n): ");
            lagi = input.next().charAt(0);

        } while (lagi == 'y' || lagi == 'Y');
        menu.tampilMenuMakanan();
        System.out.println("\n=== TERIMA KASIH ===");
        input.close();
    }
}
