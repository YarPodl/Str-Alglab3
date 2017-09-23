package HashSearch.Hash;

public class HashMethodDividing implements Hash{
    public int getHash(long value, int countAddress) {
        return (int) ((value % divisor) * ((double)countAddress / divisor));
    }
}
