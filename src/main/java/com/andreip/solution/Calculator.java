package com.andreip.solution;

/**
 * Calculates total sum in the thread save way.
 * Calculator class represents the singleton pattern.
 */
public class Calculator {

    // single instance of the class
    private static Calculator instance;
    // Holds current sum of all numbers
    private int sum = 0;
    // Holds previously summed number
    private int prevSum = 0;
    // Shows that currently summing in the progress
    private boolean inProgress = false;

    private Calculator() {
    }

    /**
     * Returns the single instance of the class
     * @return  Calculator instance
     */
    public static Calculator getInstance() {
        // For performance optimization do not threads synchronization done
        // only once when instance is not yet exists
        if (instance == null) {
            synchronized (Calculator.class) {
                if (instance == null) {
                    instance = new Calculator();
                }
            }
        }
        return instance;
    }

    /**
     * Return sum of all numbers
     * @return sum of numbers
     */
    public synchronized int getSum() {
        return prevSum;
    }

    /**
     * Returns true if currently summing is in progress
     * @return  boolean
     */
    public synchronized boolean isInProgress() {
        return inProgress;
    }

    /**
     * Stop the progress and reset current sum value to 0. Also return sum.
     * @return sum of all numbers
     */
    public synchronized int stopAndGetSum() {
        inProgress = false;
        prevSum = sum;
        sum = 0;
        return prevSum;
    }

    /**
     * Start the summing process and if not e started and adds num to sum.
     * @param num   number to add to the sum
     */
    public synchronized void startAndAddToSum(int num) {
        inProgress = true;
        sum+=num;
    }
}
