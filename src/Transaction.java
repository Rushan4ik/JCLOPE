@SuppressWarnings("ClassCanBeRecord")
public class Transaction {
    private final char[] data;
    public Transaction(char[] data) {
        this.data = data;
    }

    public char[] getData() {
        return data;
    }

    public int getLength() {
        return data.length;
    }
}
