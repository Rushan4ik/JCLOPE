import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public final class CLOPE {
    private List<Cluster> clusters = new LinkedList<>();
    private final List<Transaction> transactions;
    private double repulsion = 2;

    public CLOPE(List<Transaction> transactions, double repulsion) {
        this.repulsion = repulsion;
        this.transactions = transactions;
    }

    public List<Cluster> getClusters() {
        return clusters;
    }

    public List<Cluster> execute() {
        clusters.clear();

        init();

        boolean moved = false;
        do {
            moved = true;
        } while (moved);

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
            current.addTransaction(transaction);
        }
    }


    public void setRepulsion(double repulsion) {
        this.repulsion = repulsion;
    }

    public double getRepulsion() {
        return repulsion;
    }
}
