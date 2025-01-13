import java.util.Date;

public class LaporanPenjualan extends Laporan {
    private int pendapatanBulanan;
    private int pendapatanTahunan;

    public LaporanPenjualan(String idLaporan, String rentangTanggal, int pendapatanBulanan, int pendapatanTahunan) {
        super(idLaporan, rentangTanggal);
        this.pendapatanBulanan = pendapatanBulanan;
        this.pendapatanTahunan = pendapatanTahunan;
    }

    public LaporanPenjualan(String laporanPenjualan, Date date, int pendapatanBulanan) {
        super(laporanPenjualan, date, pendapatanBulanan);
    }

    @Override
    public void buatLaporan() {
        System.out.println("Laporan Penjualan:");
        System.out.println("Pendapatan Bulanan: " + pendapatanBulanan);
        System.out.println("Pendapatan Tahunan: " + pendapatanTahunan);
    }
}

