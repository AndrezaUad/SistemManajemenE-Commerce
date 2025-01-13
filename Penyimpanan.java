import java.util.*;

public interface Penyimpanan {
    void tambahProduk(Produk produk);
    boolean cekKetersediaan(String idProduk);
    void kurangiStok(String idProduk, int jumlah);
    void tambahStok(String idProduk, int jumlah);
    void hapusProduk(String idProduk);
    void perbaruiProduk(String idProduk, String nama, String kategori, int harga, int stok);


}
