import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String fileName;
    private static double repulsion;
    private static void readInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        fileName = scanner.next();
        System.out.println("Enter repulsion:");
        repulsion = scanner.nextDouble();
    }

    private static ArrayList<Transaction> readTransactions() throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        FileTransactionReader reader = new FileTransactionReader(
                new File(fileName),
                line -> {
                    String status = String.valueOf(line.charAt(0));
                    return new Transaction(line.split(","), status);
                });
        while (reader.hasNextTransaction()) {
            transactions.add(reader.nextTransaction());
        }
        reader.close();
        return transactions;
    }

    public static void main(String[] args) throws IOException {
        readInput();
        ArrayList<Transaction> transactions = readTransactions();

        CLOPE clope = new CLOPE(transactions, repulsion);

        List<Cluster> clusters = clope.execute();
        System.out.println(clusters.size());
        for (Cluster cluster : clusters) {
            System.out.println(cluster);
        }
    }
}