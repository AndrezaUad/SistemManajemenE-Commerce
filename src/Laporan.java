import java.util.Date;

public abstract class Laporan {
    protected String idLaporan;
    protected String rentangTanggal;

    public Laporan(String idLaporan, String rentangTanggal) {
        this.idLaporan = idLaporan;
        this.rentangTanggal = rentangTanggal;
    }

    public Laporan(String laporanPenjualan, Date date, int pendapatanBulanan) {
    }

    public abstract void buatLaporan();

    public void tampilkanLaporan() {
        System.out.println("Laporan ID: " + idLaporan + " - Rentang Tanggal: " + rentangTanggal);
    }
}
