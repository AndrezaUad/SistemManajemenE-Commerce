public abstract class Laporan {
    protected String idLaporan;
    protected String rentangTanggal;

    public Laporan(String idLaporan, String rentangTanggal) {
        this.idLaporan = idLaporan;
        this.rentangTanggal = rentangTanggal;
    }

    public abstract void buatLaporan();

    public void tampilkanLaporan() {
        System.out.println("Laporan ID: " + idLaporan + " - Rentang Tanggal: " + rentangTanggal);
    }
}
