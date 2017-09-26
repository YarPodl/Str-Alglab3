package Main;

import HashSearch.HashSearch;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Main {


    private static int count = 1000;
    private static int countSearch = 10000;
    private static int maxNumberForSearch = 20000;

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
        public void actionPerformed(ActionEvent e) {/*
            boolean b = hashSearch.searchOpenAddress((int)form.spinnerSearch.getValue());
            form.textFieldOpAdr.setText(b ? "Найдено" : "Не найдено");
            form.textFieldOpAdrTime.setText(Long.toString(hashSearch.getTime()));

            b = hashSearch.searchChain((int)form.spinnerSearch.getValue());
            form.textFieldCh.setText(b ? "Найдено" : "Не найдено");
            form.textFieldChTime.setText(Long.toString(hashSearch.getTime()));
*/
            int sumTimeOpenAddress = 0, sumCompareOpenAddress = 0, key;
            int sumTimeChain = 0, sumCompareChain = 0;
            int countOpenAddress = 0, countChain = 0;
            Random random = new Random();
            for (int i = 0; i < countSearch; i++){
                key = random.nextInt(maxNumberForSearch);
                if(hashSearch.searchOpenAddress(key)){
                    countOpenAddress++;
                }
                sumCompareOpenAddress += hashSearch.getCountCompare();
                sumTimeOpenAddress += hashSearch.getTime();
                if(hashSearch.searchChain(key)){
                    countChain++;
                }
                sumCompareChain += hashSearch.getCountCompare();
                sumTimeChain += hashSearch.getTime();
            }
            form.textFieldOpAdrTime.setText(Integer.toString(sumTimeOpenAddress/countSearch));
            form.textFieldChTime.setText(Integer.toString(sumTimeChain/countSearch));
            form.textFieldCmpOpAdr.setText(Double.toString((double)sumCompareOpenAddress/countSearch));
            form.textFieldCmpCh.setText(Double.toString((double)sumCompareChain/countSearch));
            form.textFieldOpAdr.setText(Integer.toString(countOpenAddress));
            form.textFieldCh.setText(Integer.toString(countChain));

/*
            form.textFieldDiv.setText(Integer.toString(i[0]));
            form.textFieldCurt.setText(Integer.toString(i[1]));
            form.textFieldMidSq.setText(Integer.toString(i[2]));
            form.textFieldMultipl.setText(Integer.toString(i[3]));*/
        }
    }

    private static class changeCount implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            count = (int)form.spinnerCount.getValue();
            hashSearch.newListKey(count);
            hashSearch.cleanHashLists();
            hashSearch.hashing();
        }
    }

    private static class changeMax implements ChangeListener {
        public void stateChanged(ChangeEvent e) {
            hashSearch.setMaxNumber((int)form.spinnerMax.getValue());
            hashSearch.newListKey(count);
            hashSearch.cleanHashLists();
            hashSearch.hashing();
        }
    }

    public static void main(String[] args) {

        hashSearch.hashing();
/*
        int[] i = hashSearch.analisysEfficiency(100);
        System.out.println("hashMethodDividing  " + Integer.toString(i[0]));
        System.out.println("hashMethodCurtailing  " + Integer.toString(i[1]));
        System.out.println("hashMethodMidSquares  " + Integer.toString(i[2]));
        System.out.println("hashMethodMultipl  " + Integer.toString(i[3]));


        System.out.println(hashSearch.searchOpenAddress(12567));
        System.out.println(hashSearch.getTime());

        System.out.println(hashSearch.searchChain(12567));
        System.out.println(hashSearch.getTime());

*/
        form.buttonAnalisys.addActionListener(new ActionAnalisys());
        form.buttonSearch.addActionListener(new ActionSearch());
        form.spinnerMax.addChangeListener(new changeMax());
        form.spinnerCount.addChangeListener(new changeCount());


        /*Hash hash = new hashMethodCurtailing();
        for (int j = 123456; j < 1234567; j++) {
            System.out.println(hash.getHash(j, 2000));
        }*/
    }
}
