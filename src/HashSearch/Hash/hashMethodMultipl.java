package HashSearch.Hash;

public class hashMethodMultipl implements Hash{
    public int getHash(long value, int countAddress) {
        double i = value * multiplier;
        i = i - (int) i;    // Дробная часть числа
        return (int) (countAddress * i);
    }
}
