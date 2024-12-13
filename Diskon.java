import java.util.Date;

public class Diskon {
    private String idDiskon;
    private String jenis;
    private int minPembelian;
    private Date tglMulai;
    private Date tglAkhir;

    public Diskon(String idDiskon, String jenis, int minPembelian, Date tglMulai, Date tglAkhir) {
        this.idDiskon = idDiskon;
        this.jenis = jenis;
        this.minPembelian = minPembelian;
        this.tglMulai = tglMulai;
        this.tglAkhir = tglAkhir;
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
}
