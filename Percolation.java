import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * A model of percolation system
 * @author Oleksandr Chornyi (iblasterus@gmail.com)
 */
public class Percolation {

    /**
     * Grid
     * row, column
     */
    private int[][] grid;

    private WeightedQuickUnionUF uf;

    /**
     * Create n-by-n grid, with all sites blocked
     * @param n grid size
     */
    public Percolation(int n) {
        int vTop = n * n; //virtual-top index of node
        int vBottom = n * n + 1; //virtual-bottom index of node
        grid = new int[n][n];
        uf = new WeightedQuickUnionUF(n * n + 2); //+2 - virtual-top + virtual-bottom nodes
        //Create virtual-top and virtual-bottom
        for (int i = 0; i < n; i++) {
            uf.union(vTop, i); //Virtual-top
            uf.union(vBottom, n * n - (i + 1)); //Virtual-bottom
        }
    }

    /**
     * Open site (row, col) if it is not open already
     * WeightedQuickUnionUF.union
     * @param row row
     * @param col column
     */
    public void open(int row, int col) {

    }

    /**
     * Is site (row, col) open?
     * @param row row
     * @param col column
     * @return is open?
     */
    public boolean isOpen(int row, int col) {
        return grid[row + 1][col + 1] == 1;
    }

    /**
     * Is site (row, col) full?
     * WeightedQuickUnionUF.connected
     * @param row row
     * @param col column
     * @return is full?
     */
    public boolean isFull(int row, int col) {
        return false;
    }

    /**
     * Number of open sites
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return 0;
    }

    /**
     * Does the system percolate?
     * WeightedQuickUnionUF.connected
     * @return does the system percolate?
     */
    public boolean percolates() {
        return false;
    }

    /**
     * Get UF-index of cell
     * @param row row
     * @param col column
     * @return UF index
     */
    private int getIndex(int row, int col) {
        return (row - 1) * grid.length + (col - 1);
    }

    /**
     * Test client
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
