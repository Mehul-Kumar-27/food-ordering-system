import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MatrixServer {
    public static void main(String[] args) {
        try {
            // Create RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(8000);

            // Create matrix operations implementation
            MatrixOperations matrixService = new MatrixOperationsImpl();

            // Bind the remote object to the registry
            registry.rebind("MatrixOperations", matrixService);

            System.out.println("Matrix Operations Server is running...");
        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}