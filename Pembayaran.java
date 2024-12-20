public class Pembayaran {
    private String idTransaksi;

    public Pembayaran(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public boolean prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sejumlah " + jumlah + " diproses.");
        return true;
    }

}
