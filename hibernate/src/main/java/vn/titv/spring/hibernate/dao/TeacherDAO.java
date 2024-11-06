package vn.titv.spring.hibernate.dao;


import vn.titv.spring.hibernate.entity.Teacher;

public interface TeacherDAO {

    public void save(Teacher teacher);

    public Teacher findTeacherById(int id);

    public void deleteTeacherById(int id);

    public void update(Teacher teacher);

    public Teacher findTeacherByIdJoinFetch(int id);

}
