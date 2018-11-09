# The Hypergeometric Calculator
### What is this?
This is a Java library for calculating Hypergeometric Distributions using the following formula:
h(x; N, n, k) = [ kCx ] [ N-kCn-x ] / [ NCn ]
N: The number of items in the population.
k: The number of items in the population that are classified as successes.
n: The number of items in the sample.
x: The number of items in the sample that are classified as successes.
kCx: The number of combinations of k things, taken x at a time.

The return value is a double to 6 decimal places.
### Why is this?
When creating private projects, I felt the need to use a Hypergeometric Calculator when creating an app for finding the probability of finding a certain card in your opening hand in a card game. I tried to find a library or general calculation to use but to no avail. So I created one myself and decided to make it open source. Enjoy!
### How do I use this?
After importing HypergeometricCalculator, use the following code to receive this probablility:
`HypergeometricCalculator.calculate(int totalNumberOfObjects, int numberOfSuccesses, int sampleSize, int successesNeeded)`
For example:
`HypergeometricCalculator.calculate(40, 3, 5, 1)` will return 0.301113.

### License
See the [LICENSE](LICENSE.md) file for license rights and limitations (MIT).
