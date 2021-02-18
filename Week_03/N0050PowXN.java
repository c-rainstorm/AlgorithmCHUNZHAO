package s0000;

/**
 * @author traceless
 */
public class N0050PowXN {
    public double myPow(double x, int n) {
        return myPow(x, n);
    }

    private double myPow(double x, long n) {
        if (n == 0)
            return 1.0;
        if (n < 0)
            return 1 / myPow(x, -n);
        if ((n & 1) == 1)
            return x * myPow(x, n - 1);
        return myPow(x * x, n / 2);
    }

    public double myPow2(double x, int n) {
        long p = n;
        if (p < 0) {
            x = 1 / x;
            p = -p;
        }
        double result = 1.0;
        while (p > 0) {
            if ((p & 1) == 1) {
                result *= x;
            }
            x *= x;
            p >>= 1;
        }
        return result;
    }
}
