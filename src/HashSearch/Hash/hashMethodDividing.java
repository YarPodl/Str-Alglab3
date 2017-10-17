package HashSearch.Hash;

public class hashMethodDividing implements Hash{
    public static void setDivisor(int divisor) {
        hashMethodDividing.divisor = divisor;
    }

    static int divisor = 1001;
    public int getHash(int value, int countAddress) {
        return (int) ((value % divisor) * ((double)countAddress / divisor));
    }
}
