import java.util.HashMap;
import java.util.Map;

public class Penyimpanan {
    private Map<String, Integer> stokProduk;

    public Penyimpanan() {
        stokProduk = new HashMap<>();
    }

    public void tambahStok(String idProduk, int jumlah) {
        stokProduk.put(idProduk, stokProduk.getOrDefault(idProduk, 0) + jumlah);
        System.out.println("Stok produk " + idProduk + " ditambah sebanyak " + jumlah + ".");
    }

    public void kurangiStok(String idProduk, int jumlah) {
        if (stokProduk.containsKey(idProduk) && stokProduk.get(idProduk) >= jumlah) {
            stokProduk.put(idProduk, stokProduk.get(idProduk) - jumlah);
            System.out.println("Stok produk " + idProduk + " dikurangi sebanyak " + jumlah + ".");
        } else {
            System.out.println("Stok produk tidak mencukupi.");
        }
    }

    public boolean cekKetersediaan(String idProduk) {
        return stokProduk.getOrDefault(idProduk, 0) > 0;
    }
}
