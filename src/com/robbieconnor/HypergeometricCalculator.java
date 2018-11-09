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

    //This code is the hypergeometric calculator's calculation.
    public static double calculate(int totalNumberOfObjects, int numberOfSuccesses, int sampleSize, int successesNeeded) {

        //Processed value to be returned at the end of the calculation.
        double probability;
        //Unprocessed value received at the end of the calculation.
        double endResult;

        //These BigDecimals are named after the symbols in the formula for the hypergeometric calculator.
        BigDecimal kCx;
        BigDecimal NkCnx;
        BigDecimal NCn;

        kCx = nCr(numberOfSuccesses, successesNeeded);
        int kCxConverted = Integer.parseInt(kCx.toString());

        NkCnx = nCr((totalNumberOfObjects - numberOfSuccesses), (sampleSize - successesNeeded));
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
