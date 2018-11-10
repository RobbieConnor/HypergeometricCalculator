/*
    MIT License

    Copyright (c) 2018 Robbie Connor

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/

package com.robbieconnor;

import java.math.BigDecimal;

public class HypergeometricCalculator {

    /**
     * Calculate the hypergeometric probability based on the parameters passed in
     * @param totalNumberOfObjects Total number of objects in the population
     * @param numberOfSuccessesPossible Number of successes possible in the population
     * @param sampleSize Size of the sample
     * @param successesNeeded Number of successes needed in the sample
     * @return The hypergeometric probability of attaining that amount of successes in a sample
     * @throws ArithmeticException If number of successes needed is greater than total number of successes in population
     * @throws ArithmeticException If number of successes possible is greater than the total number of objects
     * @throws IllegalArgumentException If an input parameter is less than or equal to 0
     */
    public static double calculate(int totalNumberOfObjects, int numberOfSuccessesPossible, int sampleSize, int successesNeeded) throws ArithmeticException, IllegalArgumentException {

        if (totalNumberOfObjects <= 0 || numberOfSuccessesPossible <= 0 || sampleSize <= 0 || successesNeeded <= 0) {
            throw new IllegalArgumentException("Negative number in calculation");
        } else if(successesNeeded > numberOfSuccessesPossible) {
            throw new ArithmeticException("Number of successes needed must be less than or equal to the number of successes in the population");
        } else if (totalNumberOfObjects < numberOfSuccessesPossible) {
            throw new ArithmeticException("Number of successes possible must be greater than or equal to total number of objects");
        }


        //Processed value to be returned at the end of the calculation.
        double probability;
        //Unprocessed value received at the end of the calculation.
        double endResult;

        //These BigDecimals are named after the symbols in the formula for the hypergeometric calculator.
        BigDecimal kCx;
        BigDecimal NkCnx;
        BigDecimal NCn;

        kCx = nCr(numberOfSuccessesPossible, successesNeeded);
        int kCxConverted = Integer.parseInt(kCx.toString());

        NkCnx = nCr((totalNumberOfObjects - numberOfSuccessesPossible), (sampleSize - successesNeeded));
        int NkCnxConverted = Integer.parseInt(NkCnx.toString());

        NCn = nCr(totalNumberOfObjects, sampleSize);
        int NCnConverted = Integer.parseInt(NCn.toString());

        endResult = (((double) kCxConverted * NkCnxConverted) / NCnConverted);

        //Returns endResult to 6 decimals places
        probability = Double.parseDouble(String.format("%.6f", endResult));
        return probability;
    }

    private static BigDecimal factorial(int num) {
        if (num <= 1) {
            return BigDecimal.ONE;
        } else {
            return factorial(num - 1).multiply(BigDecimal.valueOf(num));
        }
    }

    private static BigDecimal nCr(int n, int r) {
        return (factorial(n).divide((factorial(n - r).multiply(factorial(r)))));
    }
}
