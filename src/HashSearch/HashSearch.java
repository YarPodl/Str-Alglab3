package HashSearch;


import HashSearch.Hash.Hash;
import HashSearch.Hash.hashMethodCurtailing;
import HashSearch.Hash.hashMethodDividing;
import HashSearch.Hash.hashMethodMidSquares;
import HashSearch.Hash.hashMethodMultipl;

import java.util.ArrayList;
import java.util.Random;


public class HashSearch {


    private Hash bestHashFunc = new hashMethodMultipl();
    private int maxNumber = 65000;
    private int[] listKey;
    private long time;
    private int countCompare;
    private int hashListOpenAddress[];
    private ArrayList<Integer>[] hashListChain;




    public HashSearch(int count){
        time = 0;
        listKey = new int[count];
        hashListOpenAddress = new int[count];
        hashListChain = new ArrayList[count];
        cleanHashLists();
        newListKey(count);
    }


    public int getCountCompare() {
        return countCompare;
    }


    public long getTime() {
        return time;
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
        listKey = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            listKey[i] = random.nextInt(maxNumber);
        }
    }

    // Очищает хэш массивы

    public void cleanHashLists(){
        hashListOpenAddress = new int[listKey.length];
        hashListChain = new ArrayList[listKey.length];

        for (int i = 0; i < hashListOpenAddress.length; i++) {
            hashListOpenAddress[i] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < hashListChain.length; i++) {
            hashListChain[i] = new ArrayList<>();
        }
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
        for (int i : listKey) {
            if (hash.getHash(i, listKey.length)>1000){
                System.out.println();
            }
            hashListChain[hash.getHash(i, listKey.length)].add(i);
        }
    }



    // Заполняет хэш значениями массивы методом открытой адресации

    private void infillOpenAddress(Hash hash){

        for (int i : listKey) {

            int index = hash.getHash(i, hashListOpenAddress.length);
            while (hashListOpenAddress[index] != Integer.MIN_VALUE){
                index++;
                if (index == hashListOpenAddress.length) {index = 0;}
            }
            hashListOpenAddress[index] = i;
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
        cleanHashLists();
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

    public boolean searchOpenAddress(int key){
        time = System.nanoTime();
        countCompare = 0;
        int index = bestHashFunc.getHash(key, hashListOpenAddress.length);
        if (hashListOpenAddress[index] != Integer.MIN_VALUE) {
            /*int barrier = index;
            while (index != hashListOpenAddress.length) {
                countCompare++;
                if (hashListOpenAddress[index] == key) {
                    time = System.nanoTime() - time;
                    return true;
                }
                index++;
            }
            index = 0;
            while (index != barrier) {
                countCompare++;
                if (hashListOpenAddress[index] == key) {
                    time = System.nanoTime() - time;
                    return true;
                }
                index++;
            }
			*/
			//////
			int barrier = hashListOpenAddress.length;
			while (hashListOpenAddress[index] != key) {
                countCompare++;
                index++;
                if (index == barrier) {
                    if(index == hashListOpenAddress.length){
                        barrier = hashListOpenAddress.length - countCompare; // Начальный индекс
						index = 0;
					}
                    if (index == barrier) {
                        time = System.nanoTime() - time;
                        return false;
                    }
                }
            }
			time = System.nanoTime() - time;
            return true;
			//////
        }
        time = System.nanoTime() - time;
        return false;
    }

    public boolean searchChain(int key){
        time = System.nanoTime();
        countCompare = 1;
        int index = bestHashFunc.getHash(key, listKey.length);
        for (int i: hashListChain[index]) {

            if (i == key){
                time = System.nanoTime() - time;
                return true;
            }
            countCompare++;
        }
        time = System.nanoTime() - time;
        return false;

    }
}
