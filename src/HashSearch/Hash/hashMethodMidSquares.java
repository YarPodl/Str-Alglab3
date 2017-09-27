package HashSearch.Hash;

public class hashMethodMidSquares implements Hash{
    public int getHash(int value, int countAddress) {
        int lengthAddress = Integer.toString(countAddress-1).length(); // порядок числа адресов
        long valueSqr = (long)value*value;
        int lengthValueSqr = Long.toString(valueSqr).length();
        while (lengthValueSqr > lengthAddress) {
            if ((lengthValueSqr - lengthAddress) % 2 == 0){
                valueSqr = valueSqr / 10;
                valueSqr =  (long)(valueSqr % Math.pow(10, lengthValueSqr - 2));
                lengthValueSqr -= 2;
            }
            if ((lengthValueSqr - lengthAddress) % 2 == 1){
                valueSqr = valueSqr / 10;
                lengthValueSqr -= 1;
            }
        }
        return (int) (valueSqr * ((double)countAddress /  Math.pow(10, lengthAddress))); // приводим хеш к количеству адресов
    }
}
