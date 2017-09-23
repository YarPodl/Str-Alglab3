package HashSearch.Hash;

public class HashMethodDividing extends Hash{
    int getHash(long value, int countAddress) {
        return (int) ((value % divisor) * ((double)countAddress / divisor));
    }
}
