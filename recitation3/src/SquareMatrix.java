import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a square 2D array
 *
 */
public class SquareMatrix<T> implements Iterable<T> {
    public T[][] matrix;

    public SquareMatrix(int n){
        matrix = (T[][]) new Object[n][n];
    }

    /**
     * Gets an element from the matrix (indexed by 0)
     * at a given row and column
     * @param i row
     * @param j column
     * @return item at that index
     */
    public T get(int i, int j){
        return matrix[i][j];
    }

    /**
     * Sets an element in the matrix (indexed by 0)
     * at a given row and column
     * @param i
     * @param j
     * @param data
     */
    public void set(int i, int j, T data){
        matrix[i][j] = data;
    }

    /**
     * Returns an iterator that traverses the matrix row by row
     * @return
     */
    public Iterator<T> iterator(){
        return new SquareMatrixIterator();
    }

    private class SquareMatrixIterator implements Iterator<T> {

        //Do not add any instance variables
        private int row ;
        private int column;

        @Override
        public boolean hasNext() {
            if (matrix.length == 0) return false;
            return (column < matrix[0].length);

//            if (matrix.length == 0) return false;
//            return (!(column == (matrix[0].length - 1) && row == (matrix.length - 1)));

        }


        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T retValue =  matrix[row][column];

            row++;
            if (row == matrix.length) {
                column++;
                row = 0;
            }

            return retValue;




//            if (!hasNext()) {
//                throw new NoSuchElementException();
//            }
//
//
//            row++;
//            if(row == matrix.length) {
//                row = 0;
//                column ++;
//            }
//
//            return matrix[row][column];





        }


    }
}
