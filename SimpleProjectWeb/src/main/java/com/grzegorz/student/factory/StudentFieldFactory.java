package com.grzegorz.student.factory;

import com.grzegorz.student.combobox.StateComboBox;
import com.vaadin.data.Item;
import com.vaadin.ui.*;

public class StudentFieldFactory implements FormFieldFactory {

    private static final String EMPTY = "";

    @Override
    public Field createField(Item item, Object propertyId, Component uiContext) {
        String pid = (String) propertyId;

        if (pid.equals("forename")) {
            return createTextField("Imiê");
        } else if (pid.equals("name")){
            return createTextField("Nazwisko");
        } else if (pid.equals("email")){
            return createTextField("E-mail");
        } else if (pid.equals("address")){
            return createTextField("Adres");
        } else if (pid.equals("birthDate")){
            return new DateField("Data urodzenia");
        } else if (pid.equals("birthPlace")){
            return createTextField("Miejsce urodzenia");
        } else if (pid.equals("state")){
            return new StateComboBox();
        } else if (pid.equals("pesel")){
            return createTextField("Pesel");
        } else if (pid.equals("phoneNumber")){
            return createTextField("Nr telefonu");
        }

        return null;
    }

    private TextField createTextField(String caption){
        TextField tField = new TextField(caption);
        tField.setNullRepresentation(EMPTY);
        return tField;
    }
}
