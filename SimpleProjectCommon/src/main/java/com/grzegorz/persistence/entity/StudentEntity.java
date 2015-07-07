package com.grzegorz.persistence.entity;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "student")
@NamedQueries({
        @NamedQuery(name = "Student.findAll", query = "SELECT s FROM StudentEntity s"),
        @NamedQuery(name = "Student.findByName", query = "SELECT s FROM StudentEntity s WHERE s.name = :name")})
public class StudentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id")
    private int id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "name")
    private String name;

    @Column(name = "pesel")
    @Size(min = 11, max = 11)
    private String pesel;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "state")
    private String state;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "birth_place")
    private String birthPlace;


    public StudentEntity() {
    }

    public StudentEntity(String forename, String name, String pesel, String email, String phoneNumber, String address, String state, Date birthDate, String birthPlace) {
        this.forename = forename;
        this.name = name;
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.state = state;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForename() {
        return this.forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", address=" + address
                + ", birthDate=" + birthDate + ", birthPlace=" + birthPlace
                + ", email=" + email + ", forename=" + forename + ", name="
                + name + ", pesel=" + pesel + ", phoneNumber=" + phoneNumber
                + ", state=" + state + "]";
    }

}