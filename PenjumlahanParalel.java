import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

public class PenjumlahanParalel {

    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);
        String ulang;

        // --- LOOPING UTAMA ---
        // Program akan terus berjalan selama user menjawab 'y' atau 'Y'
        do {

            // Reset totalAkhir setiap kali program diulang
            // agar hasil perhitungan sebelumnya tidak ikut terjumlahkan
            AtomicLong totalAkhir = new AtomicLong(0);

            // --- INPUT ---
            System.out.print("Masukkan Jumlah Thread : ");
            int jumlahThread = scanner.nextInt();

            System.out.print("Masukkan Angka Akhir   : ");
            long angkaAkhir = scanner.nextLong();

            System.out.println("\n--- PEMBAGIAN TUGAS ---");

            // --- PEMBAGIAN TUGAS (DIVIDE AND CONQUER) ---
            // Rentang angka dibagi merata ke setiap thread
            long porsiPerThread = angkaAkhir / jumlahThread;

            Thread[] threads = new Thread[jumlahThread];

            for (int i = 0; i < jumlahThread; i++) {

                // Hitung batas bawah dan batas atas untuk setiap thread
                long batasBawah = (i * porsiPerThread) + 1;

                // Thread terakhir mengambil sisa angka jika tidak habis dibagi
                long batasAtas = (i == jumlahThread - 1) ? angkaAkhir : (i + 1) * porsiPerThread;

                // Variabel final agar bisa digunakan di dalam lambda
                final long start = batasBawah;
                final long end = batasAtas;
                final int threadIndex = i + 1;
                final AtomicLong total = totalAkhir;

                // Buat thread untuk menghitung penjumlahan di rentang yang ditentukan
                threads[i] = new Thread(() -> {

                    System.out.println("Thread-" + threadIndex +
                        " bertugas menjumlahkan " + start + " sampai " + end);

                    // Hasil parsial dihitung di variabel lokal agar thread-safe
                    long hasilParsial = 0;
                    for (long j = start; j <= end; j++) {
                        hasilParsial += j;
                    }

                    System.out.println("Thread-" + threadIndex +
                        " selesai → Hasil parsial: " + hasilParsial);

                    // Tambahkan hasil parsial ke total secara atomik
                    total.addAndGet(hasilParsial);
                });
            }

            // --- JALANKAN SEMUA THREAD SECARA PARALEL ---
            for (Thread t : threads) {
                t.start();
            }

            // --- SINKRONISASI: Tunggu semua thread selesai ---
            for (Thread t : threads) {
                t.join();
            }

            // --- OUTPUT HASIL AKHIR ---
            System.out.println("\n--- HASIL AKHIR ---");
            System.out.println("Total penjumlahan 1 sampai " + angkaAkhir + " = " + totalAkhir.get());

            // Verifikasi menggunakan rumus Gauss: n*(n+1)/2
            long verifikasi = angkaAkhir * (angkaAkhir + 1) / 2;
            System.out.println("Verifikasi (rumus n*(n+1)/2)          = " + verifikasi);
            System.out.println("Hasil : " + (totalAkhir.get() == verifikasi ? "BENAR ✓" : "SALAH ✗"));

            // --- TANYA ULANG ---
            System.out.print("\nApakah ingin mengulangi program? (y/n) : ");
            ulang = scanner.next();
            System.out.println();

        } while (ulang.equalsIgnoreCase("y"));
        // equalsIgnoreCase() agar user bisa input 'y' atau 'Y'

        // Pesan penutup saat user memilih tidak mengulang
        System.out.println("Terima kasih telah menggunakan program ini!");
        scanner.close();
    }
}