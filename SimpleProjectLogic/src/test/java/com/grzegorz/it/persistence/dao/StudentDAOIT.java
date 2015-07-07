package com.grzegorz.it.persistence.dao;

import com.google.common.collect.Lists;
import com.grzegorz.it.configuration.AbstractArquillianTest;
import com.grzegorz.it.configuration.ArquillianDeploymentClasses;
import com.grzegorz.persistence.dao.StudentDAO;
import com.grzegorz.persistence.entity.StudentEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


@RunWith(Arquillian.class)
@PersistenceTest
@Cleanup(phase = TestExecutionPhase.AFTER, strategy = CleanupStrategy.STRICT)
public class StudentDAOIT extends AbstractArquillianTest {

    @Inject
    private StudentDAO sut;

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Deployment
    public static WebArchive createJarArchive() {
        return prepareBaseArchive().addPackages(true, "org.assertj").addClasses(ArquillianDeploymentClasses.STUDENT_IT);
    }

    @Test
    public void daoShouldReturnEmptyStudentList() {
        List<StudentEntity> students = sut.getAll();

        assertThat(students).isNotNull().isEmpty();
    }

    @Test
    @UsingDataSet("dataset/StudentList.xml")
    public void daoShouldObtainListWithTwoStudents() {
        List<StudentEntity> students = sut.getAll();

        assertThat(students).isNotNull().hasSize(2);
    }

    @Test
    @UsingDataSet("dataset/StudentList.xml")
    public void daoShouldObtainStudentById() {
        StudentEntity student = sut.find(1);

        assertThat(student).isNotNull();
        assertThat(student.getName().equals("Jarzyna"));
    }

    @Test
    @UsingDataSet("dataset/StudentList.xml")
    public void daoShouldObtainStudentByName() {
        StudentEntity student = sut.getByName("Jarzyna");

        assertThat(student).isNotNull();
        assertThat(student.getId() == 1);
    }

    @Test
    @ShouldMatchDataSet("dataset/StudentList.xml")
    public void daoShouldAddAStudentToTable() {
        List<StudentEntity> l = createStudentList();

        sut.add(l.get(0));
        sut.add(l.get(1));
    }

    @Test
    @UsingDataSet("dataset/Student.xml")
    @ShouldMatchDataSet("dataset/StudentAfterEdit.xml")
    public void daoShouldUpdateStudent() {
        StudentEntity e = sut.find(1);

        e.setAddress("ul. Trzecia");
        e.setEmail("krzysztof.jarzyna@gmail.com");
        e.setPhoneNumber("572456876");
        e.setState("2");

        sut.update(e);
    }

    @Test
    @UsingDataSet("dataset/StudentList.xml")
    @ShouldMatchDataSet("dataset/EmptyStudentList.xml")
    public void daoShouldDeleteStudent() {
        sut.remove(createStudentEntityWithId(1));
        sut.remove(createStudentEntityWithId(2));
    }

    private List<StudentEntity> createStudentList() {
        List<StudentEntity> l = Lists.newArrayList();

        l.add(createStudentOne());
        l.add(createStudentTwo());

        return l;
    }

    private StudentEntity createStudentOne() {
        return new StudentEntity("Krzysztof", "Jarzyna", "80120223987", "malpa@gmail.com", "677788344", "ul. Pierwsza",
                "1", obtainDate("1980-12-02"), "Szczecin");
    }

    private StudentEntity createStudentTwo() {
        return new StudentEntity("Franciszek", "Pieczara", "57022327947", "kon@gmail.com", "987456312", "ul. Druga",
                "3", obtainDate("1957-02-23"), "Warszawa");
    }

    private Date obtainDate(String text) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(text);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Formatowanie daty nie powiodlo sie!");
        }
    }

    private StudentEntity createStudentEntityWithId(Integer i) {
        StudentEntity e = new StudentEntity();
        e.setId(i);
        return e;
    }


    @Test
    public void daoShouldNotAllowToSetNonStandardLengthOfPesel() {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();


        StudentEntity firstStudent = new StudentEntity();
        firstStudent.setPesel("1234512345123451234512345");
        firstStudent.setEmail("to_nie_jest_prawidlowy_email");

        StudentEntity secondStudent = new StudentEntity();
        secondStudent.setPesel("12345678901");
        secondStudent.setEmail("test@mail.com");

        Set<ConstraintViolation<StudentEntity>> constraintViolationsFirst = validator.validate(firstStudent);
        Set<ConstraintViolation<StudentEntity>> constraintViolationsSecond = validator.validate(secondStudent);

        assertThat(constraintViolationsFirst.size() == 2);
        //assertThat(constraintViolationsFirst.iterator().next().getMessage().equals("size must me between 10 and 11"));

        assertThat(constraintViolationsSecond.size() == 0);

    }

}
