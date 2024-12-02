import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MatrixOperations extends Remote {
    /**
     * Performs matrix addition
     * @param matrixA First input matrix
     * @param matrixB Second input matrix
     * @return Result of matrix addition
     * @throws RemoteException If remote communication fails
     * @throws IllegalArgumentException If matrices have incompatible dimensions
     */
    double[][] add(double[][] matrixA, double[][] matrixB) 
        throws RemoteException, IllegalArgumentException;

    /**
     * Performs matrix multiplication
     * @param matrixA First input matrix
     * @param matrixB Second input matrix
     * @return Result of matrix multiplication
     * @throws RemoteException If remote communication fails
     * @throws IllegalArgumentException If matrices have incompatible dimensions
     */
    double[][] multiply(double[][] matrixA, double[][] matrixB) 
        throws RemoteException, IllegalArgumentException;

    /**
     * Computes matrix transposition
     * @param matrix Input matrix
     * @return Transposed matrix
     * @throws RemoteException If remote communication fails
     */
    double[][] transpose(double[][] matrix) 
        throws RemoteException;

    /**
     * Calculates matrix determinant
     * @param matrix Input square matrix
     * @return Determinant of the matrix
     * @throws RemoteException If remote communication fails
     * @throws IllegalArgumentException If matrix is not square
     */
    double determinant(double[][] matrix) 
        throws RemoteException, IllegalArgumentException;
}
