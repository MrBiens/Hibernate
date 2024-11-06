package vn.titv.spring.hibernate.dao;

import vn.titv.spring.hibernate.entity.Course;
import vn.titv.spring.hibernate.entity.Student;

public interface StudentDAO<T> {

    public void save(T student);

    public Student findStudentById(int id);
    public Student findStudentCourseAndByStudentId(int id);

}
