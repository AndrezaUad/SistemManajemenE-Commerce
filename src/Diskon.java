import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Diskon {
    private String idDiskon;
    private String jenis;
    private int minPembelian;
    private Date tglMulai;
    private Date tglAkhir;
    private List<Pesanan> pesananList;

    public Diskon(String idDiskon, String jenis, int minPembelian, Date tglMulai, Date tglAkhir) {
        this.idDiskon = idDiskon;
        this.jenis = jenis;
        this.minPembelian = minPembelian;
        this.tglMulai = tglMulai;
        this.tglAkhir = tglAkhir;
        this.pesananList = new ArrayList<>();
    }

    public void hitungDiskon(int totalPembelian) {
        if (totalPembelian >= minPembelian) {
            System.out.println("Diskon diterapkan untuk pembelian sebesar " + totalPembelian);
        } else {
            System.out.println("Pembelian tidak memenuhi syarat diskon.");
        }
    }

    public boolean valid(Date tanggal) {
        return tanggal.after(tglMulai) && tanggal.before(tglAkhir);
    }

    public List<Pesanan> getPesananList() {
        return pesananList;
    }

    public void tambahPesanan(Pesanan pesanan) {
        if (!pesananList.contains(pesanan)) {
            pesananList.add(pesanan);
            pesanan.setDiskon(this); // Sinkronisasi ke sisi Pesanan
        }
    }

    public void tambahProduk(Pesanan pesanan) {
    }
}
