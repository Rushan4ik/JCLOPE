import java.util.*;

public final class Cluster {
    private final Set<Transaction> transactions = new HashSet<>();
    private final HashMap<String, Integer> occurrence = new HashMap<>();
    private int square = 0;

    public Cluster() {}

    public Cluster(Transaction... transactions) {
        for (Transaction t : transactions) {
            addTransaction(t);
        }
    }

    public Cluster(Collection<Transaction> transactions) {
        for (Transaction transaction : transactions) {
            addTransaction(transaction);
        }
    }

    public int getWidth() {
        return occurrence.size();
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public int getTransactionCount() {
        return transactions.size();
    }

    public void addTransaction(Transaction transaction) {
        square += transaction.getLength();
        transactions.add(transaction);
        for (String element : transaction.getData()) {
            occurrence.put(element, occurrence.getOrDefault(element, 0) + 1);
        }
    }

    public void removeTransaction(Transaction transaction) {
        square -= transaction.getLength();
        transactions.remove(transaction);
        for (String element : transaction.getData()) {
            int newCount = occurrence.remove(element) - 1;
            if (newCount != 0) {
                occurrence.put(element, newCount);
            }
        }
    }

    public boolean containsTransaction(Transaction transaction) {
        return transactions.contains(transaction);
    }

    public boolean isEmpty() {
        return transactions.isEmpty();
    }

    private double countValue(double repulsion) {
        return isEmpty() ? 0 : square * getTransactionCount() * 1. / Math.pow(getWidth(), repulsion);
    }

    public double deltaAdd(Transaction transaction, double repulsion) {
        double oldValue = countValue(repulsion);
        addTransaction(transaction);
        double newValue = countValue(repulsion);
        removeTransaction(transaction);
        return newValue - oldValue;
    }

    public double deltaRemove(Transaction transaction, double repulsion) {
        double oldValue = countValue(repulsion);
        removeTransaction(transaction);
        double newValue = countValue(repulsion);
        addTransaction(transaction);
        return newValue - oldValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cluster cluster = (Cluster) o;
        return square == cluster.square && Objects.equals(transactions, cluster.transactions) && Objects.equals(occurrence, cluster.occurrence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactions, occurrence, square);
    }

    @Override
    public String toString() {
        HashMap<String, Integer> temp = new HashMap<>();
        for (Transaction transaction : transactions) {
            String status = transaction.getStatus();
            temp.put(status, temp.getOrDefault(status, 0) + 1);
        }
        return "[Cluster: " + temp + "]";
    }
}
