package HashSearch.Hash;

public class hashMethodCurtailing implements Hash{
    public int getHash(int value, int countAddress) {
        int lengthAddress = Integer.toString(countAddress - 1).length(); // порядок числа адресов
        int lengthValue = Long.toString(value).length();
        int result = 0;
        while (value != 0){
            result += value % Math.pow(10, lengthAddress);
            value /= Math.pow(10, lengthAddress);
            if (Integer.toString(result).length() > lengthAddress){
                result /= 10;
            }

        }
        result *= (double)(countAddress - 1) / Math.pow(10, lengthAddress);
        return result;
    }
}
