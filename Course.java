package Courses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Course implements java.io.Serializable{

	String courseName;
	String courseID;
	int maxNum;
	int currentNum;
	String courseInstructor;
	int courseSection;
	String courseLocation;
	ArrayList<User> registeredStuList = new ArrayList<User>();
	static ArrayList<Course> courseList = new ArrayList<Course>();
	static ArrayList<Student> stuList = new ArrayList<Student>();
	
	Course(){
		
	}
	
	public Course(String courseName, String courseID, int maxNum, int currentNum, String courseInstructor,
			int courseSection, String courseLocation) {
		this.courseName = courseName;
		this.courseID = courseID;
		this.maxNum = maxNum;
		this.currentNum = currentNum;
		this.courseInstructor = courseInstructor;
		this.courseSection = courseSection;
		this.courseLocation = courseLocation;
		this.registeredStuList = new ArrayList<User>();
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	public String getCourseInstructor() {
		return courseInstructor;
	}

	public void setCourseInstructor(String courseInstructor) {
		this.courseInstructor = courseInstructor;
	}

	public int getCourseSection() {
		return courseSection;
	}

	public void setCourseSection(int courseSection) {
		this.courseSection = courseSection;
	}

	public String getCourseLocation() {
		return courseLocation;
	}

	public void setCourseLocation(String courseLocation) {
		this.courseLocation = courseLocation;
	}

	public ArrayList<User> getRegisteredStuList() {
		return registeredStuList;
	}

	public void setRegisteredStuList(ArrayList<Student> registratedStuList) {
		this.registeredStuList = registeredStuList;
	}

	public static ArrayList<Course> getCourseList() {
		return courseList;
	}

	public static void setCourseList(ArrayList<Course> courseList) {
		Course.courseList = courseList;
	}
	
	public static ArrayList<Student> getStuList() {
		return stuList;
	}

	public static void setStuList(ArrayList<Student> stuList) {
		Admin.stuList = stuList;
	}
	
	// A helper method to print course info for admin (with student names)
	public String adminPrint() {
		String stuNames = "";
		

			for (int i = 0; i < registeredStuList.size(); i++) {
				String firstname = registeredStuList.get(i).getFirstname();
				String lastname = registeredStuList.get(i).getLastname();
				stuNames = stuNames + firstname+ " " + lastname + "; ";
			}
			System.out.println("Course: " + courseName + "\n" + "Course ID: " + courseID + "\n"
					+ "Maximum # of Students: " + maxNum + "\n" + "Current # of Students: " + currentNum
					+ "\n" + "Registered Students: " + stuNames + "\n" + "Instructor: " + courseInstructor + "\n"
					+ "Section: " + courseSection + "\n" + "Location: " + courseLocation);
			System.out.println("------------------------------------------------------------------------");
			return "Course: " + courseName + "\n" + "Course ID: " + courseID + "\n"
					+ "Maximum # of Students: " + maxNum + "\n" + "Current # of Students: " + currentNum
					+ "\n" + "Registered Students: " + stuNames + "\n" + "Instructor: " + courseInstructor + "\n"
					+ "Section: " + courseSection + "\n" + "Location: " + courseLocation + "\n";
	}
	
	// A helper method to print course info for student (without student names)
	public void stuPrint() {
		System.out.println("Course: " + courseName + "\n" + "Course ID: " + courseID + "\n" + "Maximum Students: "
				+ maxNum + "\n" + "Current Students: " + currentNum + "\n" + "Instructor: " + courseInstructor + "\n" 
				+ "Section: " + courseSection + "\n" + "Location: "+ courseLocation);
		System.out.println("------------------------------------------------------------------------");
	}
	
	// a helper method to return the input course section number.
	public int csInput() throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int courseSection;
		while(true) {
			System.out.println("Please enter the course section: ");
			String str = in.readLine();
			try {
				courseSection = Integer.parseInt(str);
	            break;
	        } catch (Exception e) {
	        	System.out.println("Please enter a number.");
	            e.printStackTrace();
	        }
		}
		return courseSection;
	}	
		
	// a helper method to find and return the course location on the array list based on course name and course section number.
	// return -1 if course not found
	public int courseLoc(String courseName, int courseSection){
		int courseLoc = -1;
		for (int i = 0; i < courseList.size(); i++) {
			String cn = courseList.get(i).getCourseName();
			int cs = courseList.get(i).getCourseSection();
			if (cn.contentEquals(courseName) && cs == courseSection) {
				System.out.println("Course Found.");
				courseLoc = i;
				break;
			}
		}
		return courseLoc;
	}			
	
	// helper method for serializing courseList
	public static void courseSerialization() {
		try {
			FileOutputStream fos = new FileOutputStream("courseList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(courseList);
			oos.close();
			fos.close();
			System.out.println("Course List Data serialization complete.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	// helper method for serializing stuList
	public static void stuSerialization() {
		try {
			FileOutputStream fos = new FileOutputStream("stuList.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(stuList);
			oos.close();
			fos.close();
			System.out.println("Student List Data serialization complete.");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}	
	
	// helper method for deserializing courseList
	public static void courseDeserialization() {
		try {
			FileInputStream fis = new FileInputStream("courseList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			courseList = (ArrayList<Course>) ois.readObject();
			ois.close();
			fis.close();
			System.out.println("Course List Data deserialization complete");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		}
	}
	
	// helper method for deserializing stuList
		public static void stuDeserialization() {
			try {
				FileInputStream fis = new FileInputStream("stuList.ser");
				ObjectInputStream ois = new ObjectInputStream(fis);

				stuList = (ArrayList<Student>) ois.readObject();
				ois.close();
				fis.close();
				System.out.println("Student List Data deserialization complete.");
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException c) {
				System.out.println("Class not found");
				c.printStackTrace();
			}
		}

	//Main is directly positioned here for the convenience of using all variables and methods.
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Admin admin = new Admin();
		
		File courseListFile = new File("courseList.ser");
		// First launch, "courseList.ser" does not exist, therefore read "MyUniversityCourses.csv" and tokenize it and store in arraylist
		if (!courseListFile.exists()) {
			courseListFile = new File("MyUniversityCourses.csv");
			
			try{
				FileReader filereader = new FileReader(courseListFile);
			    BufferedReader bufferedreader = new BufferedReader(filereader);
			    bufferedreader.close();
			    System.out.println("File Loaded.");
			}catch (FileNotFoundException e){
			    System.out.println("File Not Found. Please copy MyUniversityCourses.csv to the user.dir.");
			}catch (IOException e){
			    System.out.println("Error Reading File. Please delete the existing file and copy the MyUniversityCourses.csv to the user.dir.");
			}
			
			String fileToStr = new Scanner(courseListFile).useDelimiter("\\A").next();
			StringTokenizer strTokens = new StringTokenizer(fileToStr, ",\n");
			for(int i = 0; i < 8; i++) {
				strTokens.nextToken();
			}
			while (strTokens.hasMoreTokens()) {
					String courseName = strTokens.nextToken();
					String courseID = strTokens.nextToken();
					String maxNumStr = strTokens.nextToken();
					String trimmedMaxNumStr = maxNumStr.replace(" ", "");
					int maxNum = Integer.parseInt(trimmedMaxNumStr);
					String currentNumStr = strTokens.nextToken();
					String trimmedCurrentNumStr = currentNumStr.replace(" ", "");
					int currentNum = Integer.parseInt(trimmedCurrentNumStr);
					strTokens.nextToken();
					String courseInstructor = strTokens.nextToken();
					String courseSectionStr = strTokens.nextToken();
					String trimmedcourseSectionStr = courseSectionStr.replace(" ", "");
					int courseSection = Integer.parseInt(trimmedcourseSectionStr);
					String courseLocation = strTokens.nextToken();

					Course c = new Course(courseName, courseID, maxNum, currentNum, courseInstructor, courseSection, courseLocation);
					courseList.add(c);
			}
		} else {
			//after first launch, deserialization stored data from previous use
			courseDeserialization();
			
			File stuListFile = new File("stuList.ser");
			if (stuListFile.exists()) {
				stuDeserialization();
			}
		}
		
		// choose user type
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Course Registration System");
		System.out.println("Please enter numbers for operations.");
		System.out.println("1: Login as Admin.");
		System.out.println("2: Login as Student.");
		System.out.println("3: Exit.");
		String mainOption = in.readLine();
		
		// a boolean variable to control if the system is logged in/out
		boolean exit = false;
		
		// error message
		while (!mainOption.contentEquals("1") && !mainOption.contentEquals("2") && !mainOption.contentEquals("3")) {
			System.out.println("Please enter numbers for operations.");
			System.out.println("1: Login as Admin.");
			System.out.println("2: Login as Student.");
			System.out.println("3: Exit.");
			mainOption = in.readLine();
		}
		
		// admin login
		if (mainOption.contentEquals("1")) {

			System.out.println("Enter the Admin username:");
			String usernameIn = in.readLine();
			System.out.println("Enter the Admin password:");
			String passwordIn = in.readLine();

      // error message
			while (!usernameIn.contentEquals("admin") || !passwordIn.contentEquals("admin001")) {
				System.out.println("Username or Password incorrect. Please try again");
				System.out.println("Enter the Admin username:");
				usernameIn = in.readLine();
				System.out.println("Enter the Admin password:");
				passwordIn = in.readLine();	
			}
			// admin operations
			System.out.println("Admin account logged in.");
			while (!exit) {
				System.out.println("Please enter numbers for operations.");
				System.out.println("1: Courses Management");
				System.out.println("2. Courses Reports");
				System.out.println("3: Exit System");
				String adminMainOption = in.readLine();
				if (adminMainOption.contentEquals("1")) {

					System.out.println("Course Management");
					System.out.println("1: Create a new course");
					System.out.println("2: Delete a course");
					System.out.println("3: Edit a course");
					System.out.println("4: Display course information");
					System.out.println("5: Register a student");
					System.out.println("6: Exit System");
					String adminCMoption = in.readLine();

					if (adminCMoption.contentEquals("1")) {
						admin.createCourse();
					} else if (adminCMoption.contentEquals("2")) {
						admin.deleteCourse();
					} else if (adminCMoption.contentEquals("3")) {
						admin.editCourse();
					} else if (adminCMoption.contentEquals("4")) {
						admin.displayCourseInfo();
					} else if (adminCMoption.contentEquals("5")) {
						admin.regisStudent();
					} else if (adminCMoption.contentEquals("6")){
						System.out.println("Course Registration System Logged Out");
						exit = true;
						courseSerialization();
						stuSerialization();
					}

				} else if (adminMainOption.contentEquals("2")) {

					System.out.println("Course Reports");
					System.out.println("1: View all courses");
					System.out.println("2: View all courses that are Full");
					System.out.println("3: Write to a file the list of course that are Full");
					System.out.println("4: View the names of the students being registered in a specific course");
					System.out.println("5: View the list of courses that a given student is being registered on");
					System.out.println("6: Sort courses based on the current number of student");
					System.out.println("7: Exit");
					String adminCRoption = in.readLine();

					if (adminCRoption.contentEquals("1")) {
						admin.viewAllCourses();;
					} else if (adminCRoption.contentEquals("2")) {
						admin.viewFullCourses();
					} else if (adminCRoption.contentEquals("3")) {
						admin.writeFileFullCourse();;
					} else if (adminCRoption.contentEquals("4")) {
						admin.viewStudentsInCourse();
					} else if (adminCRoption.contentEquals("5")) {
						admin.viewCoursesInStudent();
					} else if (adminCRoption.contentEquals("6")) {
						admin.sortCourses();
					} else if (adminCRoption.contentEquals("7")) {
						System.out.println("Course Registration System Logged Out");
						exit = true;
						courseSerialization();
						stuSerialization();
					}

				} else {
					System.out.println("Course Registration System Logged Out");
					exit = true;
					courseSerialization();
					stuSerialization();
				}
			}
			
		} else if (mainOption.contentEquals("2")) {
			// student login
			System.out.println("Please enter your first name:");
			String firstnameIn = in.readLine();
			System.out.println("Please enter your last name:");
			String lastnameIn = in.readLine();
			
			// check if student is registered by admin through searching name
			int stuLoc = -1;
			while (stuLoc == -1) {
				for (int i = 0; i < stuList.size(); i++) {
					String existFirstname = stuList.get(i).getFirstname();
					String existLastname = stuList.get(i).getLastname();
					if (existFirstname.contentEquals(firstnameIn) && existLastname.contentEquals(lastnameIn)) {
						System.out.println("Student Found.");
						stuLoc = i;
						break;
					} else if (((!existFirstname.contentEquals(firstnameIn)) || (!existLastname.contentEquals(lastnameIn))) && (i == Admin.stuList.size() - 1)) {
						System.out.println("Unable to find your name in the system. Please try again.(Need to be registered as a Student by Admin)");
						System.out.println("Please enter your first name:");
						firstnameIn = in.readLine();
						System.out.println("Please enter your last name:");
						lastnameIn = in.readLine();
					}
				}
			}
			
			// set up username and password for first time user
			if ((stuList.get(stuLoc).getUsername() == null) || (stuList.get(stuLoc).getPassword() == null)) {
				System.out.println("You need to set up username and password before logining in.");
				System.out.println("Please set your username:");
				String usernameSet = in.readLine();
				System.out.println("Please set your password:");
				String passwordSet = in.readLine();
				stuList.get(stuLoc).setUsername(usernameSet);
				stuList.get(stuLoc).setPassword(passwordSet);
				System.out.println("Your username and password are set. Please continue login.");	
					
			}
			
			// student login
			System.out.println("Enter your Student username:");
			String usernameIn = in.readLine();
			System.out.println("Enter your Student password:");
			String passwordIn = in.readLine();
			
			// error message
			while (!usernameIn.contentEquals(stuList.get(stuLoc).getUsername()) && !passwordIn.contentEquals(stuList.get(stuLoc).getPassword())) {
				System.out.println("Username or Password incorrect. Please try again");
				System.out.println("Enter your Student username:");
				usernameIn = in.readLine();
				System.out.println("Enter your Student password:");
				passwordIn = in.readLine();	
			}

			
			System.out.println(firstnameIn + " " + lastnameIn + " " + usernameIn + " Student account logged in.");
			Student student = new Student(firstnameIn, lastnameIn, usernameIn, passwordIn);
			
			// student operations
			while (!exit) {
				System.out.println("Please enter numbers for operations.");
				System.out.println("1: View all courses");
				System.out.println("2: View all courses that are not Full");
				System.out.println("3: Register on a course");
				System.out.println("4: Withdraw from a course");
				System.out.println("5: View all courses that you registered in");
				System.out.println("6: Exit");
				String stuOption = in.readLine();

				if (stuOption.contentEquals("1")) {
					student.viewAllCourses();
				} else if (stuOption.contentEquals("2")) {
					student.stuViewCoursesNotFull();
				} else if (stuOption.contentEquals("3")) {
					student.stuRegisCourse();
				} else if (stuOption.contentEquals("4")) {
					student.stuWithdrawCourse();
				} else if (stuOption.contentEquals("5")) {
					student.studViewCoursesRegistered();
				} else if (stuOption.contentEquals("6")) {
					System.out.println("Course Registration System Logged Out");
					exit = true;
					courseSerialization();
					stuSerialization();}
			}
		} else if (mainOption.contentEquals("3")) {
			System.out.println("Course Registration System Logged Out");
			exit = true;
			courseSerialization();
			stuSerialization();}
		}
		
	
}
