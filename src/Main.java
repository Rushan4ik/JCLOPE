import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CLOPE clope = new CLOPE(readTransactions());
        List<Cluster> clusters = clope.goCLOPE(2);
        for (Cluster cluster : clusters) {
            System.out.printf("%s - %d\n", cluster, cluster.getTransactionCount());
        }
        System.out.println(clusters.size());
    }

    private static List<Transaction> readTransactions() throws FileNotFoundException {
        ArrayList<Transaction> arrayList = new ArrayList<>(8200);
        Scanner scanner = new Scanner(new File("abalone.data"));
        int i = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.charAt(0) == 'M') i++;
            arrayList.add(new Transaction(line.substring(2).split(","), line.charAt(0) == 'M'));
        }
        System.out.println(i);
        return arrayList;
    }
/*    private static ArrayList<Cluster> clusters = new ArrayList<>();

    private static ArrayList<Transaction> readTransactions() throws FileNotFoundException {
        ArrayList<Transaction> transactions = new ArrayList<>(8200);
        Scanner scanner = new Scanner(new File("agaricus-lepiota.data"));
        while (scanner.hasNextLine()) {
            transactions.add(new Transaction(scanner.nextLine().split(",")));
        }
        return transactions;
    }


    public static void main(String[] args) throws FileNotFoundException {
        double repulsion = 2.6 * 3;
        ArrayList<Transaction> transactions = readTransactions();
        HashMap<Integer, Integer> mapTransactionToCluster = new HashMap<>();
        for (int i = 0; i < transactions.size(); ++i) {
            int resIndex = handleTransaction(transactions.get(i), repulsion);
            mapTransactionToCluster.put(i, resIndex);
        }
        Map<Integer, Integer> mapCtoT = new HashMap<>();
        mapTransactionToCluster.forEach((k, v) -> {
            mapCtoT.put(v, mapCtoT.getOrDefault(v, 0) + 1);
        });
        mapCtoT.forEach((k, v) -> System.out.printf("%d: %d\n", k, v));
    }

    private static int handleTransaction(Transaction transaction, double repulsion) {
        int index = -1;
        Cluster newCluster = new Cluster();
        double value = newCluster.deltaAdd(transaction, repulsion);
        double other = 0;
        for (int i = 0; i < clusters.size(); ++i) {
            Cluster cluster = clusters.get(i);
            double current = cluster.deltaAdd(transaction, repulsion);
            if (current > other) {
                other = current;
                index = i;
            }
        }
        if (value > other) {
            clusters.add(newCluster);
            index = clusters.size() - 1;
        }
        clusters.get(index).addTransaction(transaction);
        return index;
    }*/
}