package section6theconcurrencychallengesandsolutions;

public class MinMaxMetricsTest {

    private volatile long min = 0;
    private volatile long max = 0;

    public MinMaxMetricsTest() {
        this.min = Long.MIN_VALUE;
        this.max = Long.MAX_VALUE;

    }

    /**
     * Adds a new sample to our metrics.
     */
    public void addSample(long newSample) {
        synchronized (this) {
            this.min = Math.min(newSample, this.min);
            this.max = Math.max(newSample, this.min);
        }
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return this.min;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return this.max;
    }
}
