package com.grzegorz.persistence.dao;

import com.grzegorz.persistence.entity.StudentEntity;

import java.util.List;

public interface StudentDAO {

    List<StudentEntity> getAll();

    StudentEntity find(Integer id);

    StudentEntity getByName(String name);

    void add(StudentEntity entity);

    void remove(StudentEntity entity);

    void update(StudentEntity entity);
}
