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
            for (int i = 0; i < clusters.size(); ++i) {
                Cluster current = clusters.get(i);
                double currentValue = current.deltaAdd(transaction, repulsion);
                if (currentValue > value) {
                    value = currentValue;
                    clusterNum = i;
                }
            }
            if (clusterNum == clusters.size()) {
                newCluster.addTransaction(transaction);
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
        do {
            moved = true;
        } while (!moved);
    }
}