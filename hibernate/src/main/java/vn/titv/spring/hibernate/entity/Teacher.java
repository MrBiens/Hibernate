package vn.titv.spring.hibernate.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name", length = 256)
    private String firstName;

    @Column(name="last_name", length = 256)
    private String lastName;

    @Column(name="email", length = 256)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_detail_id")
    private TeacherDetail teacherDetail;

    //Lazy sẽ có lỗi
    @OneToMany(fetch=FetchType.LAZY
            ,mappedBy = "teacher", cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    private List<Course> courses;


    public Teacher() {
    }

    public Teacher(String firstName, String lastName, String email, TeacherDetail teacherDetail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.teacherDetail = teacherDetail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TeacherDetail getTeacherDetail() {
        return teacherDetail;
    }

    public void setTeacherDetail(TeacherDetail teacherDetail) {
        this.teacherDetail = teacherDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\''  +
                '}';
    }

    public void addCourse(Course course){
        if(this.courses==null){
            this.courses = new ArrayList<>();
        }

        this.courses.add(course); // add course vào List courses
        course.setTeacher(this); // add ngược lại teacher vào course

    }
}
