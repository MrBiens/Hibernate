package vn.titv.spring.hibernate;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.titv.spring.hibernate.dao.CourseDAO;
import vn.titv.spring.hibernate.dao.StudentDAO;
import vn.titv.spring.hibernate.dao.TeacherDAO;
import vn.titv.spring.hibernate.dao.TeacherDetailDAO;
import vn.titv.spring.hibernate.entity.Course;
import vn.titv.spring.hibernate.entity.Student;
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
	public CommandLineRunner commandLineRunner(TeacherDAO teacherDAO, TeacherDetailDAO teacherDetailDAO, CourseDAO courseDAO, StudentDAO<Student> studentDAO){
		return runner -> {
			// createTeacher(teacherDAO);

			// findTeacherById(teacherDAO, 1);

			// findTeacherDetailById(teacherDetailDAO, 1);

//			createCourses(teacherDAO, courseDAO);

//			findTeacherWithCourse(teacherDAO,1);

//			findTeacherWithCourse_Lazy(teacherDAO,courseDAO,3);


			// tối ưu code
//			findTeacher_Lazy_JoinFetch(teacherDAO,1); // tối ưu code

//			createCourseAndStudent(courseDAO,studentDAO);

			findCourseAndStudentByCourseId(courseDAO,7);

			findCourseAndStudentByStudentId(studentDAO,1);




		};

	}

	private void findCourseAndStudentByStudentId(StudentDAO<Student> studentDAO, int id) {
		Student student = studentDAO.findStudentCourseAndByStudentId(id);
		System.out.println("Student:"+student);

	}

	private void findCourseAndStudentByCourseId(CourseDAO courseDAO, int id) {
		Course course = courseDAO.findCourseStudentAndByCourseId(id);
		System.out.println("Course : "+course);

	}

	//many to many
	private void createCourseAndStudent(CourseDAO courseDAO, StudentDAO<Student> studentDAO) {
		Course course = new Course();
		course.setTitle("Lập trình Spring MVC kết hợp Spring Boot");

		Student student = new Student();
		student.setFirstName("SBIT");
		student.setLastName("MR");

		Student student2 = new Student();
		student2.setFirstName("TITV");
		student2.setLastName("VN");

		course.add(student);
		course.add(student2);


		courseDAO.save(course);

	}

	private void findTeacherWithCourse(TeacherDAO teacherDAO, int id) {
			Teacher teacherEAGER= teacherDAO.findTeacherById(id);
			System.out.println("Teacher is information"+teacherEAGER);
			System.out.println("List of course"+teacherEAGER.getCourses());
	}

	private void findTeacherWithCourse_Lazy(TeacherDAO teacherDAO,CourseDAO courseDAO, int id) {
		//find teacher by id
		Teacher teacherEAGER= teacherDAO.findTeacherById(id);
		System.out.println("Teacher is information"+teacherEAGER);
		//select course
		List<Course> courses = courseDAO.findCourseByTeacherId(teacherEAGER.getId());
		teacherEAGER.setCourses(courses);
		// print list courses
		System.out.println("List of course"+teacherEAGER.getCourses());
	}
	private void findTeacher_Lazy_JoinFetch(TeacherDAO teacherDAO, int id) {
		//find teacher by id
		Teacher teacherEAGER= teacherDAO.findTeacherByIdJoinFetch(id);
		System.out.println("Teacher is information"+teacherEAGER);

		//select course ( Unnecessary)
//		List<Course> courses = courseDAO.findCourseByTeacherId(teacherEAGER.getId());
//		teacherEAGER.setCourses(courses);

		// print list courses
		System.out.println("List of course"+teacherEAGER.getCourses());
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
