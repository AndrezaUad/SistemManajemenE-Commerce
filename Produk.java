public class Produk {
    private String idProduk;
    private String nama;
    private String kategori;
    private int harga;
    private int stok;

    public Produk(String idProduk, String nama, String kategori, int harga, int stok) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void tampilkanProduk() {
        System.out.println("Produk: " + nama + " - Harga: " + harga + " - Stok: " + stok);
    }

}
