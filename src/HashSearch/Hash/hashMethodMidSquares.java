package HashSearch.Hash;

public class hashMethodMidSquares implements Hash{
    public int getHash(int value, int countAddress) {
        long valueSqr = (long)value*value;/*


        int lengthAddress = Integer.toString(countAddress-1).length(); // порядок числа адресов

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
        }*/

        int lengthAddress = (int) Math.log10(countAddress - 1) + 1;
        int lengthValueSqr = (int) Math.log10(valueSqr) + 1;
        int i = (lengthValueSqr - lengthAddress)/2;
        if(i % 2 == 1)
        if (i > 0){
            valueSqr %= Math.pow(10, lengthValueSqr - i);
            valueSqr /= Math.pow(10, i);
        }
        return (int) (valueSqr * ((double)countAddress /  Math.pow(10, lengthAddress))); // приводим хеш к количеству адресов
    }
}
