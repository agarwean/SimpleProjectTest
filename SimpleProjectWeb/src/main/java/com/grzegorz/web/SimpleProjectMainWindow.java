package com.grzegorz.web;

import com.grzegorz.persistence.dao.DbBasedStudentDAO;
import com.grzegorz.persistence.dao.StudentDAO;
import com.grzegorz.persistence.entity.StudentEntity;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.*;

import javax.inject.Inject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Grzechu on 07-04-2015.
 */
public class SimpleProjectMainWindow extends Window {

//    @Inject
    private final StudentDAO sut;

    private VerticalLayout mainVerticalLayout;
    private Label selectedRowLabel;
    private Button addBtn, editBtn, deleteBtn;


    public SimpleProjectMainWindow(String caption) {
        super(caption);

        sut = new DbBasedStudentDAO();

        mainVerticalLayout = new VerticalLayout();

        Table studentsTable = buildTableDisplayingStudents();
        mainVerticalLayout.addComponent(studentsTable);
        mainVerticalLayout.setComponentAlignment(studentsTable, Alignment.MIDDLE_CENTER);

        HorizontalLayout buttonsHorizontalLayout = buildCRUDButtons();
        mainVerticalLayout.addComponent(buttonsHorizontalLayout);
        mainVerticalLayout.setComponentAlignment(buttonsHorizontalLayout, Alignment.MIDDLE_CENTER);

        selectedRowLabel = new Label("Wybrany wiersz: -");
        mainVerticalLayout.addComponent(selectedRowLabel);
        mainVerticalLayout.setComponentAlignment(selectedRowLabel, Alignment.MIDDLE_CENTER);

        addComponent(mainVerticalLayout);
    }


    private Table buildTableDisplayingStudents() {

        final Table studentsTable = new Table("Lista studentów");

        String[] columnHeaders = {"ID", "Imiê", "Nazwisko", "Adres", "Województwo", "E-mail", "Data urodzenia",
                "Miejsce urodzenia", "PESEL", "Nr telefonu"};

        String[] columnsIds =  {"id", "forename", "name", "address", "state", "email", "birthDate",
                "birthPlace", "pesel", "phoneNumber"};


//        List<StudentEntity> studentList = sut.getAll();
        List<StudentEntity> studentList = createStudentList();
        BeanItemContainer<StudentEntity> container = new BeanItemContainer<StudentEntity>(studentList);

        for (String s : columnsIds)
            container.addNestedContainerProperty(s);

        studentsTable.setContainerDataSource(container);
        studentsTable.setVisibleColumns(columnsIds);
        studentsTable.setColumnHeaders(columnHeaders);
        studentsTable.setSelectable(true);
        studentsTable.setImmediate(true);

        studentsTable.addListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent e) {

                if (!(studentsTable.getValue() == null)) {
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                    selectedRowLabel.setValue("Wybrany wiersz: " + studentsTable.getValue());
                } else {
                    editBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                    selectedRowLabel.setValue("Wybrany wiersz: -");
                }

            }

        });

        return studentsTable;
    }


    private HorizontalLayout buildCRUDButtons() {

        addBtn = new Button("Dodaj");

        addBtn.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SimpleProjectAddEditFormWindow addStudentFormWindow = new SimpleProjectAddEditFormWindow("Dodawanie studenta", new BeanItem<StudentEntity>(new StudentEntity()));
                addWindow(addStudentFormWindow);
            }
        });

        editBtn = new Button("Modyfikuj");
        editBtn.setEnabled(false);

        deleteBtn = new Button("Usuñ");
        deleteBtn.setEnabled(false);

        HorizontalLayout buttonsHorizontalLayout = new HorizontalLayout();
        buttonsHorizontalLayout.addComponent(addBtn);
        buttonsHorizontalLayout.addComponent(editBtn);
        buttonsHorizontalLayout.addComponent(deleteBtn);

        return buttonsHorizontalLayout;
    }



    // Metody zapo¿yczone z klasy StudentDAOIT

    private Date obtainDate(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatowanie daty nie powiodlo sie!");
        }
    }

    private StudentEntity createStudentOne() {
        return new StudentEntity("Krzysztof", "Jarzyna", "80120223987", "malpa@gmail.com", "677788344", "ul. Pierwsza",
                "1", obtainDate("1980-12-02"), "Szczecin");
    }

    private StudentEntity createStudentTwo() {
        return new StudentEntity("Franciszek", "Pieczara", "57022327947", "kon@gmail.com", "987456312", "ul. Druga",
                "3", obtainDate("1957-02-23"), "Warszawa");
    }

    private List<StudentEntity> createStudentList() {
        List<StudentEntity> l = new ArrayList<StudentEntity>();

        l.add(createStudentOne());
        l.add(createStudentTwo());

        return l;
    }

}
