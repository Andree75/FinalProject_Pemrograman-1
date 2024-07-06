import java.util.Scanner;

class Reservasi {
    String passengerName;
    String rutePenerbangan;
    String setKelas;
    double harga;
    String metodePembayaran;
    String tanggalKeberangkatan;

    /**
     * @passenggerName nama penumpang
     * @rutePenerbangan rute penerbangan yang disediakan
     * @setKelas set tipe Kelas yang ada
     * @harga harga penerbangan
     * @metodePembayaran untuk metode pembayaran
     */

    public Reservasi(String passengerName, String rutePenerbangan, String setKelas, double harga,
            String metodePembayaran, String tanggalKeberangkatan) {

        this.passengerName = passengerName;
        this.rutePenerbangan = rutePenerbangan;
        this.setKelas = setKelas;
        this.harga = harga;
        this.metodePembayaran = metodePembayaran;
        this.tanggalKeberangkatan = tanggalKeberangkatan;

    }

    /**
     * @everride untuk mengambil variabel yang di deklarasikan dari class utama
     * 
     * @String.format untuk mengembalikan/menampilkan nilai variabel yang telah di
     *                deklarasikan
     */
    @Override
    public String toString() {
        return String.format(
                "Nama: %s | Penerbangan: %s | tanggal Keberangkatan: %s| Kelas: %s | Harga: Rp%,.2f \n\t | Metode Pembayaran dengan %s",
                passengerName, rutePenerbangan, tanggalKeberangkatan,
                setKelas,
                harga, metodePembayaran);
    }
}

public class AirlineReservationSystem {

    static Scanner input = new Scanner(System.in);

    /**
     * @rutePenerbangan untuk menyimpan rute penerbangan yang tersedia
     */
    // Array untuk menyimpan penerbangan yang tersedia
    static String[] rutePenerbangan = { "Jakarta - Denpasar", "Jakarta - Surabaya", "Jakarta - Medan",
            "Surabaya - Makassar",
            "Medan - Denpasar", "Banda Aceh - Jakarta", "Surabaya - Lombok", "Balikpapan - Surabaya",
            "Surabaya - Manado",
            "Surabaya - Kupang", "Papua - Banda Aceh", "Jakarta - Lombok", "Batam - Padang", "Denpasar - Yogyakarta",
            "Jakarta - Banda Aceh" };

    /**
     * @kelasTersedia untuk menyimpan data kursi perkelas yang tersedia di masing2
     *                rute penerbangan
     * 
     */
    static int[][] kelasTersedia = { { 2, 4, 5 }, { 3, 6, 6 }, { 3, 2, 6 }, { 2, 5, 5 }, { 3, 5, 6 },
            { 1, 3, 6 }, { 2, 6, 5 }, { 2, 1, 6 }, { 3, 4, 5 }, { 0, 1, 6 }, { 0, 2, 5 }, { 0, 3, 0 },
            { 0, 2, 6 }, { 4, 6, 6 }, { 0, 3, 6 } }; // Kursi tersedia untuk masing-masing kelas (First Class, Bisnis,
                                                     // Ekonomi)

    /**
     * @reservasiUser untuk menyimpan rute penerbangan, kelas penerbangan, maksimal
     *                per
     *                kelas menampung 6 kuota
     */
    static Reservasi[][][] reservasiUser = new Reservasi[15][3][6]; // Menyimpan data pemesan berdasarkan penerbangan
                                                                    // dan kelas

    /**
     * @tanggalKeberangkatan Tanggal Keberangkatan Pemesan di Rute Penerbangan
     */
    static String tanggalKeberangkatan;

    /**
     * @validUsername untuk menyimpan data username login
     * 
     */
    private static String[] validUsernames = { "uisi2023", "uisi2023", "uisi2023", "uisi2023" };

    /**
     * @validPassword untuk menyimpan data password login
     */
    private static String[] validPasswords = { "if9", "andre", "nadhif", "arif" };

