package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;

public class Main extends Application {

    @FXML
    TableView table;
    @FXML
    TableColumn<Item, String> nameColumn;
    @FXML
    TableColumn<Item, String> hColumn;
    @FXML
    TableColumn<Item, String> wColumn;
    @FXML
    TableColumn<Item, String> sColumn;
    @FXML
    TableColumn<Item, String> tColumn;
    @FXML
    TableColumn<Item, String> priceColumn;
    @FXML
    TextField delName;
    @FXML
    TextField nameField;
    @FXML
    TextField hField;
    @FXML
    TextField wField;
    @FXML
    TextField sField;
    @FXML
    TextField timeField;
    @FXML
    TextField priceField;
    @FXML
    Label vLab;
    @FXML
    Label delLab;
    ObservableList<Item> list;
    ArrayList<SItem> list1;
    ObservableList<Item> myMat= FXCollections.observableArrayList();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Assistant-Classifier");
        primaryStage.setScene(new Scene(root, 600, 415));
        primaryStage.show();
    }

    public void initialize() {
        types.add("Брусок");
        types.add("Доска");
        types.add("Плашка");
        types.add("Фанера");
        ObservableList<String> ol2= FXCollections.observableArrayList(types);
        material.setItems(ol2);
        wood.setItems(ol2);
        list1=new ArrayList<SItem>();
        list12=new ArrayList<SItem>();
        list= FXCollections.observableArrayList();
        list2= FXCollections.observableArrayList();
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.ser")))
        {
            Save s=(Save)ois.readObject();
            woods=s.woods;
            m=s.m;
            cartList=s.cartL;
            for(int i=0;i<s.cartL.size();i++){
                Item item=new Item(s.cartL.get(i).name,s.cartL.get(i).name1,s.cartL.get(i).sort);
                ol.add(item);
            }
            list12=s.list1;
            for(int i=0;i<s.list1.size();i++){
                Item item=new Item(s.list1.get(i).wood, s.list1.get(i).sort, s.list1.get(i).mname, s.list1.get(i).price1);
                list2.add(item);
            }
            list1=s.list;
            for(int i=0;i<s.list.size();i++){
                Item item=new Item(s.list.get(i).sname, s.list.get(i).sh, s.list.get(i).sw, s.list.get(i).ss, s.list.get(i).stime, s.list.get(i).sprice);
                list.add(item);
            }
        }
        catch(Exception e){}
        ObservableList<String> sorts= FXCollections.observableArrayList(woods);
        sort.setItems(sorts);
        sort1.setItems(sorts);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        hColumn.setCellValueFactory(cellData -> cellData.getValue().h);
        wColumn.setCellValueFactory(cellData -> cellData.getValue().w);
        sColumn.setCellValueFactory(cellData -> cellData.getValue().s);
        tColumn.setCellValueFactory(cellData -> cellData.getValue().time);
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().price);
        table.setItems(list);
        ArrayList<String> names=new ArrayList<String>();
        for(int i=0;i<list1.size();i++){
            names.add(list1.get(i).sname);
        }
        ObservableList<String> ol1= FXCollections.observableArrayList(names);
        obType.setItems(ol1);
        matColumn.setCellValueFactory(cellData -> cellData.getValue().wood);
        sColumn1.setCellValueFactory(cellData -> cellData.getValue().sort);
        shopColumn.setCellValueFactory(cellData -> cellData.getValue().mname);
        priceColumn1.setCellValueFactory(cellData -> cellData.getValue().price1);

        matColumn3.setCellValueFactory(cellData -> cellData.getValue().mat);
        Size3.setCellValueFactory(cellData -> cellData.getValue().size);
        c1Column.setCellValueFactory(cellData -> cellData.getValue().c1);
        c2Column.setCellValueFactory(cellData -> cellData.getValue().c2);
        c3Column.setCellValueFactory(cellData -> cellData.getValue().c3);
        table1.setItems(list2);
        MaterialList.setItems(ol2);
        table4.setItems(ol);
        sortWood.setItems(sorts);
    }

    public void save(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.ser")))
        {
            Save save=new Save(list1, list12, woods, m, cartList);
            oos.writeObject(save);
        }
        catch(Exception e){}
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void addInTable(ActionEvent actionEvent) {
        String obN = nameField.getText();
        String obH = hField.getText();
        String obW = wField.getText();
        String obS = sField.getText();
        String obT = timeField.getText();
        String obP = priceField.getText();
        if(!obN.equals("") && !obH.equals("") && !obW.equals("") && !obS.equals("") && !obT.equals("")  && !obP.equals("")) {
            Item item = new Item(obN, obH, obW, obS, obT, obP);
            SItem sitem = new SItem(obN, obH, obW, obS, obT, obP);

            list.add(item);
            list1.add(sitem);
            vLab.setText("");
            nameField.setText("");
            hField.setText("");
            wField.setText("");
            sField.setText("");
            timeField.setText("");
            priceField.setText("");
        }
        else{
            vLab.setText("Не все поля заполнены!");
        }
        ArrayList<String> names=new ArrayList<String>();
        for(int i=0;i<list1.size();i++){
            names.add(list1.get(i).sname);
        }
        ObservableList<String> ol1= FXCollections.observableArrayList(names);
        table.setItems(list);
        obType.setItems(ol1);
        save();
    }

    public void delete(ActionEvent actionEvent) {
        String dname = delName.getText();
        if(!dname.equals("")) {
            for(int i=0;i<list1.size();i++){
                if(list1.get(i).sname.equals(dname)){
                    list1.remove(i);
                    list.remove(i);
                    delLab.setText("");
                    delName.setText("");
                    break;
                }
                else{
                    delLab.setText("Изделие не найдено!");
                }
            }
        }
        ArrayList<String> names=new ArrayList<String>();
        for(int i=0;i<list1.size();i++){
            names.add(list1.get(i).sname);
        }
        ObservableList<String> ol1= FXCollections.observableArrayList(names);
        table.setItems(list);
        obType.setItems(ol1);
        save();
    }



    @FXML
    TextField woodField;
    @FXML
    TextField mName;
    @FXML
    TextField priceField1;
    @FXML
    ListView material;

    @FXML
    ListView wood;
    @FXML
    ListView sort;
    @FXML
    ListView mname;
    @FXML
    ListView price;

    @FXML
    ListView sort1;
    @FXML
    Label mLab;
    @FXML
    Label magLab;
    @FXML
    Label sortLab;
    @FXML
    TableView table1;

    @FXML
    TableColumn<Item, String> matColumn;
    @FXML
    TableColumn<Item, String> sColumn1;
    @FXML
    TableColumn<Item, String> shopColumn;
    @FXML
    TableColumn<Item, String> priceColumn1;
    ArrayList<String> types=new ArrayList<String>();
    ArrayList<String> woods=new ArrayList<String>();
    ArrayList<ArrayList<String>> m=new ArrayList<ArrayList<String>>();
    ObservableList<Item> list2;
    ArrayList<SItem> list12;

    public void deleteItem(){
        String mName1 = mName.getText();
        String price = priceField1.getText();
        ObservableList<String> selSort= sort1.getSelectionModel().getSelectedItems();
        ObservableList<String> selMat= material.getSelectionModel().getSelectedItems();
        if(!mName1.equals("") && !price.equals("") && selSort.size()>0 && selMat.size()>0) {
            for(int i=0;i<m.size();i++){
                if(m.get(i).get(0).equals(selMat.get(0)) && m.get(i).get(1).equals(selSort.get(0)) && m.get(i).get(2).equals(mName1) && m.get(i).get(3).equals(price)){
                    m.remove(i);
                    mName.setText("");
                    priceField1.setText("");
                    magLab.setText("");
                    break;
                }
                else{
                    magLab.setText("Данные не найдены!");
                }
            }
        }
        else{
            magLab.setText("Не все поля заполнены!");
        }
        save();
    }

    public void removeItem(ActionEvent actionevent) {
        ObservableList<String> selected= wood.getSelectionModel().getSelectedItems();
        ObservableList<String> selected1= sort.getSelectionModel().getSelectedItems();
        ObservableList<String> selected2= mname.getSelectionModel().getSelectedItems();
        ObservableList<String> selected3= price.getSelectionModel().getSelectedItems();
        if(selected.size()>0 && selected1.size()>0 && selected2.size()>0 && selected3.size()>0 ) {
            for(int i=0;i<list12.size();i++){
                if(list12.get(i).wood.equals(selected.get(0)) && list12.get(i).sort.equals(selected1.get(0)) && list12.get(i).mname.equals(selected2.get(0)) && list12.get(i).price1.equals(selected3.get(0))){
                    list12.remove(i);
                    list2.remove(i);
                }
            }
            mLab.setText("");
        }
        else{
            mLab.setText("Не все поля заполнены!");
        }

        table1.setItems(list2);
        save();
    }
    public void addItem(ActionEvent actionevent) {
        ObservableList<String> selected= wood.getSelectionModel().getSelectedItems();
        ObservableList<String> selected1= sort.getSelectionModel().getSelectedItems();
        ObservableList<String> selected2= mname.getSelectionModel().getSelectedItems();
        ObservableList<String> selected3= price.getSelectionModel().getSelectedItems();
        if(selected.size()>0 && selected1.size()>0 && selected2.size()>0 && selected3.size()>0 ) {
            Item item = new Item(selected.get(0), selected1.get(0), selected2.get(0), selected3.get(0));
            SItem sitem = new SItem(selected.get(0), selected1.get(0), selected2.get(0), selected3.get(0));

            list2.add(item);
            list12.add(sitem);
            mLab.setText("");
        }
        else{
            mLab.setText("Не все поля заполнены!");
        }

        table1.setItems(list2);
        save();
    }
    public void addItem2(MouseEvent actionEvent) {
        ObservableList<String> selected= sort.getSelectionModel().getSelectedItems();
        ObservableList<String> selected1= wood.getSelectionModel().getSelectedItems();
        if(selected.size()>0 && !selected.get(0).equals("") && selected1.size()>0) {
            ArrayList<String> al=new ArrayList<String>();
            for(int i=0;i<m.size();i++){
                if(m.get(i).get(1).equals(selected.get(0)) && m.get(i).get(0).equals(selected1.get(0))){
                    al.add(m.get(i).get(2));
                }
            }
            ObservableList<String> ol= FXCollections.observableArrayList(al);
            mname.setItems(ol);
        }
        ObservableList<String> ol= FXCollections.observableArrayList(new ArrayList<String>());
        price.setItems(ol);
    }
    public void addItem3(MouseEvent actionEvent) {
        ObservableList<String> selected2= sort.getSelectionModel().getSelectedItems();
        ObservableList<String> selected1= wood.getSelectionModel().getSelectedItems();
        ObservableList<String> selected= mname.getSelectionModel().getSelectedItems();
        if(selected.size()>0 && !selected.get(0).equals("") && selected1.size()>0 && selected2.size()>0) {
            ArrayList<String> pr=new ArrayList<String>();
            for(int i=0;i<m.size();i++){
                if(m.get(i).get(2).equals(selected.get(0)) && m.get(i).get(1).equals(selected2.get(0)) && m.get(i).get(0).equals(selected1.get(0))){
                    pr.add(m.get(i).get(3));
                }
            }
            ObservableList<String> ol= FXCollections.observableArrayList(pr);
            price.setItems(ol);
        }
    }

    public void addSort(ActionEvent actionEvent) {
        String wood = woodField.getText();
        if(!wood.equals("")) {
            woods.add(wood);
            sortLab.setText("");
            woodField.setText("");
        }
        else{
            sortLab.setText("Введите название сорта");
        }
        ObservableList<String> ol= FXCollections.observableArrayList(woods);
        sort.setItems(ol);
        sort1.setItems(ol);
        sortWood.setItems(ol);
        save();
    }

    public void deleteSort(ActionEvent actionEvent) {
        String wood = woodField.getText();
        if(!wood.equals("")) {
            for(int i=0;i<woods.size();i++){
                if(woods.get(i).equals(wood)){
                    woods.remove(i);
                    sortLab.setText("");
                    woodField.setText("");
                    break;
                }
                else{
                    sortLab.setText("Сорт не найден");
                }
            }
        }
        else{
            sortLab.setText("Введите название сорта");
        }
        ObservableList<String> ol= FXCollections.observableArrayList(woods);
        sort.setItems(ol);
        sort1.setItems(ol);
        sortWood.setItems(ol);
        save();
    }

    public void addM(ActionEvent actionEvent) {
        String mName1 = mName.getText();
        String price = priceField1.getText();
        ObservableList<String> selSort= sort1.getSelectionModel().getSelectedItems();
        ObservableList<String> selMat= material.getSelectionModel().getSelectedItems();
        if(!mName1.equals("") && !price.equals("") && selSort.size()>0 && selMat.size()>0) {
            ArrayList<String> al=new ArrayList<String>();
            al.add(selMat.get(0));
            al.add(selSort.get(0));
            al.add(mName1);
            al.add(price);
            int p=0;
            for(int i=0;i<m.size();i++){
                int p1=Integer.parseInt(price);
                if(Integer.parseInt(m.get(i).get(3))>=p1){
                    p=i;
                    break;
                }
                else{
                    p=i+1;
                }
            }
            m.add(p, al);
            magLab.setText("");
            mName.setText("");
            priceField1.setText("");
        }
        else{
            magLab.setText("Не все поля заполнены!");
        }
        save();
    }


    @FXML
    Label helpLab;

    @FXML
    ListView obType;
    @FXML
    ListView sortWood;
    @FXML
    ListView MaterialList;

    @FXML
    TextField obName;
    @FXML
    TextField sizeField;

    @FXML
    TableColumn<Item, String> matColumn3;
    @FXML
    TableColumn<Item, String> Size3;
    @FXML
    TableView table3;

    @FXML
    TableColumn<Item, String> c1Column;
    @FXML
    TableColumn<Item, String> c2Column;
    @FXML
    TableColumn<Item, String> c3Column;
    @FXML
    TableView table4;

    @FXML
    Label lab;

    ArrayList<ArrayList<String>> materials=new ArrayList<ArrayList<String>>();

    ArrayList<Cart> cartList=new ArrayList<Cart>();
    ObservableList<Item> ol= FXCollections.observableArrayList();

    public void addObject(ActionEvent actionevent) {
        myMat.clear();
        String obname=obName.getText();
        ObservableList<String> obtype= obType.getSelectionModel().getSelectedItems();
        ObservableList<String> sortwood= sortWood.getSelectionModel().getSelectedItems();
        if(!obname.equals("") && obtype.size()>0 && sortwood.size()>0 && materials.size()>0){
            Cart cart=new Cart(obname,obtype.get(0),sortwood.get(0),materials);
            cartList.add(cart);

            Item item=new Item(cart.name,cart.name1,cart.sort);
            ol.add(item);
        }
        table4.setItems(ol);
        save();
    }

    public void addMaterial(ActionEvent actionevent){
        String s=sizeField.getText();
        ObservableList<String> obtype= MaterialList.getSelectionModel().getSelectedItems();
        if(!s.equals("") && obtype.size()>0){
            ArrayList<String> al=new ArrayList<String>();
            al.add(s);
            al.add(obtype.get(0));
            materials.add(al);
            myMat.add(new Item(obtype.get(0), s));
            lab.setText("");
        }
        else{
            lab.setText("Не все поля заполнены!");
        }
        sizeField.setText("");
        table3.setItems(myMat);
    }

    public void deleteMaterial(){
        ObservableList<Item> selOb= table3.getSelectionModel().getSelectedItems();
        if(selOb.size()>0) {
            for (int i = 0; i < myMat.size(); i++) {
                if (myMat.get(i).mat.equals(selOb.get(0).mat) && myMat.get(i).size.equals(selOb.get(0).size)){
                    myMat.remove(i);
                    lab.setText("");
                }
                else{
                    lab.setText("Материал почему-то не найеден");
                }
            }
        }
        else{
            lab.setText("Выберите материал из списка!");
        }
    }

    public void setCartValue(ActionEvent actionevent) {
        ObservableList<Item> selOb= table4.getSelectionModel().getSelectedItems();
        if(selOb.size()>0){
            for(int i=0;i<cartList.size();i++){
                if(selOb.get(0).c1.getValue().equals(cartList.get(i).name)){
                    obName.setText(cartList.get(i).name);
                    myMat.clear();
                    for(int i2=0;i2<obType.getItems().size();i2++){
                        if(obType.getItems().get(i2).equals(cartList.get(i).name1)){
                            obType.getSelectionModel().select(i2);
                        }
                    }
                    for(int i3=0;i3<sortWood.getItems().size();i3++){
                        if(sortWood.getItems().get(i3).equals(cartList.get(i).sort)){
                            sortWood.getSelectionModel().select(i3);
                        }
                    }
                    for(int i1=0;i1<cartList.get(i).materials.size();i1++){
                        myMat.add(new Item(cartList.get(i).materials.get(i1).get(1), cartList.get(i).materials.get(i1).get(0)));
                    }
                    table3.setItems(myMat);
                }
            }
        }
        helpLab.setText("Перейдите во вкладку технологии");
    }

    public void deleteCart(ActionEvent actionevent) {
        ObservableList<Item> selOb= table4.getSelectionModel().getSelectedItems();
        if(selOb.size()>0){;
            for(int i=0;i<cartList.size();i++){
                if(selOb.get(0).c1.getValue().equals(cartList.get(i).name)){
                    cartList.remove(i);
                    ol.remove(i);
                    helpLab.setText("");
                    break;
                }
            }
        }
        else{
            helpLab.setText("Выберите объект из списка!");
        }
        save();
        table4.setItems(ol);
    }

}
