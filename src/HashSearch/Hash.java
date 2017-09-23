package HashSearch;



public class Hash {

    private static final double multiplier = 0.6180339887;
    private static final int divisor = 11817;


    static int methodDividing(long value, int countAddress){
        return (int) ((value % divisor) * ((double)countAddress / divisor));
    }

    static int methofMidSquares(long value, int countAddress){
        int lengthAddress = Integer.toString(countAddress).length(); // порядок числа адресов
        long valueSqr = value*value;
        int lengthValueSqr = Long.toString(valueSqr).length();
        while (lengthValueSqr > lengthAddress) {
            if ((lengthValueSqr - lengthAddress) % 2 == 0){
                valueSqr = valueSqr / 10;
                valueSqr =  (long)(valueSqr % Math.pow(10, lengthValueSqr - 1));
                lengthValueSqr -= 2;
            }
            if ((lengthValueSqr - lengthAddress) % 2 == 1){
                valueSqr = valueSqr / 10;
                lengthValueSqr -= 1;
            }
        }
        return (int) (valueSqr * ((double)countAddress /  Math.pow(10, lengthAddress)));
    }

    static int methodCurtailing(long value, int countAddress){
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

    static  int methodMultipl (long value, int countAddress){
        double i = value * multiplier;
        i = i - (int) i;    // Дробная часть числа
        return (int) (countAddress * i);
    }
}
