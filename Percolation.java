import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * A model of percolation system
 *
 * @author Oleksandr Chornyi (iblasterus@gmail.com)
 */
public class Percolation {

    /**
     * Grid
     * row, column
     */
    private boolean[][] grid;

    /**
     * Union-Find object
     */
    private final WeightedQuickUnionUF uf;

    /**
     * Number of open sides
     */
    private int open;

    /**
     * Create n-by-n grid, with all sites blocked
     *
     * @param n grid size
     */
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n too small");
        int vTop = n * n; // virtual-top index of node
        int vBottom = n * n + 1; // virtual-bottom index of node
        grid = new boolean[n][n];
        open = 0;
        uf = new WeightedQuickUnionUF(n * n + 2); // +2 - virtual-top + virtual-bottom nodes
        // Create virtual-top and virtual-bottom
        for (int i = 0; i < n; i++) {
            uf.union(vTop, i); // Virtual-top
            uf.union(vBottom, n * n - (i + 1)); // Virtual-bottom
        }
    }

    /**
     * Open site (row, col) if it is not open already
     * WeightedQuickUnionUF.union
     *
     * @param row row
     * @param col column
     */
    public void open(int row, int col) {
        checkRowCol(row, col);
        if (isOpen(row, col)) return;
        check(row, col, "up");
        check(row, col, "down");
        check(row, col, "left");
        check(row, col, "right");
        grid[row - 1][col - 1] = true;
        open++;
    }

    /**
     * Is site (row, col) open?
     *
     * @param row row
     * @param col column
     * @return is open?
     */
    public boolean isOpen(int row, int col) {
        checkRowCol(row, col);
        return grid[row - 1][col - 1];
    }

    /**
     * Is site (row, col) full?
     * WeightedQuickUnionUF.connected
     *
     * @param row row
     * @param col column
     * @return is full?
     */
    public boolean isFull(int row, int col) {
        checkRowCol(row, col);
        if (!isOpen(row, col)) return false;
        int n = grid.length;
        int vTop = n * n; // virtual-top index of node
        int indexUf = getIndex(row, col);
        return uf.connected(indexUf, vTop);
    }

    /**
     * Number of open sites
     *
     * @return number of open sites
     */
    public int numberOfOpenSites() {
        return open;
    }

    /**
     * Does the system percolate?
     * WeightedQuickUnionUF.connected
     *
     * @return does the system percolate?
     */
    public boolean percolates() {
        int n = grid.length;
        int vTop = n * n; // virtual-top index of node
        int vBottom = n * n + 1; // virtual-bottom index of node
        return uf.connected(vTop, vBottom);
    }

    /**
     * Get UF-index of cell
     *
     * @param row row
     * @param col column
     * @return UF index
     */
    private int getIndex(int row, int col) {
        return (row - 1) * grid.length + (col - 1);
    }

    /**
     * Check nearby cell and make uf-connect with it
     *
     * @param row  row
     * @param col  column
     * @param side up/down/left/right
     */
    private void check(int row, int col, String side) {
        int n = grid.length;
        int curIndex = getIndex(row, col);
        switch (side) {
            case "up":
                if (row - 1 <= 0) {
                    return;
                } else {
                    row--;
                }
                break;
            case "down":
                if (row + 1 > n) {
                    return;
                } else {
                    row++;
                }
                break;
            case "left":
                if (col - 1 <= 0) {
                    return;
                } else {
                    col--;
                }
                break;
            case "right":
                if (col + 1 > n) {
                    return;
                } else {
                    col++;
                }
        }
        int cellIndex = getIndex(row, col);
        if (isOpen(row, col)) {
            if (!uf.connected(curIndex, cellIndex)) {
                uf.union(curIndex, cellIndex);
            }
        }
    }

    /**
     * Check range of rows and columns
     *
     * @param row row
     * @param col column
     */
    private void checkRowCol(int row, int col) {
        int n = grid.length;
        if (row <= 0 || row > n) throw new IndexOutOfBoundsException("row out of bounds");
        if (col <= 0 || col > n) throw new IndexOutOfBoundsException("col out of bounds");
    }

    /**
     * Test client
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        System.out.println("Test");
    }
}
