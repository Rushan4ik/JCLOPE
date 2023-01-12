import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.function.Function;

public class FileTransactionReader implements TransactionCollection, Closeable {
    private final Scanner scanner;
    private final Function<String, Transaction> handler;
    public FileTransactionReader(File file, Function<String, Transaction> handler) throws FileNotFoundException {
        scanner = new Scanner(file);
        this.handler = handler;
    }

    @Override
    public boolean hasNextTransaction() {
        return scanner.hasNextLine();
    }

    @Override
    public Transaction nextTransaction() {
        return handler.apply(scanner.nextLine());
    }

    @Override
    public void close() throws IOException {
        scanner.close();
        IOException exception = scanner.ioException();
        if (exception != null) {
            throw exception;
        }
    }
}
