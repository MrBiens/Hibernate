package vn.titv.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.titv.spring.hibernate.entity.Teacher;

@Repository
public class TeacherDAOImpl implements TeacherDAO{
    private EntityManager entityManager;

    @Autowired
    public TeacherDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Teacher teacher) {
        entityManager.persist(teacher);
    }

    @Override
    public Teacher findTeacherById(int id) {
        return entityManager.find(Teacher.class, id);
    }

    @Override
    @Transactional
    public void deleteTeacherById(int id) {
        // find
        Teacher teacher = entityManager.find(Teacher.class, id);

        // delete
        entityManager.remove(teacher);
    }

    @Override
    @Transactional
    public void update(Teacher teacher) {
        entityManager.merge(teacher);
        entityManager.flush();
    }
}
