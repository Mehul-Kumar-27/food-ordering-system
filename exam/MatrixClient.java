import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MatrixClient {
    private static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%8.2f", val);
            }
            System.out.println();
        }
    }

    private static double[][] inputMatrix(Scanner scanner, String matrixName) {
        System.out.printf("Enter rows for %s: ", matrixName);
        int rows = scanner.nextInt();
        System.out.printf("Enter columns for %s: ", matrixName);
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];
        System.out.printf("Enter %d x %d matrix values:\n", rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        try {
            // Connect to RMI registry
            Registry registry = LocateRegistry.getRegistry("localhost", 8000);
            MatrixOperations matrixService = 
                (MatrixOperations) registry.lookup("MatrixOperations");

            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\nMatrix Operations Menu:");
                System.out.println("1. Matrix Addition");
                System.out.println("2. Matrix Multiplication");
                System.out.println("3. Matrix Transposition");
                System.out.println("4. Matrix Determinant");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                try {
                    switch (choice) {
                        case 1: {
                            double[][] matrixA = inputMatrix(scanner, "Matrix A");
                            double[][] matrixB = inputMatrix(scanner, "Matrix B");
                            double[][] result = matrixService.add(matrixA, matrixB);
                            System.out.println("Result of Addition:");
                            printMatrix(result);
                            break;
                        }
                        case 2: {
                            double[][] matrixA = inputMatrix(scanner, "Matrix A");
                            double[][] matrixB = inputMatrix(scanner, "Matrix B");
                            double[][] result = matrixService.multiply(matrixA, matrixB);
                            System.out.println("Result of Multiplication:");
                            printMatrix(result);
                            break;
                        }
                        case 3: {
                            double[][] matrix = inputMatrix(scanner, "Matrix");
                            double[][] result = matrixService.transpose(matrix);
                            System.out.println("Transposed Matrix:");
                            printMatrix(result);
                            break;
                        }
                        case 4: {
                            double[][] matrix = inputMatrix(scanner, "Matrix");
                            double result = matrixService.determinant(matrix);
                            System.out.println("Matrix Determinant: " + result);
                            break;
                        }
                        case 5:
                            System.out.println("Exiting...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } catch (IllegalArgumentException e) {
                    System.err.println("Operation Error: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.err.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}