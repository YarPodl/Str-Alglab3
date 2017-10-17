package HashSearch.Hash;

public class hashMethodMultipl implements Hash{
    static double multiplier = 0.6180339887;
    public int getHash(int value, int countAddress) {
        double i = value * multiplier;
        i = i - (int) i;    // Дробная часть числа
        return (int) (countAddress * i);
    }
}
