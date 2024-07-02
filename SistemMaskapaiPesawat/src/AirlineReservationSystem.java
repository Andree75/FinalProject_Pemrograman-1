import java.util.Scanner;

class Booking {
    String passengerName;
    String flight;
    String seatClass;
    double price;
    String paymentType;

    /**
     * aku lai
     * jhgjhjgjg
     * @passenggerName nama penumpang
     * @filght penerbangan
     * @setClass set tipe Kelas yang ada
     * @price harga penerbangan
     * 
     */

    public Booking(String passengerName, String flight, String seatClass, double price, String paymentType) {

        this.passengerName = passengerName;
        this.flight = flight;
        this.seatClass = seatClass;
        this.price = price;
        this.paymentType = paymentType;

    }

    @Override
    public String toString() {
        return String.format("Nama: %s | Penerbangan: %s | Kelas: %s | Harga: Rp%,.2f| Tipe Pembayaran: %s",
                passengerName, flight,
                seatClass,
                price, paymentType);
    }
}

public class AirlineReservationSystem {
    /**
     * @bookings kjahddhkja
     * 
     */
    static Scanner scanner = new Scanner(System.in);
    /**
     * andre
     */
    // Array untuk menyimpan penerbangan yang tersedia
    static String[] flights = { "Jakarta - Denpasar", "Jakarta - Surabaya", "Jakarta - Medan", "Surabaya - Makassar",
            "Medan - Denpasar", "Banda Aceh - Jakarta", "Surabaya - Lombok", "Balikpapan - Surabaya",
            "Surabaya - Manado",
            "Surabaya - Kupang", "Papua - Banda Aceh", "Jakarta - Lombok", "Batam - Padang", "Denpasar - Yogyakarta",
            "Jakarta - Banda Aceh" };
    /**
     * nadhif
     */
    static int[][] seatsAvailable = { { 2, 4, 5 }, { 3, 6, 6 }, { 3, 2, 6 }, { 2, 5, 5 }, { 3, 5, 6 },
            { 1, 3, 6 }, { 2, 6, 5 }, { 2, 1, 6 }, { 3, 4, 5 }, { 0, 1, 6 }, { 0, 2, 5 }, { 0, 3, 0 },
            { 0, 2, 6 }, { 4, 6, 6 }, { 0, 3, 6 } }; // Kursi tersedia untuk masing-masing
    // kelas (First Class, Bisnis, Ekonomi)

    static Booking[][][] bookings = new Booking[15][3][6]; // Menyimpan data pemesan berdasarkan penerbangan dan
    // kelas

    private static String[] validUsernames = { "uisi2023", "uisi2023", "uisi2023" };
    private static String[] validPasswords = { "if9", "andre", "nadhif" };

    public static void main(String[] args) throws InterruptedException {
        boolean close = false;

        Login();

        while (!close) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Clear the newline

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

        System.out.println("\t<<<= Selamat Datang di Sistem Reservasi Pesawat IDNFLYSTARR =>>>");
        System.out.println("1. Tampilkan Daftar Penerbangan");
        System.out.println("2. Cari Penerbangan");
        System.out.println("3. Pesan Tiket");
        System.out.println("4. Batalkan Tiket");
        System.out.println("5. Tampilkan Pemesanan");
        System.out.println("6. Keluar");
        System.out.println();
        System.out.print("Pilih opsi: ");
    }

    // Method untuk menampilkan daftar penerbangan
    public static void showFlights() {
        System.out.println();
        System.out.println("\t\t\t\t\t<<<= Daftar Penerbangan =>>>");
        System.out.println();
        for (int i = 0; i < flights.length; i++) {
            System.out.println((i + 1) + ". " + flights[i] +
                    " (Kuota First Class Tersedia: " + seatsAvailable[i][0] +
                    ", Kuota Bisnis Tersedia: " + seatsAvailable[i][1] +
                    ", Kuota Ekonomi Tersedia: " + seatsAvailable[i][2] + ")\n");
        }
    }

