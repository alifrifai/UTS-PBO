import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Kelas Film merepresentasikan entitas film dengan atribut id, judul, genre, dan rating
class Film {
    private int id;
    private String judul;
    private String genre;
    private double rating;

    // Konstruktor untuk menginisialisasi objek Film
    public Film(int id, String judul, String genre, double rating) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.rating = rating;
    }

     // Getter dan Setter untuk atribut Film
    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (rating >= 0 && rating <= 10) {
            this.rating = rating;
        } else {
            System.out.println("! Rating harus antara 0 - 10.");
        }
    }

    public void displayInfo() {
        System.out.printf("| %-3d | %-20s | %-10s | %-5.1f |\n", id, judul, genre, rating);
    }
}

// Menu utama program crud film 
public class FilmCRUD {
    private static ArrayList<Film> daftarFilm = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounter = 1;

    public static void main(String[] args) {
        while (true) {
            clearScreen();
            System.out.println("\n==================================");
            System.out.println("★   WELCOME TO MY PROJECT FILM   ★");
            System.out.println("==================================");
            System.out.println("1. Tambah Film");
            System.out.println("2. Lihat Daftar Film");
            System.out.println("3. Edit Film");
            System.out.println("4. Hapus Film");
            System.out.println("5. Keluar");
            System.out.println("==================================");
            System.out.print("> Pilih opsi: ");

            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine(); 

                switch (pilihan) {
                    case 1:
                        tambahFilm();
                        break;
                    case 2:
                        lihatFilm();
                        break;
                    case 3:
                        editFilm();
                        break;
                    case 4:
                        hapusFilm();
                        break;
                    case 5:
                        keluar();
                        return;
                    default:
                        System.out.println("! Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("X Input harus berupa angka! Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    }

    // keluar dari menu
    private static void keluar() {
        clearScreen();
        System.out.println("-> Terima kasih telah menggunakan sistem ini!");
    }

    // menambah film
    private static void tambahFilm() {
        clearScreen();
        System.out.println("\n> Tambah Film Baru");
        System.out.print("Masukkan judul film: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan genre film: ");
        String genre = scanner.nextLine();

        double rating = cekInputRating();
        Film film = new Film(idCounter++, judul, genre, rating);
        daftarFilm.add(film);
        System.out.println("Film berhasil ditambahkan!");
    }

    // melihat film yg sudah di tambhkan
    private static void lihatFilm() {
        while (true) {
            clearScreen();
            if (daftarFilm.isEmpty()) {
                System.out.println("Tidak ada film dalam daftar.");
            } else {
                System.out.println("\n==================================================");
                System.out.println("| ID  | Judul Film          | Genre     | Rating |");
                System.out.println("==================================================");
                for (Film film : daftarFilm) {
                    film.displayInfo();
                }
                System.out.println("=================================================");
            }
            System.out.print("<- Tekan Enter untuk kembali...");
            scanner.nextLine();
            return;
        }
    }

    // lihat untuk yg dibagian edit film dan hapus film
    private static void lihat() {
        while (true) {
            clearScreen();
            if (daftarFilm.isEmpty()) {
                System.out.println("Tidak ada film dalam daftar.");
            } else {
                System.out.println("\n==================================================");
                System.out.println("| ID  | Judul Film          | Genre     | Rating |");
                System.out.println("==================================================");
                for (Film film : daftarFilm) {
                    film.displayInfo();
                }
                System.out.println("=================================================");
            }
            return;
        }
    }

    // mengedit film berdasarkan id
    private static void editFilm() {
        clearScreen();
        lihat();
        System.out.print("\nMasukkan ID film yang ingin diedit: ");
        int id = cekInputAngka();

        Film film = cariFilm(id);
        if (film == null) {
            System.out.println("X Film tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan judul baru: ");
        film.setJudul(scanner.nextLine());
        System.out.print("Masukkan genre baru: ");
        film.setGenre(scanner.nextLine());
        film.setRating(cekInputRating());
        System.out.println("Film berhasil diperbarui!");
    }

    // untuk menghapus film berdasarkan id
    private static void hapusFilm() {
        clearScreen();
        lihat();
        System.out.print("\nMasukkan ID film yang ingin dihapus: ");
        int id = cekInputAngka();

        Film film = cariFilm(id);
        if (film == null) {
            System.out.println("X Film tidak ditemukan.");
            return;
        }

        daftarFilm.remove(film);
        System.out.println("Film berhasil dihapus!");
    }

    // untuk mencari film berdasarkan id (misal nnti ppas edit film dia nentuinnya dari id)
    private static Film cariFilm(int id) {
        for (Film film : daftarFilm) {
            if (film.getId() == id) {
                return film;
            }
        }
        return null;
    }

    // metode untuk validasi bilangan INT atau inputan angka
    private static int cekInputAngka() {
        while (true) {
            try {
                int angka = scanner.nextInt();
                scanner.nextLine();
                return angka;
            } catch (InputMismatchException e) {
                System.out.println("X Harap masukkan angka yang valid.");
                scanner.nextLine();
            }
        }
    }

    // untuk validasi rating supaya ga keinput string
    private static double cekInputRating() {
        while (true) {
            try {
                System.out.print("Masukkan rating film (0-10): ");
                double rating = scanner.nextDouble();
                scanner.nextLine();
                if (rating >= 0 && rating <= 10) return rating;
                System.out.println("! Rating harus antara 0 - 10.");
            } catch (InputMismatchException e) {
                System.out.println("X Input harus berupa angka desimal! Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    }

    // metode untuk clearscreen, menghapus menu yg sebelumnya 
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}