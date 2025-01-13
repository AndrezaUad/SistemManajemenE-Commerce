import java.util.*;

public class Penyimpananimpl implements Penyimpanan {
    private Map<String, Produk> produkMap = new HashMap<>();
    private List<Pengguna> penggunaList = new ArrayList<>();
    private List<Produk> produkList = new ArrayList<>();

    @Override
    public void tambahProduk(Produk produk) {
        produkMap.put(produk.getIdProduk(), produk);
    }

    @Override
    public boolean cekKetersediaan(String idProduk) {
        Produk produk = produkMap.get(idProduk);
        return produk != null && produk.getStok() > 0;
    }

    @Override
    public void kurangiStok(String idProduk, int jumlah) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(produk.getStok() - jumlah);
        }
    }

    @Override
    public void tambahStok(String idProduk, int jumlah) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(produk.getStok() + jumlah);
        }
    }

    @Override
    public void hapusProduk(String idProduk) {
        produkMap.remove(idProduk);
    }

    @Override
    public void perbaruiProduk(String idProduk, String nama, String kategori, int harga, int stok) {
        Produk produk = produkMap.get(idProduk);
        if (produk != null) {
            produk.setStok(stok);
        }
    }

    public void PenyimpananImpl() {
        // Tambahkan beberapa data pengguna untuk testing
        penggunaList.add(new Admin("1", "Admin1", "admin@example.com", "adminpass", "Manager"));
        penggunaList.add(new Pelanggan("2", "Pelanggan1", "pelanggan@example.com", "pelangganpass", "Jakarta"));
    }

    public List<Pengguna> getAllPengguna() {
        return penggunaList;
    }

    public List<Produk> getAllProduk() {
        return produkList;
    }

}
