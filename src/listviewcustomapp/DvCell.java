/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviewcustomapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class DvCell {

    // private BooleanProperty detected;
    public SimpleStringProperty onSet;
    public SimpleStringProperty offSet;
    public SimpleStringProperty code;
    ////  private StringProperty defectLongDescription;
    // private Defect(boolean invited, String defDeccription, String loc) {

    public DvCell(String onSet, String offSet, String code) {

        //  this.detected = new SimpleBooleanProperty(invited);
        this.onSet = new SimpleStringProperty(onSet);

        this.offSet = new SimpleStringProperty(offSet);
        this.code = new SimpleStringProperty(code);

    }

    public String getOnSet() {
        return onSet.get();
    }

    public void setOnSet(String ons) {
        onSet.set(ons);
    }

    public String getOffSet() {
        return offSet.get();
    }

    public void setOffSet(String ofs) {
        offSet.set(ofs);
    }

    public String getCode() {
        return code.get();
    }

    public void setCode(String c) {
        code.set(c);
    }

}