    /**
     * @close sebagai kondisi penentu untuk mengulang pilihan menu
     * @choice untuk pilihan yang dapat dinputkan user
     */
    public static void main(String[] args) throws InterruptedException {
        boolean close = false;

        Login();

        while (!close) {
            showMenu();
            int choice = input.nextInt();
            input.nextLine(); // Clear the newline

            switch (choice) {
                case 1:
                    showFlights();
                    System.out.println();
                    break;
                case 2:
                    searchFlights();
                    System.out.println();
                    break;
                case 3:
                    bookTicket();
                    break;
                case 4:
                    cancelTicket();
                    System.out.println();
                    break;
                case 5:
                    showBookings();
                    break;
                case 6:
                    close = true;
                    System.out.println("Terima kasih telah menggunakan sistem Pemesanan di Maskapai Kami.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Method untuk menampilkan menu utama
    public static void showMenu() throws InterruptedException {

        System.out.println("+==================================================================+");
        System.out.println("||<<<= Selamat Datang di Sistem Reservasi Pesawat IDNFLYSTARR =>>>||");
        System.out.println("+==================================================================+");
        System.out.println("|| 1 || Tampilkan Daftar Penerbangan                              ||");
        System.out.println("|| 2 || Cari Penerbangan                                          ||");
        System.out.println("|| 3 || Pesan Tiket                                               ||");
        System.out.println("|| 4 || Batalkan Tiket                                            ||");
        System.out.println("|| 5 || Tampilkan Pemesanan                                       ||");
        System.out.println("|| 6 || Keluar                                                    ||");
        System.out.println("+==================================================================+");

        System.out.println("+=====================+");
        System.out.print(" Silahkan Pilih opsi: ");
    }

    // Method untuk menampilkan daftar penerbangan
    public static void showFlights() {
        System.out.println();
        System.out.println(
                "+====================================================================================================================+");
        System.out.println(
                "||                                           <<<= Daftar Penerbangan =>>>                                           ||");
        System.out.println(
                "+====================================================================================================================+");
        System.out.println();
        for (int i = 0; i < rutePenerbangan.length; i++) {
            System.out.println(
                    "+====+===============================================================================================================");
            System.out.println("|| " + (i + 1) + " || " + rutePenerbangan[i] +
                    " (Kuota First Class Tersedia: " + kelasTersedia[i][0] +
                    ", Kuota Bisnis Tersedia: " + kelasTersedia[i][1] +
                    ", Kuota Ekonomi Tersedia: " + kelasTersedia[i][2] + ")");

        }
    }

    // Method untuk mencari penerbangan berdasarkan tujuan
    /**
     * @found untuk menentukan dan menemukan Tipe kelas
     */
    public static void searchFlights() {
        System.out.println();
        System.out.println("+==========================+");
        System.out.println("||<<= Cari Penerbangan =>>||");
        System.out.println("+==========================+");
        System.out.println("+============================================================+");
        System.out.print("  Masukkan nama Daerah yang akan anda tuju (ex: Surabaya): ");
        String destination = input.nextLine().toLowerCase();
        System.out.println();

        boolean found = false;
        for (int i = 0; i < rutePenerbangan.length; i++) {
            if (rutePenerbangan[i].toLowerCase().contains(destination)) {
                System.out.println(
                        "+====+===============================================================================================================");
                System.out.println("||" + (i + 1) + "|| " + rutePenerbangan[i] +
                        " (Kuota First Class Tersedia: " + kelasTersedia[i][0] +
                        ", Kuota Bisnis Tersedia: " + kelasTersedia[i][1] +
                        ", Kuota Ekonomi Tersedia: " + kelasTersedia[i][2] + ")");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada penerbangan yang ditemukan untuk tujuan tersebut.");
        }
    }

    // Method untuk memesan tiket
    /**
     * @pilihPenerbangan memilih Rute penerbangan dalam Array
     * @indeksPenerbangan menampilkan inputan Pemesan agar sesuai dengan Array Rute
     *                    Penerbangan
     * @pilihKelas memilih Tipe kelas di Rute Penerbangan
     * @indeksKelas menampilkan inputan Pemesan agar sesuai dengan Array Kelas
     *              Penerbangan
     * @name meminta inputan Nama Pemesan
     * @harga menyimpan nilai harga tiket di method bookTicket()
     * @metodePembayaran menyimpan nilai pada method tipe pembayaran di method
     *                   bookTicket()
     */
    public static void bookTicket() throws InterruptedException {

        System.out.println();
        System.out.println("+===========================+");
        System.out.println("||<<<= Pemesanan Tiket =>>>||");
        System.out.println("+===========================+");
        showFlights();
        System.out.println();
        System.out.print(">> Pilih penerbangan (1-15): ");
        int pilihPenerbangan = input.nextInt();
        input.nextLine(); // Clear the newline

        if (pilihPenerbangan < 1 || pilihPenerbangan > 15) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }

        int indeksPenerbangan = pilihPenerbangan - 1;

        System.out.print("\n >> 1. First Class \n >> 2. Bisnis \n >> 3. Ekonomi \n\nPilih Kelas: ");
        int pilihKelas = input.nextInt();
        input.nextLine(); // Clear the newline

        if (pilihKelas < 1 || pilihKelas > 3) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }

        int indeksKelas = pilihKelas - 1;
        if (kelasTersedia[indeksPenerbangan][indeksKelas] > 0) {
            System.out.print("Masukkan nama Pemesan: ");
            String name = input.nextLine();

            System.out.print("masukkan tanggal Keberangkatan Pemesan (hari/bulan/tahun): ");
            tanggalKeberangkatan = input.nextLine();

            double harga = calculatePrice(indeksPenerbangan, pilihKelas);
            String metodePembayaran = metodePembayaran(prosesPembayaran(harga));

            for (int i = 0; i < reservasiUser[indeksPenerbangan][indeksKelas].length; i++) {
                if (reservasiUser[indeksPenerbangan][indeksKelas][i] == null) {
                    reservasiUser[indeksPenerbangan][indeksKelas][i] = new Reservasi(name,
                            rutePenerbangan[indeksPenerbangan],
                            kelasPenerbangan(pilihKelas), harga, metodePembayaran, tanggalKeberangkatan);
                    kelasTersedia[indeksPenerbangan][indeksKelas]--;

                    System.out.printf(
                            "\n\n\t Tiket berhasil dipesan atas Nama: %s \n\t Rute Penerbangan: %s \n\t Waktu tanggal Keberangkatan: %s \n\t Kelas: %s \n\t Harga Tiket One-Way Rp.%,.2f \n\t Metode Pembayaran: %s",
                            name, rutePenerbangan[indeksPenerbangan], tanggalKeberangkatan,
                            kelasPenerbangan(pilihKelas), harga,
                            metodePembayaran);
                    System.out.println("\n");
                    break;
                }
            }
        } else if (kelasTersedia[indeksPenerbangan][indeksKelas] == 0) {
            System.out.println("Maaf, kursi untuk kelas yang Anda pilih tidak tersedia di Rute Penerbangan ini");
            System.out.println();
        } else {
            System.out.println("Maaf, kursi sudah penuh untuk kelas ini silahkan pilih kelas yang lain.");
            System.out.println();
        }
    }

    // Method untuk membatalkan tiket
    /**
     * @name nama Pemesan yang telah diinputkan
     * @found kondisi untuk menemukan dan menampilkan detail tentang pembatalan
     *        reservasi tiket
     */
    public static void cancelTicket() throws InterruptedException {
        System.out.println();
        System.out.println("+============================+");
        System.out.println("||<<<= Pembatalan Tiket =>>>||");
        System.out.println("+============================+");
        System.out.println("+=====================+");
        System.out.print(" Masukkan nama Pemesan yang akan membatalkan Reservasi-nya: ");
        String name = input.nextLine();
        System.out.println();

        for (int i = 0; i < 90; i++) {
            System.out.print("|");
            Thread.sleep(10);
        }

        boolean found = false;
        for (int i = 0; i < rutePenerbangan.length; i++) {
            for (int j = 0; j < tanggalKeberangkatan.length(); j++) {
                for (int k = 0; k < reservasiUser[i].length; k++) {
                    for (int l = 0; l < reservasiUser[i][k].length; l++) {
                        if (reservasiUser[i][k][l] != null
                                && reservasiUser[i][k][l].passengerName.equalsIgnoreCase(name)) {
                            System.out.println(
                                    "\n\nTiket atas nama " + name + " | Rute penerbangan " + rutePenerbangan[i]
                                            + " | kelas " + kelasPenerbangan(k + 1) + " | Waktu Keberangkatan "
                                            + tanggalKeberangkatan + ", Berhasil dibatalkan.");
                            reservasiUser[i][k][l] = null;
                            kelasTersedia[i][k]++;
                            found = true;
                            break;
                        }
                    }
                }
            }
        }
        if (!found) {
            System.out.println();
            System.out.println();
            System.out.println("Tiket tidak ditemukan atas nama tersebut.");
        }
    }

    // Method untuk menampilkan daftar pemesan
    public static void showBookings() {
        System.out.println();
        System.out.println(
                "+===============================================================================================+");
        System.out.println(
                "||                                <<<= Daftar Pemesanan =>>>                                   ||");
        System.out.println(
                "+===============================================================================================+");
        System.out.println();

        for (int i = 0; i < rutePenerbangan.length; i++) {
            System.out.println(
                    "+==============================================================================================");
            System.out.println("+) Penerbangan: " + rutePenerbangan[i]);
            System.out.println(
                    "+==============================================================================================");
            for (int j = 0; j < reservasiUser[i].length; j++) {

                System.out.println("=>> Kelas " + kelasPenerbangan(j + 1) + ": ");

                for (int k = 0; k < reservasiUser[i][j].length; k++) {
                    if (reservasiUser[i][j][k] != null) {
                        System.out.println("\t" + (k + 1) + ". " + reservasiUser[i][j][k]);
                    }
                }
                System.out.println(
                        "\t------------------------   ||  -------------------------  ||  -----------------------");
            }
            System.out.println();
        }
    }

    // Method untuk mendapatkan nama kelas berdasarkan indeks
    /**
     * @param classChoice Pilihan Kelas
     *                    1. First Class
     *                    2. Bisnis
     *                    3. Ekonomi
     */
    public static String kelasPenerbangan(int classChoice) {
        switch (classChoice) {
            case 1:
                return "First class";
            case 2:
                return "Bisnis";
            case 3:
                return "Ekonomi";
            default:
                return "Unknown";
        }
    }

    // Method untuk menghitung harga tiket berdasarkan kelas
    /**
     * @calculatePrice jadi di methods ini berfungsi untuk menhitung harga tiket
     *                 penerbangan
     * 
     * @param flightIndex : Rute Penerbangan
     * @param classChoice : Pilihan kelas
     * @basePrice Harga Dasar untuk semua penerbangan
     * @layananBisnis addon pelayanan pada Kelas Bisnis
     * @layananPremium addon pelayanan pada First Class
     */
    public static double calculatePrice(int flightIndex, int classChoice) {

        double basePrice;
        double layananBisnis = 3000000;
        double layananPremium = 5000000;
        switch (flightIndex) {
            case 0:
                basePrice = 1470000; // Jakarta - Bali
                break;
            case 1:
                basePrice = 1050000; // Jakarta - Surabaya
                break;
            case 2:
                basePrice = 1640000; // Jakarta - Medan
                break;
            case 3:
                basePrice = 1250000; // Surabaya - Makassar
                break;
            case 4:
                basePrice = 1380000; // Medan - Bali
                break;
            case 5:
                basePrice = 2170000; // Aceh - Jakarta
                break;
            case 6:
                basePrice = 910000; // Surabaya - Lombok
                break;
            case 7:
                basePrice = 1200000; // Balikpapan - Surabaya
                break;
            case 8:
                basePrice = 2100000; // Surabaya - Manado
                break;
            case 9:
                basePrice = 1524000; // Surabaya - Kupang
                break;
            case 10:
                basePrice = 2766300; // papua - banda aceh
                break;
            case 11:
                basePrice = 1232000; // jakarta - lombok
                break;
            case 12:
                basePrice = 757000; // batam - padang
                break;
            case 13:
                basePrice = 912700; // Bali denpasa - Yogyakarta
                break;
            case 14:
                basePrice = 1232000; // jakarta - banda aceh
                break;
            default:
                basePrice = 0;
                break;
        }

        switch (classChoice) {
            case 1:
                return (basePrice + layananPremium) * 2; // First class
            case 2:
                return (basePrice + layananBisnis) * 2; // Bisnis
            case 3:
                return basePrice * 1.2; // Ekonomi
            default:
                return basePrice;
        }
    }

    /**
     * @login methods ini berfungsi untuk menampilkan login dan login ini berfungsi
     *        untuk memasuki aplikasi ini
     * 
     * @tries berfungsi untuk perulangan dan untuk menampilkan fungsi loading
     * @maxTries untuk indikator berapa kali anda bisa memasukan password ketika
     *           anda salah
     * @indikator menjadi indikator ketikan benar login bisa masuk ke menu utama
     * @isAuthenticated fungsi untuk mengetahui login ini benar atau salah
     * 
     * @throws InterruptedException
     */
    @SuppressWarnings("resource")
    public static void Login() throws InterruptedException {

        Scanner input = new Scanner(System.in);

        int tries = 0;
        int maxTries = 3;
        int indikator = 0;

        System.out.println("+==========================+");
        System.out.println("||   <<<=LOGIN PAGE=>>>   ||");
        System.out.println("+==========================+");
        System.out.println();
        do {
            System.out.println("+================+");
            System.out.print(" Enter username: ");
            String username = input.nextLine();
            System.out.println();
            System.out.println("+================+");
            System.out.print("Enter password: ");
            String password = input.nextLine();
            System.out.println();

            boolean isAuthenticated = false;
            for (int i = 0; i < validUsernames.length; i++) {
                if (username.equalsIgnoreCase(validUsernames[i]) && password.equalsIgnoreCase(validPasswords[i])) {
                    isAuthenticated = true;
                    break;
                }
            }

            if (isAuthenticated) {
                for (int i = 0; i < 65; i++) {
                    System.out.print("|");
                    Thread.sleep(11);
                }
                System.out.println();
                System.out.println("\n\t\t\t" + password + " Berhasil Login");
                indikator = 1;
                System.out.println();
            } else {
                tries++;
                for (int i = 0; i < 120; i++) {
                    System.out.print("|");
                    Thread.sleep(12);
                }
                System.out.println();
                System.out.println("\n\t\t\t\tUsername atau Password yang di Inputkan salah. Coba Lagi");
                System.out.println();

                if (tries >= maxTries) {
                    System.out.println("Akun telah mencapai batas maksimum setelah " + maxTries + " Percobaan Gagal"
                            + ", Re-Login lagi!");
                    System.exit(0);
                }
            }
        } while (indikator != 1 && tries < maxTries);
    }

    // Method untuk memproses pembayaran

    /**
     * 
     * @param pilihanPembayaran pilihan pembayaran yang disediakan Maskapai
     */
    public static String metodePembayaran(int pilihanPembayaran) {
        switch (pilihanPembayaran) {
            case 1:
                return "Kartu Kredit";
            case 2:
                return "Kartu Debit";
            case 3:
                return "Transfer Bank";
            case 4:
                return "E-Wallet";
            default:
                return "Unknown";
        }
    }

    /**
     * @param amount besaran biaya di masing-masing Metode Pembayaran
     * @pilihPembayaran Pemesan memilih metode Pembayaran
     */
    public static int prosesPembayaran(double amount) throws InterruptedException {
        System.out.println();
        System.out.println(">> Pilih Tipe Pembayaran:\n");
        System.out.println("1. Kartu Kredit");
        System.out.println("2. Kartu Debit");
        System.out.println("3. Transfer Bank");
        System.out.println("4. E-Wallet\n");
        System.out.print("Pilih metode pembayaran: ");
        int pilihPembayaran = input.nextInt();
        input.nextLine(); // Clear the newline

        switch (pilihPembayaran) {
            case 1:
                processCreditCardPayment(amount);
                break;
            case 2:
                processDebitCardPayment(amount);
                break;
            case 3:
                processBankTransfer(amount);
                break;
            case 4:
                processEWalletPayment(amount);
                break;
            default:
                System.out.println("Metode pembayaran tidak valid. Silakan coba lagi.");
                prosesPembayaran(amount);
                break;
        }
        return pilihPembayaran;
    }

    /**
     * 
     * @param amount besaran biaya di masing-masing Metode Pembayaran
     * @creditCard nomor kartu Kredit Pemesan
     * @nb catatan yang bisa di inputkan Pemesan
     */
    @SuppressWarnings("unused")
    public static void processCreditCardPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor kartu kredit: ");
        String creditCard = input.nextLine();
        System.out.print("Masukkan Catatan: ");
        String nb = input.nextLine();
        // Memproses pembayaran dengan Kartu Kredit
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f dari nomor kartu Kredit: %s kepada Pihak Maskapai",
                amount, creditCard);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }

    /**
     * 
     * @param amount besaran biaya di masing-masing Metode Pembayaran
     * @debitCard nomor kartu Debit pemesan
     * @nb catatan yang dapat di inputkan Pemesan
     */
    @SuppressWarnings("unused")
    public static void processDebitCardPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor kartu debit: ");
        String debitCard = input.nextLine();
        System.out.print("Masukkan Catatan: ");
        String nb = input.nextLine();
        // Memproses pembayaran dengan Kartu Debit
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f dari nomor kartu Debit: %s kepada Pihak Maskapai",
                amount, debitCard);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }

    /**
     * 
     * @param amount besaran biaya di masing-masing Metode Pembayaran
     * @accountNumber nomor Rekening dari Pemesan
     */
    public static void processBankTransfer(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor rekening: ");
        String accountNumber = input.nextLine();
        System.out.println();
        // Memproses pembayaran dengan Transfer bank
        System.out.printf("Rekening %s Memproses transfer sebesar Rp %,.2f ke pihak Maskapai", accountNumber, amount);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Transfer berhasil!");
    }

    /**
     * 
     * @param amount besaran biaya di masing-masing Metode Pembayaran
     * @ewalletId ID E-wallet dari Pemesan
     */
    public static void processEWalletPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan ID E-Wallet: ");
        String ewalletId = input.nextLine();
        // Memproses pembayaran dengan E-Wallet
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f dari ID E-Wallet: %s kepada Pihak Maskapai", amount,
                ewalletId);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }
}