package sample;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;

public class SItem  implements Serializable {

    String sname;
    String sh;
    String sw;
    String ss;
    String stime;
    String sprice;

    String wood;
    String sort;
    String mname;
    String price1;

    public SItem(String name, String h, String w, String s, String time, String price) {
        this.sname = name;
        this.sh = h;
        this.sw = w;
        this.ss = s;
        this.stime = time;
        this.sprice = price;
    }

    public SItem(String wood, String sort, String mname, String price1) {
        this.wood = wood;
        this.sort = sort;
        this.mname = mname;
        this.price1 = price1;
    }

}
