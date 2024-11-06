package vn.titv.spring.hibernate.dao;


import vn.titv.spring.hibernate.entity.Teacher;

public interface TeacherDAO {

    public void save(Teacher teacher);

    public Teacher findTeacherById(int id);

    void deleteTeacherById(int id);

    void update(Teacher teacher);
}
