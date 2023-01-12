import java.util.Iterator;

public interface TransactionCollection  {

    boolean hasNextTransaction();

    Transaction nextTransaction();
}
