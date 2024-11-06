package vn.titv.spring.hibernate;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.titv.spring.hibernate.dao.CourseDAO;
import vn.titv.spring.hibernate.dao.TeacherDAO;
import vn.titv.spring.hibernate.dao.TeacherDetailDAO;
import vn.titv.spring.hibernate.entity.Course;
import vn.titv.spring.hibernate.entity.Teacher;
import vn.titv.spring.hibernate.entity.TeacherDetail;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(TeacherDAO teacherDAO, TeacherDetailDAO teacherDetailDAO, CourseDAO courseDAO){
		return runner -> {
			// createTeacher(teacherDAO);

			// findTeacherById(teacherDAO, 1);

			// findTeacherDetailById(teacherDetailDAO, 1);

			createCourses(teacherDAO, courseDAO);
		};
	}

	@Transactional
	private void createCourses(TeacherDAO teacherDAO, CourseDAO courseDAO) {
		// find a teacher
		Teacher teacher = new Teacher();
		teacher.setFirstName("Viễn Đông");
		teacher.setLastName("Phạm");
		teacher.setEmail("phamviendong@gmail.com");


		TeacherDetail teacherDetail = new TeacherDetail();
		teacherDetail.setGender(true);
		teacherDetail.setAddress("Đồng Nai - Việt Nam");
		teacherDetail.setYoutubeChannel("@viendong");
		teacher.setTeacherDetail(teacherDetail);

		// create courses
		Course c1 = new Course("Spring Hibernate", "Description", null, null);
		Course c2 = new Course("Fullstack React and Spring Boot", "Description", null, null);

		// add courses
		teacher.addCourse(c1);
		teacher.addCourse(c2);

		System.out.println("Updating teacher ... ");
		teacherDAO.update(teacher);
		System.out.println("Done!");

	}

	private void findTeacherDetailById(TeacherDetailDAO teacherDetailDAO, int id) {
		TeacherDetail teacherDetail = teacherDetailDAO.findTeacherDetailById(id);
		System.out.println("TeacherDetail: " + teacherDetail);
		System.out.println("Teacher: " + teacherDetail.getTeacher());
	}

	private void findTeacherById(TeacherDAO teacherDAO, int id) {
		Teacher teacher = teacherDAO.findTeacherById(id);
		System.out.println("Teacher: " + teacher);
		System.out.println("TeacherDetail: " + teacher.getTeacherDetail());
	}


	private void createTeacher(TeacherDAO teacherDAO) {
		Teacher teacher = new Teacher();
		teacher.setFirstName("Nhat Tung");
		teacher.setLastName("Le");
		teacher.setEmail("lenhattung@gmail.com");


		TeacherDetail teacherDetail = new TeacherDetail();
		teacherDetail.setGender(true);
		teacherDetail.setAddress("Warsaw - Poland");
		teacherDetail.setYoutubeChannel("@TITVvn");

		// associate the object
		teacher.setTeacherDetail(teacherDetail);


		// save
		System.out.println("Saving teacher .... " + teacher);
		teacherDAO.save(teacher);
		System.out.println("Done!");
	}


}
