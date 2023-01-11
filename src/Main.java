import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    private static final HashMap<Integer, Integer> mapTransactionNumberToClusterNumber = new HashMap<>();

    private static ArrayList<Cluster> readTransactions(double repulsion) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("agaricus-lepiota.data"));
        ArrayList<Cluster> clusters = new ArrayList<>();
        int transactionId = 0;
        while (scanner.hasNextLine()) {
            Transaction transaction = new Transaction(scanner.nextLine().split(","));
            ++transactionId;
            int clusterNum = clusters.size();
            Cluster newCluster = new Cluster();
            double value = newCluster.deltaAdd(transaction, repulsion);
            System.out.println(transactionId);
            System.out.println("\tvalue: " + value);
            for (int i = 0; i < clusters.size(); ++i) {
                Cluster current = clusters.get(i);
                double currentValue = current.deltaAdd(transaction, repulsion);
                if (currentValue > value) {
                    System.out.println(currentValue);
                    value = currentValue;
                    clusterNum = i;
                }
            }
            if (clusterNum == clusters.size()) {
                clusters.add(newCluster);
            } else {
                clusters.get(clusterNum).addTransaction(transaction);
            }
            mapTransactionNumberToClusterNumber.put(transactionId, clusterNum);
        }
        return clusters;
    }


    public static void main(String[] args) throws FileNotFoundException {
        double repulsion = 2.6;
        readTransactions(repulsion);

        boolean moved = false;
        mapTransactionNumberToClusterNumber.forEach((k, v) -> {
            System.out.printf("\t%d: %d\n", k, v);
        });
        do {
            moved = true;
        } while (!moved);
    }
}