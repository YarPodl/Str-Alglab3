package Main;

import HashSearch.Hash.*;
import HashSearch.HashSearch;

public class Main {
    public static void main(String[] args) {
        HashSearch hashSearch = new HashSearch(20000);
        hashSearch.hashing();
        /*
        int[] i = hashSearch.analisysEfficiency(100);
        System.out.println("hashMethodDividing  " + Integer.toString(i[0]));
        System.out.println("hashMethodCurtailing  " + Integer.toString(i[1]));
        System.out.println("hashMethodMidSquares  " + Integer.toString(i[2]));
        System.out.println("hashMethodMultipl  " + Integer.toString(i[3]));
        */

        System.out.println(hashSearch.searchOpenAddress(12567));
        System.out.println(hashSearch.getTime());

        System.out.println(hashSearch.searchChain(12567));
        System.out.println(hashSearch.getTime());

        Form form = new Form();


        /*Hash hash = new hashMethodCurtailing();
        for (int j = 123456; j < 1234567; j++) {
            System.out.println(hash.getHash(j, 2000));
        }*/
    }
}
