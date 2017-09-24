package HashSearch.Hash;

public class hashMethodDividing implements Hash{
    public int getHash(int value, int countAddress) {
        return (int) ((value % divisor) * ((double)countAddress / divisor));
    }
}
