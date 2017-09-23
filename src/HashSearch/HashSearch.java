package HashSearch;


import java.util.ArrayList;
import java.util.Random;


public class HashSearch {


    private final int maxNumber = 65000;
    private long listKey[];



    long hashListOpenAddress[];
    ArrayList<Long>[] hashListChain;




    public void newListKey(int count){
        listKey = new long[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            listKey[i] = random.nextInt(maxNumber);
        }
    }

    private void cleanHashLists(){
        for (long i: hashListOpenAddress) {
            i = Long.MAX_VALUE;
        }

        for (ArrayList<Long> i: hashListChain) {
            i = new ArrayList<Long>();
        }
    }
    HashSearch(int count){
        hashListOpenAddress = new long[count];
        hashListChain = new ArrayList[count];
        cleanHashLists();
        newListKey(count);
        /*
        hashListMethodDividing = new long[count];
        hashListMethodMidSquares = new long[count];
        hashListMethodCurtailing = new long[count];
        hashListMethodMultipl = new long[count];

        hashArrayListMethodDividing = new ArrayList<Long>[];
        hashArrayListMethodMidSquares[];
        hashArrayListMethodCurtailing[];
        hashArrayListMethodMultipl[];

        infill();
        */
    }



    // Добавляет одно хэш значение в массив методом открытой адресации

    private void addMethodOpenAddress(long value, int index) {
        if (listKey[index] != Long.MIN_VALUE){
            int j = index + 1;
            while (listKey[index] != Long.MIN_VALUE){
                j++;
                if (j == listKey.length) {j = 0;}
            }
        }
        listKey[index] = value;
    }


    public int getCountCollision(){
        int count = 0;
        for (int i = 0; i < hashListChain.length; i++) {
            if (hashListChain[i].size() > 1){
                count += hashListChain[i].size() - 1;
            }
        }
        return count;
    }

    // Заполняет хэш значениями массив методом цепочек

    private void addChain(int count){
        for (int i = 0; i < listKey.length; i++) {
            hashListChain[Hash.methodDividing(listKey[i], count)].add(listKey[i]);
        }
    }



    // Заполняет хэш значениями массивы методом открытой адресации

    private void infillOpenAddress(){
        int hashMethodDividing;
        int hashMethodMidSquares;
        int hashMethodCurtailing;
        int hashMethodMethodMultipl;
        for (int i = 0; i < listKey.length; i++) {
            /*
            hashMethodDividing = Hash.methodDividing(list[i], list.length, divisor);
            hashMethodMidSquares = Hash.methodMidSquares(list[i], list.length);
            hashMethodCurtailing = Hash.methodCurtailing(list[i], list.length);
            hashMethodMethodMultipl = Hash.methodMultipl(list[i], list.length, multiplier);

            addOpenAddress(hashListMethodDividing, hashMethodDividing, list[i]);
            addOpenAddress(hashListMethodMidSquares, hashMethodMidSquares, list[i]);
            addOpenAddress(hashListMethodCurtailing, hashMethodCurtailing, list[i]);
            addOpenAddress(hashListMethodMultipl, hashMethodMethodMultipl, list[i]);
            */
        }
    }


    /*
    long hashListMethodDividing[];
    long hashListMethodMidSquares[];
    long hashListMethodCurtailing[];
    long hashListMethodMultipl[];

    ArrayList<Long> hashArrayListMethodDividing[]; // Тут изменить!!
    ArrayList<Long> hashArrayListMethodMidSquares[];
    ArrayList<Long> hashArrayListMethodCurtailing[];
    ArrayList<Long> hashArrayListMethodMultipl[];
    */

    /*
    private void hashOpenAddressMethodDividing(){

    }
    private void hashOpenAddressMethodMidSquares(){

    }
    private void hashOpenAddressMethodCurtailing(){

    }
    private void hashOpenAddressMethodMultipl(){

    }

    */
}
