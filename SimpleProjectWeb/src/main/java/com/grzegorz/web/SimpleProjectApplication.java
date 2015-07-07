package com.grzegorz.web;

import com.vaadin.Application;


public class SimpleProjectApplication extends Application {
    
    private SimpleProjectMainWindow mainWindow;

    @Override
    public void init() {

        mainWindow = new SimpleProjectMainWindow("Simple Project Main Window");

        setMainWindow(mainWindow);

        // Null pointer exception!
        /*sut.add(studentList.get(0));
        sut.add(studentList.get(1));*/

    }

}
