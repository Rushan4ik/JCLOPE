import java.math.BigDecimal;
import java.util.*;

import static java.lang.Math.pow;

public class Cluster {
    private final HashMap<String, Integer> occ = new HashMap<>();
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private int square = 0;

    public Cluster() {}

    public Set<String> distinctElements() {
        return occ.keySet();
    }

    public int frequencyOfElement(String element) {
        return occ.getOrDefault(element, 0);
    }

    public int getSquare() {
        return square;
    }

    public void addTransaction(Transaction transaction) {
        square += transaction.getLength();
        transactions.add(transaction);
        for (String c : transaction.getData()) {
            if (occ.containsKey(c)) {
                occ.put(c, occ.get(c) + 1);
            } else {
                occ.put(c, 1);
            }
        }
    }

    public void removeTransaction(Transaction transaction) {
        square -= transaction.getLength();
        transactions.remove(transaction);
        for (String c : transaction.getData()) {
            if (occ.containsKey(c)) {
                int freq = occ.remove(c) - 1;
                if (freq != 0) {
                    occ.put(c, freq);
                }
            } else {
                System.out.println("Bad delete: " + c);
            }
        }
    }

    public int getWidth() {
        return occ.size();
    }

    public double getHeight() {
        return getSquare() * 1. / getWidth();
    }

    public double deltaAdd(Transaction transaction, double repulsion) {
        int newSquare = square + transaction.getLength();
        int width = getWidth();
        int newWidth = width;
        Set<String> elements = new HashSet<>(distinctElements());
        for (String element : transaction.getData()) {
            if (!elements.contains(element)) {
                newWidth++;
                elements.add(element);
            }
        }
        int transactionsCount = transactions.size();
        double newValue = newSquare * (transactionsCount + 1) * 1. / pow(newWidth, repulsion);
        double oldValue = square * transactionsCount * 1. / pow(width, repulsion);
        if (Double.isNaN(oldValue)) {
            return newValue;
        } else {
            return newValue - oldValue;
        }
    }

    public double deltaRemove(Transaction transaction, double repulsion) {
        int newSquare = square - transaction.getLength();
        int width = getWidth();
        int newWidth = width;
        for (String element : transaction.getData()) {
            if (occ.containsKey(element) && occ.get(element) == 1) {
                newWidth--;
            }
        }
        int transactionsCount = transactions.size();
        double newValue = newSquare * (transactionsCount - 1) * 1. / pow(newWidth, repulsion);
        double oldValue = square * transactionsCount * 1. / pow(width, repulsion);
        if (Double.isNaN(newValue)) {
            return oldValue;
        } else {
            return newValue - oldValue;
        }
    }


    public int getTransactionCount() {
        return transactions.size();
    }

    @Override
    public String toString() {
        int c = 0;
        for (Transaction t : transactions) {
            if (t.getStatus()) c++;
        }
        return "Cluster[" + c + "/" + transactions.size() + "|" + "%6.2f".formatted(c * 100. / transactions.size()) + "]";
    }
}