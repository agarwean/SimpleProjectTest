package com.grzegorz.web;

import com.grzegorz.persistence.entity.StudentEntity;
import com.grzegorz.student.factory.StudentFieldFactory;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

public class SimpleProjectAddEditFormWindow extends Window {

    private BeanItem<StudentEntity> sItem;

    public SimpleProjectAddEditFormWindow(String caption, BeanItem<StudentEntity> sItem) {
        super(caption);
        this.sItem = sItem;

        setClosable(true);
        /*setWidth("640");
        setHeight("520");*/
        center();

        addComponent(buildMainLayout());

        getContent().setSizeUndefined();
    }

    private VerticalLayout buildMainLayout() {

        VerticalLayout mainLayout = new VerticalLayout();

        Form form = buildAddEditForm();
        HorizontalLayout buttons = buildButtons();

        mainLayout = new VerticalLayout();

        mainLayout.addComponent(form);
        mainLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

        mainLayout.addComponent(buttons);
        mainLayout.setComponentAlignment(buttons, Alignment.MIDDLE_CENTER);

        return mainLayout;
    }

    private Form buildAddEditForm() {
        Form form = new Form();
        String[] vProp =  {"forename", "name", "address", "state", "email", "birthDate",
                "birthPlace", "pesel", "phoneNumber"};
        form.setFormFieldFactory(new StudentFieldFactory());
        form.setItemDataSource(sItem);
        form.setVisibleItemProperties(vProp);
        return form;
    }


    private HorizontalLayout buildButtons() {

        HorizontalLayout buttons = new HorizontalLayout();

        Button addButton = new Button("Dodaj");
        buttons.addComponent(addButton);
        buttons.setComponentAlignment(addButton, Alignment.MIDDLE_CENTER);

        addButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                getWindow().showNotification("ToBeDone - Student zosta³ pomy¶lnie dodany.");
            }
        });


        Button cancelButton = new Button("Anuluj");
        buttons.addComponent(cancelButton);
        buttons.setComponentAlignment(cancelButton, Alignment.MIDDLE_CENTER);

        cancelButton.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                close();
            }
        });


        return buttons;
    }

    private Container buildStatesContainer() {

        IndexedContainer statesContainer = new IndexedContainer();
        statesContainer.addContainerProperty("state", String.class, "");
        statesContainer.addItem("¶l±skie");
        statesContainer.addItem("ma³opolskie");

        return statesContainer;
    }

    private String[] buildStatesCollection() {

        String[] states = {"¶l±skie", "ma³opolskie", "zachodnio-pomorskie"};

        return states;
    }

    private ComboBox buildStatesComboBox() {

        ComboBox states = new ComboBox("Województwo");
        states.addItem("¶l±skie");
        states.addItem("ma³opolskie");
        states.addItem("zachodnio-pomorskie");

        return states;
    }



}
