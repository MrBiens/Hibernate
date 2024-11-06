package vn.titv.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.titv.spring.hibernate.entity.Teacher;
import vn.titv.spring.hibernate.entity.TeacherDetail;

@Repository
public class TeacherDetailDAOImpl implements TeacherDetailDAO{
    private EntityManager entityManager;

    @Autowired
    public TeacherDetailDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(TeacherDetail teacherDetail) {
        entityManager.persist(teacherDetail);
    }

    @Override
    public TeacherDetail findTeacherDetailById(int id) {
        return entityManager.find(TeacherDetail.class, id);
    }

    @Override
    @Transactional
    public void deleteTeacherDetailById(int id) {
        // find
        TeacherDetail teacherDetail =  entityManager.find(TeacherDetail.class, id);
        // delete
        entityManager.remove(teacherDetail);
    }
}
