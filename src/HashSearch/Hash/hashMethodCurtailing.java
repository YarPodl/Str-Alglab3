package HashSearch.Hash;

public class hashMethodCurtailing implements Hash{
    public int getHash(long value, int countAddress) {
        int lengthAddress = Integer.toString(countAddress).length(); // порядок числа адресов
        int lengthValueSqr = Long.toString(value).length();
        int result = 0;
        while (value != 0){
            result += value % Math.pow(10, lengthValueSqr + 1);
            value /= Math.pow(10, lengthValueSqr);
            if (Integer.toString(result).length() > lengthAddress){
                result /= 10;
            }
        }
        return result;
    }
}
