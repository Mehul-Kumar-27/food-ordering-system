import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MatrixOperationsImpl extends UnicastRemoteObject implements MatrixOperations {
    public MatrixOperationsImpl() throws RemoteException {
        super();
    }

    @Override
    public double[][] add(double[][] matrixA, double[][] matrixB) throws RemoteException {
        // Validate matrix dimensions
        if (matrixA.length != matrixB.length || 
            matrixA[0].length != matrixB[0].length) {
            throw new IllegalArgumentException("Matrices must have identical dimensions for addition");
        }

        int rows = matrixA.length;
        int cols = matrixA[0].length;
        double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    @Override
    public double[][] multiply(double[][] matrixA, double[][] matrixB) throws RemoteException {
        // Validate matrix dimensions for multiplication
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException(
                "Number of columns in first matrix must equal number of rows in second matrix"
            );
        }

        int rowsA = matrixA.length;
        int colsB = matrixB[0].length;
        int colsA = matrixA[0].length;
        double[][] result = new double[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public double[][] transpose(double[][] matrix) throws RemoteException {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }

    @Override
    public double determinant(double[][] matrix) throws RemoteException {
        // Validate square matrix
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("Determinant can only be calculated for square matrices");
        }

        return calculateDeterminant(matrix);
    }

    // Recursive Laplace expansion for determinant calculation
    private double calculateDeterminant(double[][] matrix) {
        int size = matrix.length;

        // Base cases
        if (size == 1) return matrix[0][0];
        if (size == 2) return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];

        double det = 0;
        for (int j = 0; j < size; j++) {
            det += Math.pow(-1, j) * matrix[0][j] * 
                calculateDeterminant(getSubMatrix(matrix, 0, j));
        }
        return det;
    }

    // Helper method to get submatrix for determinant calculation
    private double[][] getSubMatrix(double[][] matrix, int excludeRow, int excludeCol) {
        int size = matrix.length;
        double[][] subMatrix = new double[size - 1][size - 1];
        int r = 0, c = 0;

        for (int i = 0; i < size; i++) {
            if (i == excludeRow) continue;
            c = 0;
            for (int j = 0; j < size; j++) {
                if (j == excludeCol) continue;
                subMatrix[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return subMatrix;
    }
}