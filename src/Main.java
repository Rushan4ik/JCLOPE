import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        FileTransactionReader reader = new FileTransactionReader(
                new File("agaricus-lepiota.data"),
                line -> {
                    String status = String.valueOf(line.charAt(0));
                    return new Transaction(line.replace("?", "").split(","), status);
                });
        while (reader.hasNextTransaction()) {
            transactions.add(reader.nextTransaction());
        }
        CLOPE clope = new CLOPE(transactions, 2);

        List<Cluster> clusters = clope.execute();
        int i = 0;
        System.out.println(clusters.size());
        for (Cluster cluster : clusters) {
            System.out.println(cluster);
        }
    }
}