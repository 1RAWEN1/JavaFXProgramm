package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Save implements Serializable {

    ArrayList<SItem> list;
    ArrayList<SItem> list1;
    ArrayList<String> woods;
    ArrayList<ArrayList<String>> m;
    ArrayList<Cart> cartL;
    public Save(ArrayList<SItem> list, ArrayList<SItem> list1, ArrayList<String> woods, ArrayList<ArrayList<String>> m, ArrayList<Cart> cartL) {
        this.list=list;
        this.list1=list1;
        this.woods=woods;
        this.m=m;
        this.cartL=cartL;
    }
}
