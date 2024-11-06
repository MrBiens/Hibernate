package vn.titv.spring.hibernate.dao;

import vn.titv.spring.hibernate.entity.Teacher;
import vn.titv.spring.hibernate.entity.TeacherDetail;

public interface TeacherDetailDAO {
    public void save(TeacherDetail teacherDetail);

    public TeacherDetail findTeacherDetailById(int id);

    void deleteTeacherDetailById(int id);
}
