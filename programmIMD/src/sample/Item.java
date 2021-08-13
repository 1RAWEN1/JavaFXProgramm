package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item{

    StringProperty name;
    StringProperty h;
    StringProperty w;
    StringProperty s;
    StringProperty time;
    StringProperty price;

    StringProperty wood;
    StringProperty sort;
    StringProperty mname;
    StringProperty price1;

    StringProperty mat;
    StringProperty size;

    StringProperty c1;
    StringProperty c2;
    StringProperty c3;

    public Item(String name, String h, String w, String s, String time, String price) {
        this.name = new SimpleStringProperty(name);
        this.h = new SimpleStringProperty(h);
        this.w = new SimpleStringProperty(w);
        this.s = new SimpleStringProperty(s);
        this.time = new SimpleStringProperty(time);
        this.price = new SimpleStringProperty(price);
    }

    public Item(String wood, String sort, String mname, String price1) {
        this.wood = new SimpleStringProperty(wood);
        this.sort = new SimpleStringProperty(sort);
        this.mname = new SimpleStringProperty(mname);
        this.price1 = new SimpleStringProperty(price1);
    }

    public Item(String c1, String c2, String c3) {
        this.c1 = new SimpleStringProperty(c1);
        this.c2 = new SimpleStringProperty(c2);
        this.c3 = new SimpleStringProperty(c3);
    }

    public Item(String mat, String size) {
        this.mat = new SimpleStringProperty(mat);
        this.size = new SimpleStringProperty(size);
    }
}
