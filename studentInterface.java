package Courses;

import java.io.IOException;

public interface studentInterface {
//	public void stuViewAllCourses();
	public void stuViewCoursesNotFull();
	public void stuRegisCourse() throws IOException;
	public void stuWithdrawCourse() throws IOException;
	public void studViewCoursesRegistered();

}
