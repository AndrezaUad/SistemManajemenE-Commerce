public class LaporanPenjualan extends Laporan {
    private int pendapatanBulanan;
    private int pendapatanTahunan;

    public LaporanPenjualan(String idLaporan, String rentangTanggal, int pendapatanBulanan, int pendapatanTahunan) {
        super(idLaporan, rentangTanggal);
        this.pendapatanBulanan = pendapatanBulanan;
        this.pendapatanTahunan = pendapatanTahunan;
    }

    @Override
    public void buatLaporan() {
        System.out.println("Laporan Penjualan:");
        System.out.println("Pendapatan Bulanan: " + pendapatanBulanan);
        System.out.println("Pendapatan Tahunan: " + pendapatanTahunan);
    }
}

