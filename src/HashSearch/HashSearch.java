package HashSearch;


import HashSearch.Hash.Hash;
import HashSearch.Hash.hashMethodCurtailing;
import HashSearch.Hash.hashMethodDividing;
import HashSearch.Hash.hashMethodMidSquares;
import HashSearch.Hash.hashMethodMultipl;

import java.util.ArrayList;
import java.util.Random;


public class HashSearch {


    private Hash bestHashFunc = new hashMethodMidSquares();
    private int maxNumber = 65000;
    private long listKey[];
    private long time;


    private long hashListOpenAddress[];
    private ArrayList<Long>[] hashListChain;




    public HashSearch(int count){
        time = 0;
        listKey = new long[count];
        hashListOpenAddress = new long[count];
        hashListChain = new ArrayList[count];
        cleanHashLists();
        newListKey(count);
    }


    public long getTime() {
        return time;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }




    public void hashing(){
        infillOpenAddress(bestHashFunc);
        infillChain(bestHashFunc);
    }


    // формирует новый массив ключей

    public void newListKey(int count){
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            listKey[i] = random.nextInt(maxNumber);
        }
    }

    // Очищает хэш массивы

    public void cleanHashLists(){
        for (int i = 0; i < hashListOpenAddress.length; i++) {
            hashListOpenAddress[i] = Long.MIN_VALUE;
        }
        for (int i = 0; i < hashListChain.length; i++) {
            hashListChain[i] = new ArrayList<>();
        }
    }




    // Добавляет одно хэш значение в массив методом открытой адресации

    private void addMethodOpenAddress(long value, int index) {
        while (hashListOpenAddress[index] != Long.MIN_VALUE){
            index++;
            if (index == hashListOpenAddress.length) {index = 0;}
        }
        hashListOpenAddress[index] = value;
    }


    // Добавляет одно хэш значение в массив методом цепочек

    private void addMethodChain(long value, int index) {
        hashListChain[index].add(value);
    }


    // Возвращает количество коллизий

    private int getCountCollision(){
        int count = 0;
        for (ArrayList i : hashListChain) {
            if (i.size() > 1){
                count += i.size() - 1;
            }
        }
        return count;
    }

    // Заполняет хэш значениями массив методом цепочек

    private void infillChain(Hash hash){
        for (long i : listKey) {
            addMethodChain(i, hash.getHash(i, listKey.length));
        }
    }



    // Заполняет хэш значениями массивы методом открытой адресации

    private void infillOpenAddress(Hash hash){

        for (long i : listKey) {
            addMethodOpenAddress(i, hash.getHash(i, listKey.length));

        }
    }



    // Возвращает массив с счетчиками эффективности хэш функций

    public int[] analisysEfficiency(int countForEfficiency){
        int efficiency[] = new int[4];
        for (int i = 0; i < countForEfficiency; i++) {

            newListKey(listKey.length);
            int countCollisions[] = new  int[4];


            cleanHashLists();
            infillChain(new hashMethodDividing());
            countCollisions[0] = getCountCollision();


            cleanHashLists();
            infillChain(new hashMethodCurtailing());
            countCollisions[1] = getCountCollision();


            cleanHashLists();
            infillChain(new hashMethodMidSquares());
            countCollisions[2] = getCountCollision();



            cleanHashLists();
            infillChain(new hashMethodMultipl());
            countCollisions[3] = getCountCollision();

            int indexMinValue = 0;
            for (int j = 1; j < countCollisions.length; j++) {
                if (countCollisions[j] < countCollisions[indexMinValue]){
                    indexMinValue = j;
                }
            }

            efficiency[indexMinValue]++;
        }
        int indexMax = 0;
        for (int j = 1; j < efficiency.length; j++) {
            if (efficiency[j] > efficiency[indexMax]){
                indexMax = j;
            }
        }
        switch (indexMax){
            case 0:
                bestHashFunc = new hashMethodDividing();
                break;
            case 1:
                bestHashFunc = new hashMethodCurtailing();
                break;
            case 2:
                bestHashFunc = new hashMethodMidSquares();
                break;
            case 3:
                bestHashFunc = new hashMethodMultipl();
                break;
        }
        hashing();
        return efficiency;
    }

    public boolean searchOpenAddress(long key){
        time = System.nanoTime();
        int index = bestHashFunc.getHash(key, listKey.length);
        if (hashListOpenAddress[index] == Integer.MIN_VALUE){
            time = System.nanoTime() - time;
            return false;
        }
        int barrier = index;
        while (index != hashListOpenAddress.length) {
            if (hashListOpenAddress[index] == key) {
                time = System.nanoTime() - time;
                return true;
            }
            index++;
        }
        index = 0;
        while (index != barrier) {
            if (hashListOpenAddress[index] == key) {
                time = System.nanoTime() - time;
                return true;
            }
            index++;
        }
        time = System.nanoTime() - time;
        return false;
    }

    public boolean searchChain(long key){
        time = System.nanoTime();
        int index = bestHashFunc.getHash(key, listKey.length);
        if(hashListChain[index].contains(key)){
            time = System.nanoTime() - time;
            return true;
        }
        time = System.nanoTime() - time;
        return false;

    }
}
