import java.util.HashMap;
import java.util.Set;
import static java.lang.Math.pow;

public class Cluster {
    private final HashMap<String, Integer> occ = new HashMap<>();
    private int square = 0;
    private int transactionsCount = 0;

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
        transactionsCount++;
        for (String c : transaction.getData()) {
            if (occ.containsKey(c)) {
                occ.put(c, occ.get(c) + 1);
                System.out.println("Hello!");
            } else {
                occ.put(c, 1);
            }
        }
    }

    public void removeTransaction(Transaction transaction) {
        square -= transaction.getLength();
        transactionsCount--;
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
        for (String element : transaction.getData()) {
            if (!occ.containsKey(element)) {
                newWidth++;
            }
        }
        double newValue = newSquare * (transactionsCount + 1) * 1. / pow(newWidth, repulsion);
        if (width == 0) {
            return newValue;
        } else {
            double oldValue = square * transactionsCount / pow(width, repulsion);
            return newValue - oldValue;
        }
    }
}
