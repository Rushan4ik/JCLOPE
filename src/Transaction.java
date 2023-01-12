import java.util.Arrays;
import java.util.Objects;

public final class Transaction {
    private final String[] data;
    private final String status;

    public Transaction(String[] data) {
        this.data = data;
        status = null;
    }

    public Transaction(String[] data, String status) {
        this.data = data;
        this.status = status;
    }

    public String[] getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }

    public int getLength() {
        return data.length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Arrays.equals(data, that.data) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(status);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
