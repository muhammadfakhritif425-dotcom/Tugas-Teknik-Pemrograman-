public class Restaurant {

    private String[] namaMakanan;
    private double[] hargaMakanan;
    private int[] stok;
    private int id;

    public Restaurant() {
        namaMakanan = new String[10];
        hargaMakanan = new double[10];
        stok = new int[10];
        id = 0;
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {

        if (stok < 0) {
            System.out.println("Stok tidak boleh negatif!");
            return;
        }

        namaMakanan[id] = nama;
        hargaMakanan[id] = harga;
        this.stok[id] = stok;
        id++;
    }

    public void tampilMenuMakanan() {
       for (int i = 0; i < id; i++) {
    if (stok[i] > 0) {
        System.out.println(i + ". " + namaMakanan[i] 
            + " | Harga: " + hargaMakanan[i] 
            + " | Stok: " + stok[i]);
                }
             }
        }


    public void pesanMakanan(int index, int jumlah) {

        if (index < 0 || index >= id) {
            System.out.println("Menu tidak ditemukan!");
            return;
        }

        if (jumlah <= 0) {
            System.out.println("Jumlah tidak valid!");
            return;
        }

        if (stok[index] >= jumlah) {
            stok[index] -= jumlah;
            System.out.println("Pemesanan berhasil!");
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
}