package vn.titv.spring.hibernate.dao;

import vn.titv.spring.hibernate.entity.Course;

import java.util.List;

public interface CourseDAO {

    public void save(Course course);

    List<Course> findCourseByTeacherId(int id);

}
