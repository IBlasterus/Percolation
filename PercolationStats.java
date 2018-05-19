import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Monte Carlo simulation with percolation model
 *
 * @author Oleksandr Chornyi (iblasterus@gmail.com)
 */
public class PercolationStats {
    /**
     * Sample mean of percolation threshold
     */
    private double mean;

    /**
     * Sample standard deviation of percolation threshold
     */
    private double stddev;

    /**
     * Low  endpoint of 95% confidence interval
     */
    private double confidenceLo;

    /**
     * high endpoint of 95% confidence interval
     */
    private double confidenceHi;

    /**
     * Perform trials independent experiments on an n-by-n grid
     *
     * @param n      grid size
     * @param trials number of trials
     */
    public PercolationStats(int n, int trials) {
        if (n <= 0) throw new IllegalArgumentException("n too small");
        if (trials <= 0) throw new IllegalArgumentException("trails too small");
        double[] open = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            do {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            } while (!p.percolates());
            open[i] = 1.0 * p.numberOfOpenSites() / (n * n);
        }
        mean = StdStats.mean(open);
        stddev = StdStats.stddev(open);
        confidenceLo = mean - (1.96 * stddev / Math.sqrt(trials));
        confidenceHi = mean + (1.96 * stddev / Math.sqrt(trials));
    }

    /**
     * @return sample mean of percolation threshold
     */
    public double mean() {
        return mean;
    }

    /**
     * @return sample standard deviation of percolation threshold
     */
    public double stddev() {
        return stddev;
    }

    /**
     * @return low  endpoint of 95% confidence interval
     */
    public double confidenceLo() {
        return confidenceLo;
    }

    /**
     * @return high endpoint of 95% confidence interval
     */
    public double confidenceHi() {
        return confidenceHi;
    }

    /**
     * Test client
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        System.out.println("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]");
    }
}
