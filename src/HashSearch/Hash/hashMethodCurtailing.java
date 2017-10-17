package HashSearch.Hash;

public class hashMethodCurtailing implements Hash{
    public int getHash(int value, int countAddress) {
        int lengthAddress = (int) Math.log10(countAddress - 1) + 1; // порядок числа адресов
        int result = 0;
        while (value != 0){
            result += value % Math.pow(10, lengthAddress);   // Добаляем часть числа в хеш
            value /= Math.pow(10, lengthAddress);            // Убираем испольованную часть числа
            /*if (Integer.toString(result).length() > lengthAddress){     // убираем старший разряд из хеша
                result /= 10;
            }*/

        }
        result %= Math.pow(10, lengthAddress);
        result *= (double)(countAddress - 1) / Math.pow(10, lengthAddress); // приводим хеш к количеству адресов
        return result;
    }
}
