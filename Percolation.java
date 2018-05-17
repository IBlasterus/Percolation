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

    /**
     * Union-Find object
     */
    private WeightedQuickUnionUF uf;

    /**
     * Number of open sides
     */
    private int open;

    /**
     * Create n-by-n grid, with all sites blocked
     * @param n grid size
     */
    public Percolation(int n) {
        int vTop = n * n; //virtual-top index of node
        int vBottom = n * n + 1; //virtual-bottom index of node
        grid = new int[n][n];
        open = 0;
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
        if (isOpen(row, col)) return;
        int curIndex = getIndex(row, col);
        int n = grid.length;
        //Check up
        if (row - 1 > 0) {
            if (isOpen(row - 1, col)) {
                int upIndex = getIndex(row - 1, col);
                if (!uf.connected(curIndex, upIndex)) {
                    uf.union(curIndex, upIndex);
                }
            }
        }
        //Check down
        if (row + 1 <= n) {

        }
        //Check left
        //Check right
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
        int n = grid.length;
        int vTop = n * n; //virtual-top index of node
        int indexUf = getIndex(row, col);
        return uf.connected(indexUf, vTop);
    }

    /**
     * Number of open sites
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return open;
    }

    /**
     * Does the system percolate?
     * WeightedQuickUnionUF.connected
     * @return does the system percolate?
     */
    public boolean percolates() {
        int n = grid.length;
        int vTop = n * n; //virtual-top index of node
        int vBottom = n * n + 1; //virtual-bottom index of node
        return uf.connected(vTop, vBottom);
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
