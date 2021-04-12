package Courses;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Admin extends User implements adminInterface, java.io.Serializable{
	
	public Admin() {
		super();
		this.username = "admin";
		this.password = "admin001";
	}

	@Override
	// method to create course. Input requires all elements of a course. 
	public void createCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String medium;

		System.out.println("Please enter the course name: ");
		this.courseName = in.readLine();

		System.out.println("Pleasr enter the course ID: ");
		this.courseID = in.readLine();

		int maxNum;
		while(true) {
			System.out.println("Please enter the maximum number of students of the course: ");
			medium = in.readLine();
			try {
				maxNum = Integer.parseInt(medium);
	            break;
	        } catch (Exception e) {
	        	System.out.println("Please enter a number: ");
	            e.printStackTrace();
	        }
		}
		
		int currentNum = 99999;
		while(true) {
			System.out.println("Please enter the current number(must be smaller than maximum number) of students of the course: ");
			medium = in.readLine();
			try {
				currentNum = Integer.parseInt(medium);
				if(currentNum < maxNum) {
					break;
				}
	        } catch (Exception e) {
	        	System.out.println("Please enter a number: ");
	            e.printStackTrace();
	        }
		}

		System.out.println("Please enter the instructor name of the course: ");
		this.courseInstructor = in.readLine();
		
		int courseSection;
		while(true) {
			System.out.println("Please enter the course section number: ");
			medium = in.readLine();
			try {
				courseSection = Integer.parseInt(medium);
	            break;
	        } catch (Exception e) {
	        	System.out.println("Please enter a number: ");
	            e.printStackTrace();
	        }
		}

		System.out.println("Enter the course location: ");
		this.courseLocation = in.readLine();

		Course c = new Course(this.courseName, this.courseID, maxNum, currentNum, this.courseInstructor, courseSection, this.courseLocation);
		courseList.add(c);

		System.out.println("The new course is added:");
		courseList.get(courseList.size()-1).adminPrint();
		
	}
	
	@Override
	// method to delete course by course name and course section#
	public void deleteCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the course name to delete the course: ");
		String courseName = in.readLine();
		
		int courseSection = super.csInput();
		
		int courseLoc = super.courseLoc(courseName, courseSection);
		
		if(courseLoc == -1) {
			System.out.println("Uable to find the course. Please check the information and try again.");
		} else {
			courseList.remove(courseLoc);
			System.out.println("The course " + courseName + " Section " + courseSection + " is deleted.");
		}
		
	}

	@Override
	// method to edit all elements of course except course name and course ID
	// Including edit functions of enroll/drop a student. 
	// For edit function, refer stuRegisCourse() and stuWithdrawCourse() under Class Student.
	public void editCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the course name to edit the course: ");
		String courseName = in.readLine();
		
		int courseSection = super.csInput();
		
		super.courseLoc(courseName, courseSection);
		
		int courseLoc = super.courseLoc(courseName, courseSection);
		
		if(courseLoc == -1) {
			System.out.println("Uable to find the course. Please check the information and try again.");
		} else {
			System.out.println("Please enter the number for edition.");
			System.out.println("1: edit maximun number of student.");
			System.out.println("2: edit current number of student.");
			System.out.println("3: edit instructor of the course.");
			System.out.println("4: edit the section of the course.");
			System.out.println("5: edit the location of the course.");
			System.out.println("6: edit the students enrolled in the course.");
			String editOption = in.readLine();
			String str = "";
			
			if (editOption.contentEquals("1")) {
				System.out.println("Please enter the new maximun number of students for Course " + courseName + " Section " + courseSection + " : ");
				str = in.readLine();
				int maxNum = Integer.parseInt(str);
				
				courseList.get(courseLoc).setMaxNum(maxNum);
				System.out.println("the new maximun number of students for Course " + courseName + " Section " + courseSection + " is changed to " + maxNum);
				
			} else if (editOption.contentEquals("2")) {
				System.out.println("Please enter the new current number of students for Course " + courseName + " Section " + courseSection + " : ");
				str = in.readLine();
				int currentNum = Integer.parseInt(str);
				
				courseList.get(courseLoc).setCurrentNum(currentNum);
				System.out.println("the new current number of student for Course " + courseName + " Section " + courseSection + " is changed to " + currentNum);
				
			} else if (editOption.contentEquals("3")) {
				System.out.println("Please enter the new instructor of Course " + courseName + " Section " + courseSection + " : ");
				String courseInstructor = in.readLine();
				
				courseList.get(courseLoc).setCourseInstructor(courseInstructor);
				System.out.println("the new instructor of Course " + courseName + " Section " + courseSection + " is changed to " + courseInstructor);
				
			} else if (editOption.contentEquals("4")) {
				System.out.println("Please enter the new section number of Course " + courseName + " Section " + courseSection + " : ");
				str = in.readLine();
				int courseSectionNum = Integer.parseInt(str);
				
				courseList.get(courseLoc).setCourseSection(courseSectionNum);;
				System.out.println("the new section number of Course " + courseName + " Section " + courseSection + " is changed to " + courseSectionNum);

			} else if (editOption.contentEquals("5")) {
				System.out.println("Please enter the new location of Course " + courseName + " Section " + courseSection + " : ");
				String courseLocation = in.readLine();
				
				courseList.get(courseLoc).setCourseLocation(courseLocation);
				System.out.println("the new location of Course " + courseName + " Section " + courseSection + " is changed to " + courseLocation);

			} else if (editOption.contentEquals("6")) {
				System.out.println("Please enter the student's first name: ");
				String firstname = in.readLine();
				System.out.println("Please enter the student's last name: ");
				String lastname = in.readLine();
				
				int stuLoc = -1;
				
				for (int i = 0; i < stuList.size(); i++) {
					String existFirstname = stuList.get(i).getFirstname();
					String existLastname = stuList.get(i).getLastname();
					if (existFirstname.contentEquals(firstname) && existLastname.contentEquals(lastname)) {
						System.out.println("Student Found.");
						stuLoc = i;
						break;
					}
				}
				
				if(stuLoc == -1) {
					System.out.println("Uable to find the student. Please check the information and try again. You may need to register student first.");
				} else {
					System.out.println("Please enter the number to Enroll the student or Drop the student from Course " + courseName + " Section " + courseSection);
					System.out.println("1: Enroll a student");
					System.out.println("2: Drop a student");
					String enrollOption = in.readLine();
					
					if (enrollOption.contentEquals("1")) {
						if(courseList.get(courseLoc).registeredStuList.size() == 0) {
							if(courseList.get(courseLoc).getCurrentNum() < courseList.get(courseLoc).getMaxNum()) {
								courseList.get(courseLoc).registeredStuList.add(new Student(firstname, lastname, username, password));
								int temp = courseList.get(courseLoc).getCurrentNum();
								temp++;
								courseList.get(courseLoc).setCurrentNum(temp);
								System.out.println("Student " + firstname + " " + lastname + ": Course "+ courseName + " Section " + courseSection + " registered.");
							}else {
								System.out.println("The course is closed. Uable to enroll the course.");
							}
						} else {
							for(int i = 0; i < courseList.get(courseLoc).registeredStuList.size(); i++) {
								if(firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname()) 
										&& lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())) {
									System.out.println("Student " + firstname + " " + lastname + "has already registered in course: " + courseName);
									break;
								} else if(((!firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname())) 
										|| (!lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())))
										&& (i == courseList.get(courseLoc).registeredStuList.size()-1)) {
									if(courseList.get(courseLoc).getCurrentNum() < courseList.get(courseLoc).getMaxNum()) {
										courseList.get(courseLoc).registeredStuList.add(new Student(firstname, lastname, username, password));
										int temp = courseList.get(courseLoc).getCurrentNum();
										temp++;
										courseList.get(courseLoc).setCurrentNum(temp);
										System.out.println("Student " + firstname + " " + lastname + "has already registered in course: " + courseName);
									}else {
										System.out.println("The course is closed. Uable to enroll the course.");
									}
								}
							}
						}
					} else if (enrollOption.contentEquals("2")) {
						if(courseList.get(courseLoc).registeredStuList.size() == 0) {
							System.out.println("Student " + firstname + " " + lastname + " is not registered in the course " + courseName + " Please check your information and try again.");
						} else {
							for(int i = 0; i < courseList.get(courseLoc).registeredStuList.size(); i++) {
								if(firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname())
										&& lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname())) {
									courseList.get(courseLoc).registeredStuList.remove(i);
									int temp = courseList.get(courseLoc).getCurrentNum();
									temp--;
									courseList.get(courseLoc).setCurrentNum(temp);
									System.out.println("Student " + firstname + " " + lastname + ": Course "+ courseName + " Section " + courseSection + " withdrew.");
									break;
								} else if(((!firstname.equals(courseList.get(courseLoc).registeredStuList.get(i).getFirstname()))
										|| (!lastname.equals(courseList.get(courseLoc).registeredStuList.get(i).getLastname()))) 
										&& (i == courseList.get(courseLoc).registeredStuList.size() - 1)) {
									System.out.println("Student " + firstname + " " + lastname + " is not registered in the course " + courseName + " Please check your information and try again.");
									
								}
							}
						}
					}
				}
			}
			
			courseList.get(courseLoc).adminPrint();
			
		}
		
	}

	@Override
	// method to print course info by course ID
	public void displayCourseInfo() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the course ID: ");
		String courseID = in.readLine();
		
		for(int i = 0; i < courseList.size(); i++) {
			String cid = courseList.get(i).getCourseID();
			if(cid.contentEquals(courseID)) {
				courseList.get(i).adminPrint();
			} else if(!cid.contentEquals(courseID) && (i == courseList.size()-1)) {
				System.out.println("Course not found. Please chech course ID and try again.");
			}
		}
	}

	@Override
	// method to add a student to CRS(stuList)
	// student must be add before admin enroll/drop classes for a student
	// student must be add before student login the CRS
	// when a registered student login in first time using his/her firstname & lastname, he/she will need to create a username and password.
	public void regisStudent() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the new student's first name: ");
		String firstname = in.readLine();
		System.out.println("Please enter the new student's last name: ");
		String lastname = in.readLine();
		
		// leave student username and password as null;
		String username = null;
		String password = null;

		Student newStudent = new Student(firstname, lastname, username, password);
		stuList.add(newStudent);
		System.out.println(stuList.get(stuList.size() - 1).getFirstname() + " " + stuList.get(stuList.size() - 1).getLastname() + " is registered into the system.");
	}

	@Override
	// A method that print all courses.
	public void viewAllCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).adminPrint();
		}
	}

	@Override
	// A method that print all courses that are full.
	public void viewFullCourses() {
		System.out.println("Full courses:");
		for (int i = 0; i < courseList.size(); i++) {
			if(courseList.get(i).getCurrentNum() >= courseList.get(i).getMaxNum()) {
				courseList.get(i).adminPrint();
			}
		}
	}

	@Override
	// A method that write all courses that are full in to file FullCourses.txt.
	public void writeFileFullCourse() {
		ArrayList<Course> fullCourses = new ArrayList<Course>();

		try {
			FileWriter fileWriter = new FileWriter("FullCourses.txt");
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < courseList.size(); i++) {
				if(courseList.get(i).getCurrentNum() >= courseList.get(i).getMaxNum()) {
					fullCourses.add(courseList.get(i));
				}
			}
			for (int i = 0; i < fullCourses.size(); i++) {
				String fullCourseInfo = fullCourses.get(i).adminPrint();
				bufferedWriter.write(fullCourseInfo);
				bufferedWriter.newLine();
			}
			bufferedWriter.close();
			System.out.println("Full courses wrote into FullCourses.txt");
			
		} catch (IOException e) {
			System.out.println("Error writing file.");
			e.printStackTrace();
		}
	}

	@Override
	// method to print enrolled student names of a specific course
	public void viewStudentsInCourse() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Please enter the course name: ");
		String courseName = in.readLine();
		
		int courseSection = super.csInput();
		
		int courseLoc = super.courseLoc(courseName, courseSection);
		
		String stuNames = "";
		for (int i = 0; i < courseList.get(courseLoc).registeredStuList.size(); i++) {
			String firstname = courseList.get(courseLoc).registeredStuList.get(i).getFirstname();
			String lastname = courseList.get(courseLoc).registeredStuList.get(i).getLastname();
			stuNames = stuNames + firstname+ " " + lastname + "; ";
		}
		System.out.println("The students in " + courseName + " :");
		System.out.println(stuNames);
	}

	@Override
	// method to print all courses that a student enrolled in
	public void viewCoursesInStudent() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter the student's first name: ");
		String stuFirstname = in.readLine();
		System.out.println("Please enter the student's last name:");
		String stuLastname = in.readLine();
		
		System.out.println("The student " + stuFirstname + " " + stuLastname + " is registered in following coures:");
		for(int i = 0; i < courseList.size(); i++) {
			for(int j = 0; j < courseList.get(i).registeredStuList.size(); j++) {
				if(stuFirstname.equals(courseList.get(i).registeredStuList.get(j).getFirstname()) 
						&& stuLastname.equals(courseList.get(i).registeredStuList.get(j).getLastname())) {
					System.out.println(courseList.get(i).getCourseName() + " Section " + courseList.get(i).getCourseSection());
				}
			}
		}
	}

	@Override
	// Bubble sort, from low to high
	public void sortCourses() {
		for (int i = 0; i < courseList.size(); i++) {
			for (int j = 0; j < courseList.size() - i - 1; j++) {
				if (courseList.get(j).getCurrentNum() > courseList.get(j+1).getCurrentNum()) {
					Course temp = courseList.get(j);
					courseList.set(j, courseList.get(j+1));
					courseList.set(j+1, temp);
				}
			}
		}

		for (int i = 0; i < courseList.size(); i++) {
			courseList.get(i).adminPrint();
		}
	}
	
	

}
