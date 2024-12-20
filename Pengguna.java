public abstract class Pengguna {
    protected String idPengguna;
    protected String nama;
    protected String email;
    protected String password;

    public Pengguna(String idPengguna, String nama, String email, String password) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public abstract void tampilkanInfo();

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("Logout berhasil.");
    }

    public String getIdPengguna() {
        return idPengguna;
    }
}
