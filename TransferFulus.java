class Account {
     // Menyimpan saldo akun, diinisialisasi dengan nilai 150
    int balance = 150;
}   

public class TransferFulus {

    public static void main(String[] args) throws InterruptedException {

        Account acc1 = new Account(); // Objek akun pertama dengan saldo awal 150
        Account acc2 = new Account(); // Objek akun kedua dengan saldo awal 150

        // Thread 1: Transfer saldo dari acc1 ke acc2
        Thread t1 = new Thread(() -> {
            // Langkah 1: Kunci acc1 terlebih dahulu (urutan konsisten)
            synchronized (acc1) {
                System.out.println("T1: Mengunci acc1, akan mengambil saldo dari acc1...");

                // Simulasi jeda proses (misal: verifikasi saldo, logging, dll.)
                // InterruptedException perlu ditangani karena Thread.sleep() bisa
                // diinterupsi oleh thread lain, sehingga program tidak crash
                try { Thread.sleep(100); } catch (Exception e) {}

                // Langkah 2: Setelah acc1 terkunci, kunci acc2 (urutan konsisten)
                synchronized (acc2) {
                    System.out.println("T1: Mengunci acc2, mentransfer saldo dari acc1 ke acc2...");
                    acc2.balance += acc1.balance; // Tambahkan saldo acc1 ke acc2
                }
            }
        });

        // Thread 2: Transfer saldo dari acc2 ke acc1
        Thread t2 = new Thread(() -> {
            // KUNCI: Urutan penguncian sama dengan T1 → acc1 dulu, baru acc2
            // Ini mencegah deadlock karena tidak ada circular waiting
            synchronized (acc1) {
                System.out.println("T2: Mengunci acc1, menunggu giliran mengakses acc2...");

                try { Thread.sleep(100); } catch (Exception e) {}

                // Setelah acc1 terkunci, kunci acc2
                synchronized (acc2) {
                    System.out.println("T2: Mengunci acc2, mentransfer saldo dari acc2 ke acc1...");
                    acc1.balance += acc2.balance; // Tambahkan saldo acc2 ke acc1
                }
            }
        });

        t1.start(); // Jalankan Thread 1
        t2.start(); // Jalankan Thread 2

        t1.join(); // Main thread menunggu t1 selesai sebelum lanjut
        t2.join(); // Main thread menunggu t2 selesai sebelum lanjut

        // Cetak hasil akhir setelah kedua thread selesai
        System.out.println("--- HASIL AKHIR ---");
        System.out.println("Saldo Akhir acc1: " + acc1.balance);
        System.out.println("Saldo Akhir acc2: " + acc2.balance);
    }
}