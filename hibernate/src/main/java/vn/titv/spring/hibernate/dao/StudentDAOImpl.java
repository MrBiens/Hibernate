package vn.titv.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.titv.spring.hibernate.entity.Course;
import vn.titv.spring.hibernate.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO<Student>{
    private EntityManager  entityManager;

    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    public Student findStudentById(int id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public Student findStudentCourseAndByStudentId(int id) {
        TypedQuery<Student> query = entityManager.createQuery(
                "select s from Student s "
                        +" JOIN FETCH s.courses "
                        +"where s.id=:x"
                , Student.class);
        query.setParameter("x",id);
        return query.getSingleResult();
    }


}
