public class Produk {
    private String idProduk;
    private String nama;
    private String kategori;
    private int harga;
    private int stok;

    // Constructor
    public Produk(String idProduk, String nama, String kategori, int harga, int stok) {
        this.idProduk = idProduk;
        this.nama = nama;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    // Getter dan Setter
    public String getIdProduk() {
        return idProduk;
    }

    public void setIdProduk(String idProduk) {
        this.idProduk = idProduk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;  // Memperbarui stok produk
    }

    public void tampilkanProduk() {
        System.out.println("ID Produk: " + idProduk + ", Nama: " + nama + ", Kategori: " + kategori + ", Harga: " + harga + ", Stok: " + stok);
    }
}


