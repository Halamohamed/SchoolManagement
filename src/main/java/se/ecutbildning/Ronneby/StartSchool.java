package se.ecutbildning.Ronneby;

import se.ecutbildning.Ronneby.data.CourseDaoList;
import se.ecutbildning.Ronneby.data.StudentDaoList;
import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
import se.ecutbildning.Ronneby.model.Course;
import se.ecutbildning.Ronneby.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * school management system
 *
 */
public class StartSchool
{
    private static StudentDaoList studentDaoList;
    private static CourseDaoList courseDaoList;
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) {
        studentDaoList = new StudentDaoList();
        courseDaoList = new CourseDaoList();
        try {
            studentDaoList.saveStudent(new Student("hala", "hala@ec.se", "halavägen"));
            studentDaoList.saveStudent(new Student("peter","peter@ec.se","petervägen"));
            studentDaoList.saveStudent(new Student("khalifa","khalifa@ec.se","khalifavägen"));
            courseDaoList.saveCourse(new Course(1,"java",LocalDate.parse("2019-08-19"),6, Arrays.asList(new Student("hala", "hala@ec.se", "halavägen"))));
            courseDaoList.saveCourse(new Course(2, "html", LocalDate.parse("2019-09-30"),5,Arrays.asList(new Student("peter","peter@ec.se","petervägen"))));
        }catch (ResourceExist exist){
            System.out.println(exist.getMessage());
        }

        System.out.println("--------------------************--------------------");
        System.out.println("            WELCOME SCHOOL MANAGEMENT SYSTEM");
        System.out.println("--------------------************--------------------");
        while (true) {
            menu();
            try{
            int selection = Integer.parseInt(scanner.next());
            switch (selection){
                case 1:
                    createCourses();
                    break;
                case 2:
                    createStudents();
                    break;
                case 3:
                    registerStudentToCourse();
                    break;
                case 4:
                    removeStudentFromCourse();
                    break;
                case 5:
                    findingCourses();
                    break;
                case 6:
                    findingStudents();
                    break;
                case 7:
                    editCourse();
                    break;
                case 8:
                    editStudent();
                    break;
                case 9:
                    System.out.println(studentDaoList.findAll());
                    break;
                case 10:
                    System.out.println(courseDaoList.findAll());
                    break;
                case 11:
                    System.out.println("Thanks and Bye! ");
                    System.exit(0);
                    break;
                default:
                    System.out.println(" wrong input");
            }}catch (NumberFormatException ex){
                System.out.println(ex.getMessage());
            }
        }

    }

    /**
     * edit course
     */
    private static void editCourse() {
        try {
            int id = enterCourseId();
            Course course = courseDaoList.findById(id);
            if (course != null) {
                System.out.println("select what you want to edit (1)name (2)start date (3)week duration");
                int select = Integer.parseInt(scanner.next());
                switch (select) {
                    case 1:
                        String name = enterStudentName();
                        for (Course c : courseDaoList.findAll()){
                            if(c.getId() == id){
                                course.setCourseName(name);
                            }
                        }
                        break;
                    case 2:
                        LocalDate date = enterDate();
                        for (Course c : courseDaoList.findAll()){
                            if(c.getId() == id){
                                course.setStartDate(date);
                            }
                        }
                        break;
                    case 3:
                        int duration = enterDuration();
                        for (Course c : courseDaoList.findAll()){
                            if(c.getId() == id){
                                course.setWeekDuration(duration);
                            }
                        }
                        break;
                    default:
                        System.out.println("ops! wrong selection");
                }
                System.out.println(course + " edited");
            } else
                System.out.println("Course not found");
        }catch (ResourceNotExist |NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * edit student
     */
    private static void editStudent()  {
        try {
            String studentEmail = enterEmail();
            Student student = studentDaoList.findByEmail(studentEmail);
            if (student != null) {
                System.out.println("select what you want to edit (1)name (2)email (3)address");
                int select = Integer.parseInt(scanner.next());
                switch (select) {
                    case 1:
                        String name = enterStudentName();
                        for (Student s : studentDaoList.findAll()){
                            if(s.getEmail().equals(studentEmail))
                            student.setName(name);
                        }
                        break;
                    case 2:
                        String email = enterEmail();
                        for (Student s : studentDaoList.findAll()){
                            if(s.getEmail().equals(studentEmail))
                                student.setEmail(email);
                        }
                        break;
                    case 3:
                        String address = enterAddress();
                        for (Student s : studentDaoList.findAll()){
                            if(s.getEmail().equals(studentEmail))
                                student.setAddress(address);
                        }
                        break;
                    default:
                        System.out.println("ops! wrong selection");
                }System.out.println(student + " edited.");
            } else
                System.out.println("Student not found");
        }catch (ResourceNotExist | NumberFormatException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     * find  courses
     */
    private static void findingCourses()  {
        try {
                System.out.println(" find course by: \t(1)id \t(2)name \t(3)start time ");
                int select = Integer.parseInt(scanner.next());
                switch (select) {
                    case 1:
                        int courseId = enterCourseId();
                        System.out.println(courseDaoList.findById(courseId));
                        break;
                    case 2:
                        String courseName = enterCourseName();
                        System.out.println(courseDaoList.findByName(courseName));
                        break;
                    case 3:
                        LocalDate date = enterDate();
                        System.out.println(courseDaoList.findByDate(date));
                        break;
                    default:
                        System.out.println("wrong choice");
                }
        }catch (ResourceNotExist| NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * find  students
     */
    private static void findingStudents(){
        try{
            System.out.println(" find student by: \t(1)id \t(2)name \t(3)email ");
            int select = Integer.parseInt(scanner.next());
            switch (select) {
                case 1:
                    String studentId = enterStudentId();
                    System.out.println(studentDaoList.findById(studentId));
                    break;
                case 2:
                    String studentName = enterStudentName();
                    System.out.println(studentDaoList.findByName(studentName));
                    break;
                case 3:
                    String email = enterEmail();
                    System.out.println(studentDaoList.findByEmail(email));
                    break;
                default:
                    System.out.println("wrong choice");
            }}catch (ResourceNotExist |NumberFormatException ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * unregister student from course
     */
    private static void removeStudentFromCourse()  {
        try {
            System.out.println("to unregister Student first ");
            int courseId = enterCourseId();
            Course course = courseDaoList.findById(courseId);
            if (course != null) {
                String email = enterEmail();
                Student student =studentDaoList.findByEmail(email);
                if(student != null){
                    course.unregister(student);
                }
                else {System.out.println("Student not found");}
            } else {System.out.println("course not found");}
        }catch (ResourceNotExist ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * register student/s to course
     */
    private static void registerStudentToCourse()  {
        try {
            System.out.print("to register Student first ");
            int courseId = enterCourseId();
            Course course = courseDaoList.findById(courseId);
            if (course != null) {
                List<Student> students = createStudents();
                for (Student student : students) {
                    if(!course.getStudents().contains(student.getEmail()))
                    course.register(student);
                    if(course.getStudents().contains(student.getEmail()))
                        System.out.println("the student with email" + student.getEmail() + " exist");
                }

            } else System.out.println("course not found");
        }catch (ResourceExist | ResourceNotExist ex){
            System.out.println(ex.getMessage());
        }
    }

    /**
     * create new courses
     */
    private static void createCourses()  {
        boolean yes = true;
        List<Student> students = new ArrayList<>();
        try {
            while (yes) {
        int id = enterCourseId();
        String courseName = enterCourseName();
        LocalDate startDate = enterDate();
        int weekDuration = enterDuration();
            Course course = new Course(id, courseName, startDate, weekDuration, students);
            //if (!course.getStudents().contains(students))
            courseDaoList.saveCourse(course);

            System.out.println("would you like to create more courses (y/n)");
            String answer = scanner.next();
            switch (answer) {
                case "y":
                case "Y":
                    yes = true;
                    break;
                case "n":
                case "N":
                    yes = false;
                    break;
                default:
                    System.out.println("OPS! please enter y/n");
            }
            }
        }catch (ResourceExist exist){
            System.out.println(exist.getMessage());
        }
        
    }

    /**
     * @return list of students
     */
    private static List<Student> createStudents()  {
        boolean yes = true;
        List<Student> students = new ArrayList<>();
        try {
        while (yes) {
            String name = enterStudentName();
            String email = enterEmail();
            String address = enterAddress();
            Student student = new Student( name, email, address);
            if(!studentDaoList.findAll().contains(student.getEmail())) {
                studentDaoList.saveStudent(student);
            }
            if (!students.contains(student)) {
                students.add(student);
            }
            System.out.println("would you want to add a Student (y/n)?");
            String yesOrNo = scanner.next();
            if(yesOrNo.equalsIgnoreCase("n")){
                yes=false;
            }
        }
        }catch (ResourceExist ex){
            System.out.println(ex.getMessage());
        }return students;
    }

    /**
     * @return student Id
     */
    private static String enterStudentId() {
        System.out.println("Enter student id:");
        String studentId = scanner.next();
        return studentId;
    }

    /**
     * @return student name
     */
    private static String enterStudentName() {
        System.out.println("enter student´s name:");
        String name = scanner.next();
        return name;
    }

    /**
     * @return email of student
     */
    private static String enterEmail() {
        System.out.println("enter student´s email:");
        String studentEmail = scanner.next();
        return studentEmail;
    }

    /**
     * @return address of student
     */
    private static String enterAddress() {
        System.out.println("enter Student address:");
        String address = scanner.next();
        return address;
    }

    /**
     * @return course Id
     */
    private static int enterCourseId() {
        int id = 0;
        System.out.println("enter Course id:");
        try {
             id = Integer.parseInt(scanner.next());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return id;
    }

    /**
     * @return name of course
     */
    private static String enterCourseName() {
        System.out.println("Enter course name:");
        String courseName = scanner.next();
        return courseName;
    }

    /**
     * @return  week duration to the course
     */
    private static int enterDuration() {
        int duration = 0;
        System.out.println("enter week duration:");
        try {
            duration = Integer.parseInt(scanner.next());
        }catch (NumberFormatException e){
            System.out.println(e.getMessage());
        }
        return duration;
    }

    /**
     * @return the start date to course
     */
    private static LocalDate enterDate() {
        System.out.println("enter course start date(yyyy-mm-dd):");
        String date = scanner.next();
        return LocalDate.parse(date);
    }


    /**
     * menu to select your choice in school system
     */
    private static void menu() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" MENU ");
        System.out.println("select a number for your choice : ");
        System.out.println("(1)Create new Courses          \t(2)Create new Students");
        System.out.println("(3)Register Student to Courses \t(4)unregister Student from Course");
        System.out.println("(5)Finding Courses             \t(6)Finding Students ");
        System.out.println("(7)Edit Courses                \t(8)Edit Students ");
        System.out.println("(9)Get all Students            \t(10)Get all Courses");
        System.out.println("(11)Exit ");
        System.out.println("-----------------------------------------------------------------");
    }
}