    // Method untuk mencari penerbangan berdasarkan tujuan
    public static void searchFlights() {
        System.out.println();
        System.out.println("<<= Cari Penerbangan =>>");
        System.out.print("Masukkan nama Daerah yang akan anda tuju (ex: Surabaya): ");
        String destination = scanner.nextLine().toLowerCase();
        System.out.println();

        boolean found = false;
        for (int i = 0; i < flights.length; i++) {
            if (flights[i].toLowerCase().contains(destination)) {
                System.out.println((i + 1) + ". " + flights[i] +
                        " (Kuota First Class Tersedia: " + seatsAvailable[i][0] +
                        ", Kuota Bisnis Tersedia: " + seatsAvailable[i][1] +
                        ", Kuota Ekonomi Tersedia: " + seatsAvailable[i][2] + ")\n");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada penerbangan yang ditemukan untuk tujuan tersebut.");
        }
    }

    /**
     * @
     */
    // Method untuk memesan tiket
    public static void bookTicket() throws InterruptedException {

        System.out.println();
        System.out.println("<<<= Pemesanan Tiket =>>>");
        showFlights();
        System.out.println();
        System.out.print(">> Pilih penerbangan (1-15): ");
        int flightChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        if (flightChoice < 1 || flightChoice > 15) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }

        int flightIndex = flightChoice - 1;

        System.out.print("\n >> 1. First Class \n >> 2. Bisnis \n >> 3. Ekonomi \n\nPilih Kelas: ");
        int classChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        if (classChoice < 1 || classChoice > 3) {
            System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            return;
        }

        int classIndex = classChoice - 1;
        if (seatsAvailable[flightIndex][classIndex] > 0) {
            System.out.print("Masukkan nama Pemesan: ");
            String name = scanner.nextLine();

            double price = calculatePrice(flightIndex, classChoice);
            String paymentType = getPaymentTypeName(processPayment(price));

            for (int i = 0; i < bookings[flightIndex][classIndex].length; i++) {
                if (bookings[flightIndex][classIndex][i] == null) {
                    bookings[flightIndex][classIndex][i] = new Booking(name, flights[flightIndex],
                            getSeatClass(classChoice), price, paymentType);
                    seatsAvailable[flightIndex][classIndex]--;
                    System.out.printf(
                            "\n\n\t Tiket berhasil dipesan atas Nama: %s \n\t Rute Penerbangan: %s \n\t Kelas: %s \n\t Harga Tiket One-Way Rp.%,.2f \n\t Tipe Pembayaran: %s",
                            name, flights[flightIndex], getSeatClass(classChoice), price, paymentType);
                    System.out.println("\n");
                    break;
                }
            }
        } else if (seatsAvailable[flightIndex][classIndex] == 0) {
            System.out.println("Maaf, kursi untuk kelas yang Anda pilih tidak tersedia di Rute Penerbangan ini");
            System.out.println();
        } else {
            System.out.println("Maaf, kursi sudah penuh untuk kelas ini.");
            System.out.println();
        }
    }

