import java.util.ArrayList;
import java.util.List;

public class CLOPE {
    private List<Cluster> clusters = new ArrayList<>();
    private final List<Transaction> transactions;

    public CLOPE(List<Transaction> transactions) {
        this.transactions = transactions;
    }
    public List<Cluster> goCLOPE(double repulsion) {
        for (Transaction transaction : transactions) {
            Cluster clusterNew = new Cluster();
            clusterNew.addTransaction(transaction);
            clusters.add(clusterNew);
            double profitFromNewCluster = getProfit(clusters, repulsion);
            double profitMax = profitFromNewCluster;
            clusters.remove(clusterNew);
            for (Cluster cluster : clusters) {
                cluster.addTransaction(transaction);
                double profit = getProfit(clusters, repulsion);
                if (profitMax <= profit) {
                    profitMax = profit;
                } else {
                    cluster.removeTransaction(transaction);
                }
            }
            if (profitMax == profitFromNewCluster) {
                clusters.add(clusterNew);
            }
        }
        return clusters;
    }

    private double getProfit(List<Cluster> clusters, double repulsion) {
        try {
            double numerator = 0;
            double denominator = 0;
            for (Cluster cluster : clusters) {
                numerator += cluster.getHeight() * cluster.getWidth() / Math.pow(cluster.getWidth(), repulsion) * cluster.getTransactionCount();
                denominator += cluster.getTransactionCount();
            }
            return numerator / denominator;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}