package vn.titv.spring.hibernate.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import vn.titv.spring.hibernate.entity.Course;
import vn.titv.spring.hibernate.entity.Teacher;

import java.util.List;

@Repository
public class CourseDAOImpl implements CourseDAO{
    private EntityManager entityManager;

    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> findCourseByTeacherId(int id) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery("from Course where teacher.id=:id", Course.class);
        query.setParameter("id",id);
        //execute query
        List<Course> courses = query.getResultList();
        return courses;
    }
}
