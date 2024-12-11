package Marketplace.view;

import Marketplace.entities.Customer;
import Marketplace.services.CustomerServices;
import Marketplace.services.CustomerServicesImpl;

import java.util.Scanner;

public class TerminalView {
    private CustomerServices customerServices;
    private Scanner scanner;

    public TerminalView() {
        this.customerServices = new CustomerServicesImpl();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Customer");
            System.out.println("2. Lihat Semua Customer");
            System.out.println("3. Cari Customer");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // untuk menghapus newline yang tersisa

            switch (pilihan) {
                case 1:
                    tambahCustomer();
                    break;
                case 2:
                    lihatSemuaCustomer();
                    break;
                case 3:
                    cariCustomer();
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void tambahCustomer() {
        System.out.print("Masukkan nama customer: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan email customer: ");
        String email = scanner.nextLine();

        Customer customer = new Customer(nama, email);
        customerServices.registerCustomer(customer);
        System.out.println("Customer berhasil ditambahkan!");
    }

    private void lihatSemuaCustomer() {
        // proses lihat semua customer
        System.out.println("Semua customer:");
        // tampilkan semua customer
    }

    private void cariCustomer() {
        System.out.print("Masukkan ID customer: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // untuk menghapus newline yang tersisa

        Customer customer = customerServices.getCustomerDetails(id);
        if (customer != null) {
            System.out.println("ID: " + customer.getId());
            System.out.println("Nama: " + customer.getName());
            System.out.println("Email: " + customer.getEmail());
        } else {
            System.out.println("Customer tidak ditemukan!");
        }
    }
}