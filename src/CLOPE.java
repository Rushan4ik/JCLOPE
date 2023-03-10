import java.util.*;

public final class CLOPE {
    private final List<Cluster> clusters = new LinkedList<>();
    private final List<Transaction> transactions;
    private final HashMap<Transaction, Cluster> table = new HashMap<>();
    private double repulsion;

    public CLOPE(List<Transaction> transactions, double repulsion) {
        this.repulsion = repulsion;
        this.transactions = transactions;
    }

    @SuppressWarnings("unused")
    public List<Cluster> getClusters() {
        return clusters;
    }

    public List<Cluster> execute() {
        clusters.clear();

        init();

        boolean moved = false;
        do {
            for (Transaction transaction : transactions) {
                moved = false;
                Cluster keepingCluster = table.get(transaction);
                double removeCost = keepingCluster.deltaRemove(transaction, repulsion);

                double maxValue = 0;
                Cluster current = null;
                for (Cluster otherCluster : clusters) {
                    if (otherCluster == keepingCluster) continue;
                    double cost = otherCluster.deltaAdd(transaction, repulsion) + removeCost;
                    if (cost > maxValue) {
                        maxValue = cost;
                        current = otherCluster;
                    }
                }

                if (maxValue > 0) {
                    if (current.isEmpty()) {
                        clusters.add(new Cluster());
                    }
                    keepingCluster.removeTransaction(transaction);
                    current.addTransaction(transaction);
                    table.put(transaction, current);
                    moved = true;
                }
            }
        } while (moved);

        clusters.removeIf(Cluster::isEmpty);
        return clusters;
    }

    private void init() {
        clusters.add(new Cluster());
        for (Transaction transaction : transactions) {
            double maxCost = 0;
            Cluster current = null;
            for (Cluster cluster : clusters) {
                double clusterCost = cluster.deltaAdd(transaction, repulsion);
                if (clusterCost > maxCost) {
                    maxCost = clusterCost;
                    current = cluster;
                }
            }
            assert current != null;
            if (current.isEmpty()) {
                clusters.add(new Cluster());
            }
            table.put(transaction, current);
            current.addTransaction(transaction);
        }
    }


    @SuppressWarnings("unused")
    public void setRepulsion(double repulsion) {
        this.repulsion = repulsion;
    }

    @SuppressWarnings("unused")
    public double getRepulsion() {
        return repulsion;
    }
}
