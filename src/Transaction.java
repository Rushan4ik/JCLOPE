import java.util.Arrays;

@SuppressWarnings("ClassCanBeRecord")
public class Transaction {
    private final String[] data;
    private final boolean status;
    public Transaction(String[] data, boolean status) {
        this.data = data;
        this.status = status;
    }

    public String[] getData() {
        return data;
    }

    public int getLength() {
        return data.length;
    }


    @Override
    public String toString() {
        return data[0];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    public boolean getStatus() {
        return status;
    }

}
