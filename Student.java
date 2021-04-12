package Courses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Student extends User implements studentInterface, java.io.Serializable{

	public Student(String firstname, String lastname, String username, String password) {
		super(firstname, lastname, username, password);
	}
	
	//Encapsulation ex
	private String courseNameIn = "";

	@Override
	// A method that print all courses.
	public void viewAllCourses() {
		for(int i = 0; i < courseList.size(); i++) {
			courseList.get(i).stuPrint();
		}
	}

	@Override
	// A method that print all courses that are not full.
	public void stuViewCoursesNotFull() {
		for(int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getMaxNum() > courseList.get(i).getCurrentNum()) {
				courseList.get(i).stuPrint();
			}
		}
	}

	@Override
	// A method that allows student enroll into a specific course by entering course name and course section number
	// Here we assume that students can only register courses for themselves.
	// The Req 04 says that student need to enter their names.
	// However, this doesn't make any sense as the CRS tracks courses registration only by student names.
	// Entering names will allow student A to register and withdraw courses for student B.
	// Therefore, that requirement of entering student name is not implemented here.
	// At the same time, the registered name is by default the name of the student who logged in the system.
	public void stuRegisCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the course name: ");
		courseNameIn = in.readLine();
		
		int courseSection = super.csInput();
		
		int courseLoc = super.courseLoc(courseNameIn, courseSection);
		
		if(courseLoc == -1) {
			System.out.println("Uable to find the course. Please check the information and try again.");
		} else {
			// corner case when there is no stu registered in the course yet
			if(courseList.get(courseLoc).registeredStuList.size() == 0) {
				if(courseList.get(courseLoc).getCurrentNum() < courseList.get(courseLoc).getMaxNum()) {
					courseList.get(courseLoc).registeredStuList.add(new Student(firstname, lastname, username, password));
					int temp = courseList.get(courseLoc).getCurrentNum();
					temp++;
					courseList.get(courseLoc).setCurrentNum(temp);
					System.out.println("Course registered.");
				}else {
					System.out.println("The course is closed. Uable to enroll the course.");
				}
			} else {
				for(int i = 0; i < courseList.get(courseLoc).registeredStuList.size(); i++) {
					// check if stu already regis in course
					if(firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname()) 
							&& lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())) {
						System.out.println("You have already registered in course: " + courseNameIn);
						break;
					} else if(((!firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname())) 
							|| (!lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())))
							&& (i == courseList.get(courseLoc).registeredStuList.size()-1)) {
						if(courseList.get(courseLoc).getCurrentNum() < courseList.get(courseLoc).getMaxNum()) {
							User stu = new Student(firstname, lastname, username, password);
							courseList.get(courseLoc).registeredStuList.add(stu);
							int temp = courseList.get(courseLoc).getCurrentNum();
							temp++;
							courseList.get(courseLoc).setCurrentNum(temp);
							System.out.println("Course registered.");
						}else {
							System.out.println("The course is closed. Uable to enroll the course.");
						}
					}
				}
			}
			
		}
	}
	
	// A method that allows student to withdraw a specific course by entering course name and course section number
	// Entering full name is not require for similar reasons mentioned above in comments for stuRegisCourse().
	@Override
	public void stuWithdrawCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the course name: ");
		courseNameIn = in.readLine();
		
		int courseSection = super.csInput();
		
		int courseLoc = super.courseLoc(courseNameIn, courseSection);
		
		if(courseLoc == -1) {
			System.out.println("Uable to find the course. Please check the information and try again.");
		} else {
			if(courseList.get(courseLoc).registeredStuList.size() == 0) {
				System.out.println("You are not registered in the course " + courseNameIn + " Please check your information and try again.");
			} else {
				for(int i = 0; i < courseList.get(courseLoc).registeredStuList.size(); i++) {
					if(firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname())
							&& lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())) {
						courseList.get(courseLoc).registeredStuList.remove(i);
						int temp = courseList.get(courseLoc).getCurrentNum();
						temp--;
						courseList.get(courseLoc).setCurrentNum(temp);
						System.out.println("Course withdrew.");
						break;
					} else if(((!firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname()))
							|| (!lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname()))) 
							&& (i == courseList.get(courseLoc).registeredStuList.size() - 1)) {
						System.out.println("You are not registered in the course " + courseNameIn + " Please check your information and try again.");
						
					}
				}
			}
			
			
		}
		
	}

	@Override
	public void studViewCoursesRegistered() {
		System.out.println("Your registered courses:");
		for(int i = 0; i < courseList.size(); i++) {
			for(int j = 0; j < courseList.get(i).registeredStuList.size(); j++) {
				if(firstname.equals(courseList.get(i).registeredStuList.get(j).getFirstname()) 
						&& lastname.equals(courseList.get(i).registeredStuList.get(j).getLastname())) {
					courseList.get(i).stuPrint();
				}
			}
		}
	}

}
