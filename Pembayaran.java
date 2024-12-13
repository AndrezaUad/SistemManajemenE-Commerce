public class Pembayaran {
    private String idTransaksi;

    public Pembayaran(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public boolean prosesPembayaran(double jumlah) {
        System.out.println("Pembayaran sejumlah " + jumlah + " diproses.");
        return true;
    }

    public boolean pengembalianDana(String idTransaksi) {
        System.out.println("Dana dengan ID Transaksi " + idTransaksi + " dikembalikan.");
        return true;
    }
}
