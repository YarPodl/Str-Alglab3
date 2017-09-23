package Main;

import HashSearch.Hash.Hash;
import HashSearch.Hash.hashMethodDividing;
import HashSearch.HashSearch;

public class Main {
    public static void main(String[] args) {
        HashSearch hashSearch = new HashSearch(1000);

        int[] i = hashSearch.analisysEfficiency(100);
        for (int j: i) {
            System.out.println(j);
        }


        Hash hash = new hashMethodDividing();
        System.out.println(hash.getHash(23610, 100));
    }
}
