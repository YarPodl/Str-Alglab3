package HashSearch;


import HashSearch.Hash.Hash;
import HashSearch.Hash.hashMethodCurtailing;
import HashSearch.Hash.hashMethodDividing;
import HashSearch.Hash.hashMethodMidSquares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class HashSearch {

    private final int countForEfficiency = 500;
    private final int maxNumber = 65000;
    private long listKey[];



    private long hashListOpenAddress[];
    private ArrayList<Long>[] hashListChain;




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
            i = new ArrayList<>();
        }
    }
    HashSearch(int count){
        hashListOpenAddress = new long[count];
        hashListChain = new ArrayList[count];
        cleanHashLists();
        newListKey(count);
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

    private void addMethodChain(long value, int index) {
        hashListChain[index].add(value);
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

    private void infillChain(Hash hash){
        for (int i = 0; i < listKey.length; i++) {
            addMethodChain(listKey[i], hash.getHash(listKey[i], listKey.length));
        }
    }



    // Заполняет хэш значениями массивы методом открытой адресации

    private void infillOpenAddress(Hash hash){

        for (int i = 0; i < listKey.length; i++) {
            addMethodOpenAddress(listKey[i], hash.getHash(listKey[i], listKey.length));

        }
    }

    private int getEffic


    // Возвращает массив с счетчиками эффективности хэш функций

    public int[] efficiency(){
        int effic[] = new int[4];
        for (int i = 0; i < countForEfficiency; i++) {

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
            infillChain(new hashMethodDividing());
            countCollisions[3] = getCountCollision();

            int minValue = Integer.MIN_VALUE;
            for (int j: countCollisions) {
                if (j < minValue){

                }
            }
        }
    }
}
