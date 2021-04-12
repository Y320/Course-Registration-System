# Course-Registration-System
The Course Registration System (CRS) has the following functions:  
1. Read, write, and store the following information:  
   
	Course name
	Course ID 
	Maximum number of students registered in the course
	Current number of registered students
	A list of names of students being registered in the given course
	Course instructor
	Course section number
	Course location
	A list of students being registered into the system
   
2. CRS allows Administrators (Admin) to do the following:
   
	Courses Management:
     
	1. Create a new course 
	2. Delete a course 
	3. Edit a course 
	4. Display information for a given course (by course ID) 
	5. Register a student 
	6. Exit 
	   
	Reports:
     
	1. View all courses 
	2. View all courses that are FULL (reached the maximum number of students) 
	3. Write to a file (FullCourses.txt in project folder) the list of course that are Full 
	4. View the names of the students being registered in a specific course 
	5. View the list of courses that a given student is being registered on
	6. Sort courses based on the current number of student registers (low to high)
	7. Exit
	   
3. CRS allows Students to do the following:
   
	Course Management
     
	1. View all courses 
	2. View all courses that are not FULL 
	3. Register on a course 
	4. Withdraw from a course
	5. View all courses that the current student is being registered in 
	6. Exit
	   
4. When first launched, the CRS will need to read MyUniversityCourses.csv to load the courses information. To avoid error, you will need to copy the correct file into project folder before first launching the program.
   
5. Please follow instructions and always Exit to save changes after use. Please note that all Exit options will directly save the changes and exit the program. Not properly exit the system may cause error when launching the CRS next time.
   
6. After first launch and a proper exit, changes will be serialized and write to file courseList.ser and stuList.ser. CRS will save the changes and you may continue work next time(CRS will load those two files if files exist). To initialize the system, delete courseList.ser and stuList.ser under the project root folder.
   
7. The functions in the CRS are mostly rely on student names. To avoid problem, please do not register multiple students with exactly same first name and last name. You may add a number to the last name to avoid the problem (for example Amber Markievicz1 and Amber Markievicz2).
   
8. Students must be registered by Admin with their names before using the CRS. Admin must register students before enroll/drop them from courses through Edit a course.