    // Method untuk membatalkan tiket
    public static void cancelTicket() throws InterruptedException {
        System.out.println();
        System.out.println("\t\t\t\t<<<= Pembatalan Tiket =>>>");
        System.out.print("Masukkan nama Anda: ");
        String name = scanner.nextLine();
        for (int i = 0; i < 100; i++) {
            System.out.print("|");
            Thread.sleep(12);
        }

        boolean found = false;
        for (int i = 0; i < flights.length; i++) {
            for (int j = 0; j < bookings[i].length; j++) {
                for (int k = 0; k < bookings[i][j].length; k++) {
                    if (bookings[i][j][k] != null && bookings[i][j][k].passengerName.equalsIgnoreCase(name)) {
                        System.out.println("\n\nTiket atas nama " + name + " Rute penerbangan " + flights[i] + " kelas "
                                + getSeatClass(j + 1) + " berhasil dibatalkan.");
                        bookings[i][j][k] = null;
                        seatsAvailable[i][j]++;
                        found = true;
                        break;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Tiket tidak ditemukan untuk nama tersebut.");
        }
    }

    // Method untuk menampilkan daftar pemesan
    public static void showBookings() {
        System.out.println();
        System.out.println("\t\t\t\t\t<<<= Daftar Pemesanan =>>>");
        for (int i = 0; i < flights.length; i++) {
            System.out.println("+) Penerbangan: " + flights[i]);
            for (int j = 0; j < bookings[i].length; j++) {
                System.out.println("=>> Kelas " + getSeatClass(j + 1) + ": ");
                for (int k = 0; k < bookings[i][j].length; k++) {
                    if (bookings[i][j][k] != null) {
                        System.out.println("\t" + (k + 1) + ". " + bookings[i][j][k]);
                    }
                }
                System.out.println("\t------------------------\t-------------------------\t-----------------------");
            }
            System.out.println();
        }
    }

    // Method untuk mendapatkan nama kelas berdasarkan indeks
    /**
     * Method ini berfungsi untuk mendapatkan kelas penerbangan
     * 
     * @param classChoice Pilihan Kelas
     *                    1. First Class
     *                    2. Bisnis
     *                    3. Ekonomi
     */
    public static String getSeatClass(int classChoice) {
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
     * 
     * @param flightIndex : Rute Penerbangan
     * @param classChoice : Pilihan kelas
     * @basePrice Harga Dasar
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

    public static void Login() throws InterruptedException {

        Scanner input = new Scanner(System.in);

        int tries = 0;
        int maxTries = 3;
        int indikator = 0;

        System.out.println("\t\t\t<<<=LOGIN PAGE=>>>");
        System.out.println();
        do {
            System.out.print("Enter username: ");
            String username = input.nextLine();
            System.out.println();
            System.out.print("Enter password: ");
            String password = input.nextLine();

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
                System.out.println("\n\t\t\t" + password + " Berhasil Login");
                indikator = 1;
                System.out.println();
            } else {
                tries++;
                for (int i = 0; i < 120; i++) {
                    System.out.print("|");
                    Thread.sleep(12);
                }
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

    public static String getPaymentTypeName(int paymentChoice) {
        switch (paymentChoice) {
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

    public static int processPayment(double amount) throws InterruptedException {
        System.out.println();
        System.out.println(">> Pilih Tipe Pembayaran:\n");
        System.out.println("1. Kartu Kredit");
        System.out.println("2. Kartu Debit");
        System.out.println("3. Transfer Bank");
        System.out.println("4. E-Wallet\n");
        System.out.print("Pilih metode pembayaran: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the newline

        switch (paymentChoice) {
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
                processPayment(amount);
                break;
        }
        return paymentChoice;
    }

    public static void processCreditCardPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor kartu kredit: ");
        String creditCard = scanner.nextLine();
        System.out.print("Masukkan Catatan: ");
        String nb = scanner.nextLine();
        // Simulasikan proses pembayaran
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f menggunakan kartu Kredit kepada Pihak Maskapai",
                amount);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }

    public static void processDebitCardPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor kartu debit: ");
        String debitCard = scanner.nextLine();
        System.out.print("Masukkan Catatan: ");
        String nb = scanner.nextLine();
        // Simulasikan proses pembayaran
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f menggunakan kartu Debit kepada Pihak Maskapai",
                amount);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }

    public static void processBankTransfer(double amount) throws InterruptedException {
        System.out.print("Masukkan nomor rekening: ");
        String accountNumber = scanner.nextLine();
        System.out.println();
        // Simulasikan proses transfer bank
        System.out.printf("Rekening %s Memproses transfer sebesar Rp %,.2f ke pihak Maskapai", accountNumber, amount);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Transfer berhasil!");
    }

    public static void processEWalletPayment(double amount) throws InterruptedException {
        System.out.print("Masukkan ID E-Wallet: ");
        String ewalletId = scanner.nextLine();
        // Simulasikan proses pembayaran E-Wallet
        System.out.printf("Memproses pembayaran sebesar Rp %,.2f menggunakan E-Wallet kepada Pihak Maskapai", amount);
        System.out.println();
        for (int i = 0; i < 70; i++) {
            System.out.print("|");
            Thread.sleep(30);
        }
        System.out.println("\n\t\t Pembayaran berhasil!");
    }
}
