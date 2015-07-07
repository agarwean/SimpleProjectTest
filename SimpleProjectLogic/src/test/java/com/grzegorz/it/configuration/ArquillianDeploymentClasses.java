package com.grzegorz.it.configuration;

import com.grzegorz.persistence.dao.DbBasedStudentDAO;
import com.grzegorz.persistence.dao.StudentDAO;
import com.grzegorz.persistence.entity.StudentEntity;

public class ArquillianDeploymentClasses {

    public static final Class<?>[] STUDENT_IT = {
            StudentDAO.class,
            DbBasedStudentDAO.class,
            StudentEntity.class
    };

}
