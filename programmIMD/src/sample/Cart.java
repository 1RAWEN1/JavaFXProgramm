package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    String name;
    String name1;
    String sort;
    ArrayList<ArrayList<String>> materials;

    public Cart(String name, String name1, String sort, ArrayList<ArrayList<String>> mat){
        this.name=name;
        this.name1=name1;
        this.sort=sort;
        this.materials=mat;
    }
}
