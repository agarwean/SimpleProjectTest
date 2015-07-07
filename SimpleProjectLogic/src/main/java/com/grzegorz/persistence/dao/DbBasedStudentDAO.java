package com.grzegorz.persistence.dao;

import com.grzegorz.persistence.entity.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class DbBasedStudentDAO implements StudentDAO {

    @PersistenceContext(unitName = "studentPU")
    private EntityManager em;

    @Override
    public List<StudentEntity> getAll() {
        return em.createNamedQuery("Student.findAll", StudentEntity.class).getResultList();
    }

    @Override
    public StudentEntity getByName(String name) {
        TypedQuery<StudentEntity> q = em.createNamedQuery("Student.findByName", StudentEntity.class);
        q.setParameter("name", name);
        return q.getSingleResult();
    }

    @Override
    public StudentEntity find(Integer id) {
        return em.find(StudentEntity.class, id);
    }

    @Override
    public void add(StudentEntity entity) {
        em.persist(entity);
    }


    @Override
    public void update(StudentEntity entity) {
        em.merge(entity);
    }

    @Override
    public void remove(StudentEntity entity) {
        em.remove(em.merge(entity));
    }


}
