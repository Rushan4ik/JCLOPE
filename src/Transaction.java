import java.util.Arrays;

@SuppressWarnings("ClassCanBeRecord")
public class Transaction {
    private final String[] data;
    public Transaction(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

    public int getLength() {
        return data.length;
    }
}
