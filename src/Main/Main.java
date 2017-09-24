package Main;

import HashSearch.Hash.*;
import HashSearch.HashSearch;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {


    private static final int count = 1000;

    private static HashSearch hashSearch = new HashSearch(count);
    private static Form form = new Form();

    private static class ActionAnalisys implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int[] i = hashSearch.analisysEfficiency((int)form.spinnerCmr.getValue());
            form.textFieldDiv.setText(Integer.toString(i[0]));
            form.textFieldCurt.setText(Integer.toString(i[1]));
            form.textFieldMidSq.setText(Integer.toString(i[2]));
            form.textFieldMultipl.setText(Integer.toString(i[3]));
        }
    }

    private static class ActionSearch implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            boolean b = hashSearch.searchOpenAddress((long)form.spinnerSearch.getValue());
            form.textFieldOpAdr.setText(b ? "Найдено" : "Не найдено");


/*
            form.textFieldDiv.setText(Integer.toString(i[0]));
            form.textFieldCurt.setText(Integer.toString(i[1]));
            form.textFieldMidSq.setText(Integer.toString(i[2]));
            form.textFieldMultipl.setText(Integer.toString(i[3]));*/
        }
    }

    public static void main(String[] args) {

        hashSearch.hashing();

        int[] i = hashSearch.analisysEfficiency(100);
        System.out.println("hashMethodDividing  " + Integer.toString(i[0]));
        System.out.println("hashMethodCurtailing  " + Integer.toString(i[1]));
        System.out.println("hashMethodMidSquares  " + Integer.toString(i[2]));
        System.out.println("hashMethodMultipl  " + Integer.toString(i[3]));


        System.out.println(hashSearch.searchOpenAddress(12567));
        System.out.println(hashSearch.getTime());

        System.out.println(hashSearch.searchChain(12567));
        System.out.println(hashSearch.getTime());


        form.buttonAnalisys.addActionListener(new ActionAnalisys());


        /*Hash hash = new hashMethodCurtailing();
        for (int j = 123456; j < 1234567; j++) {
            System.out.println(hash.getHash(j, 2000));
        }*/
    }
}
