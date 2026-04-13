class Resto {

    // Stok ayam awal = 100.
    // Hanya bisa diakses oleh 1 thread dalam satu waktu
    // berkat keyword synchronized pada method di bawah.
    private int chickenStock = 100;

    // SOLUSI: Keyword synchronized ditambahkan pada method ini.
    // Artinya hanya 1 kasir (thread) yang boleh menjalankan method ini
    // pada satu waktu. Kasir lain harus antri menunggu di luar
    // hingga kasir yang sedang berjalan selesai.
    public synchronized void serveCustomer(String cashierName) {

        // Pengecekan stok kini aman karena dilakukan di dalam blok synchronized.
        // Tidak ada dua thread yang bisa lolos pengecekan ini secara bersamaan.
        if (chickenStock > 0) {

            // Simulasi waktu proses transaksi seperti mencetak struk atau input data.
            // InterruptedException ditangkap agar program tidak crash
            // jika thread diinterupsi saat sedang sleep.
            try { Thread.sleep(10); } catch (InterruptedException e) {}

            // Pengurangan stok kini aman karena hanya dilakukan oleh 1 thread.
            // Tidak akan ada kondisi di mana dua kasir mengurangi stok bersamaan.
            chickenStock--;

            System.out.println(cashierName + " berhasil menjual 1 ayam. Sisa stok: " + chickenStock);

        } else {
            // Jika stok sudah habis, kasir gagal melayani pelanggan.
            // Dengan synchronized, kondisi ini dijamin akurat —
            // tidak ada kasir yang lolos pengecekan saat stok sudah 0.
            System.out.println(cashierName + " gagal: Stok Habis!");
        }
    }

    // Mengembalikan sisa stok ayam saat ini untuk ditampilkan di akhir program.
    public int getRemainingStock() {
        return chickenStock;
    }
}

public class RestoSimulasi {

    public static void main(String[] args) throws InterruptedException {

        // Satu objek Resto digunakan bersama oleh semua kasir (shared resource).
        // Kini aman karena method serveCustomer() sudah synchronized.
        Resto ayamJuicyLuicyGallagher = new Resto();

        // Setiap kasir mencoba menjual sebanyak 40 kali.
        // Total percobaan = 3 × 40 = 120, namun hanya 100 yang akan berhasil
        // karena stok hanya 100 dan sinkronisasi memastikan tidak ada yang lolos
        // saat stok sudah habis.
        Runnable task = () -> {
            for (int i = 0; i < 40; i++) {
                ayamJuicyLuicyGallagher.serveCustomer(Thread.currentThread().getName());
            }
        };

        // Membuat 3 thread kasir yang menjalankan task yang sama.
        Thread kasir1 = new Thread(task, "Kasir-A");
        Thread kasir2 = new Thread(task, "Kasir-B");
        Thread kasir3 = new Thread(task, "Kasir-C");

        kasir1.start(); // Mulai jalankan Kasir-A
        kasir2.start(); // Mulai jalankan Kasir-B
        kasir3.start(); // Mulai jalankan Kasir-C

        // join() memastikan main thread menunggu semua kasir selesai
        // sebelum mencetak hasil akhir stok.
        kasir1.join();
        kasir2.join();
        kasir3.join();

        // Dengan synchronized, hasil akhir stok dijamin selalu 0 — tidak pernah negatif.
        System.out.println("--- HASIL AKHIR STOK: " + ayamJuicyLuicyGallagher.getRemainingStock() + " ---");
    }
}