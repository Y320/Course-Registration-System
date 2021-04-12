package Courses;
import java.io.IOException;

public interface adminInterface {
	//Admin courses management
	public void createCourse() throws IOException;
	public void deleteCourse() throws IOException;
	public void editCourse() throws IOException;
	public void displayCourseInfo() throws IOException;
	public void regisStudent() throws IOException;
	
	//Admin courses reports
//	public void viewAllCourses();
	public void viewFullCourses();
	public void writeFileFullCourse();
	public void viewStudentsInCourse() throws IOException;
	public void viewCoursesInStudent() throws IOException;
	public void sortCourses();

}
