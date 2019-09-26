package se.ecutbildning.Ronneby;

import se.ecutbildning.Ronneby.data.CourseDaoList;
import se.ecutbildning.Ronneby.data.StudentDaoList;
import se.ecutbildning.Ronneby.model.Course;
import se.ecutbildning.Ronneby.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static StudentDaoList studentDaoList;
    private static CourseDaoList courseDaoList;
    static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args )
    {
        studentDaoList = new StudentDaoList();
        courseDaoList = new CourseDaoList();

        Student hala = new Student( "hala", "hala@ec.se","hemgatan 120");
        while (true) {
            System.out.println("School Management System");
            menu();
            int selection = scanner.nextInt();
            switch (selection){
                case 1:
                    createNew();
                    break;
                case 2:
                    registerAndRemove();
                    break;
                case 3:
                    findStudentsAndCourses();
                    break;
                case 4:
                    editStudentsAndCourses();
                    break;
                case 5:
                    System.out.println(studentDaoList.findAll());
                    break;
                case 6:
                    System.out.println(courseDaoList.findAll());
                    break;
                default:
                    System.out.println("ops! something wrong");
            }
        }
    }

    private static void editStudentsAndCourses() {
        System.out.println("would you like to edit Student or Course (s/c)?");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("s")){
            editStudent();
        }else if(answer.equalsIgnoreCase("c")){
            editcourse();
        }
    }

    private static void editcourse() {
        System.out.println("enter course id:");
        int id = scanner.nextInt();
        Course course = courseDaoList.findById(id);
        if(course != null){
            courseDaoList.removeCourse(course);
            System.out.println(course);
            System.out.println("enter name");
            String name = scanner.nextLine();
            System.out.println("enter start date");
            LocalDate date = LocalDate.parse(scanner.next());

            courseDaoList.saveCourse(course);
        }else
            System.out.println("Course not found");

    }

    private static void editStudent() {
        System.out.println("enter student id:");
        String id = scanner.next();
        Student student = studentDaoList.findById(id);
        if(student != null){
            studentDaoList.deleteStudent(student);
            System.out.println(student);
            System.out.println("enter name");
            String name = scanner.nextLine();
            System.out.println("enter email");
            String email = scanner.next();
            System.out.println("enter address");
            String address = scanner.next();
            studentDaoList.saveStudent(new Student(name,email,address));
        }else
            System.out.println("Student not found");

    }

    private static void findStudentsAndCourses() {
        System.out.println("would you want to find course or student(c/s)?");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("c")){
            System.out.println(" find course by: \t(1)id \t(2)name \t(3)start time ");
            int select = scanner.nextInt();
            switch (select){
                case 1:
                    System.out.println("Enter course id:");
                    int courseId = scanner.nextInt();
                    System.out.println(courseDaoList.findById(courseId));
                    break;
                case 2:
                    System.out.println("Enter course name:");
                    String courseName = scanner.next();
                    System.out.println(courseDaoList.findByName(courseName));
                    break;
                case 3:
                    System.out.println("Enter course start date:");
                    LocalDate date = LocalDate.parse(scanner.next());
                    System.out.println(courseDaoList.findByDate(date));
                    break;
                default:
                    System.out.println("wrong choice");
            }
        }else if(answer.equalsIgnoreCase("s")){
            System.out.println(" find student by: \t(1)id \t(2)name \t(3)email ");
            int select = scanner.nextInt();
            switch (select){
                case 1:
                    System.out.println("Enter student id:");
                    String studentId = scanner.nextLine();
                    System.out.println(studentDaoList.findById(studentId));
                    break;
                case 2:
                    System.out.println("Enter student name:");
                    String studentName = scanner.next();
                    System.out.println(studentDaoList.findByName(studentName));
                    break;
                case 3:
                    System.out.println("Enter student email:");
                    String email =scanner.next();
                    System.out.println(studentDaoList.findByEmail(email));
                    break;
                default:
                    System.out.println("wrong choice");
            }

        }else
            System.out.println("ops!");
    }

    private static void registerAndRemove() {
        System.out.println("would you like to register or remove (1/2)?");
        int answer = scanner.nextInt();
        if(answer == 1){
            registerStudentToCourse();
        }
        else if(answer == 2){
            removeStudentFromCourse();
        }

    }

    private static void removeStudentFromCourse() {
        System.out.println("Enter Course Id you want to unregister Student from it:");
        int courseId = scanner.nextInt();
        Course course = courseDaoList.findById(courseId);
        if(course != null){
            List<Student> students = enterStudent();
            for (Student student: students
            ) {
                course.unregister(student);
            }
        }else System.out.println("course not found");
    }

    private static void registerStudentToCourse() {
        System.out.println("Enter Course Id you want to register Student to it:");
        int courseId = scanner.nextInt();
        Course course = courseDaoList.findById(courseId);
        if(course != null){
        List<Student> students = enterStudent();
            for (Student student: students
                 ) {
                course.register(student);
            }

        }else System.out.println("course not found");
    }

    private static void createNew() {
        boolean yes = false;
        List<Student> students = new ArrayList<>();
        System.out.println("enter Course id:");
        int id = scanner.nextInt();
        System.out.println("enter Course name:");
        String courseName = scanner.next();
        System.out.println("enter Course start date:");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("enter Course week duration:");
        int weekDuration = scanner.nextInt();

        System.out.println("would you want to add a Student(y/n)?");
        if(scanner.next().equalsIgnoreCase("y"))
        students = enterStudent();
            Course course = new Course(id, courseName,startDate,weekDuration,students );
            courseDaoList.saveCourse(course);
        
    }

    private static List<Student> enterStudent() {
        boolean yes = true;
        List<Student> students = new ArrayList<>();
        while (yes) {
            System.out.println("enter Student name:");
            String name = scanner.next();
            System.out.println("enter Student email:");
            String email = scanner.next();
            System.out.println("enter Student address:");
            String address = scanner.next();
            Student student = new Student( name, email, address);
            studentDaoList.saveStudent(student);
            students.add(student);
            System.out.println("would you want to add a Student (y/n)?");
            String yesOrNo = scanner.next();
            if(yesOrNo.equalsIgnoreCase("n")){
                yes=false;
            }
        }return students;
    }

    private static void menu() {
        System.out.println("select a number for your choice : ");
        System.out.println("(1)Create new Courses and Students");
        System.out.println("(2)Register and remove Students to/from Courses");
        System.out.println("(3)Finding Courses and Students in various ways");
        System.out.println("(4)Edit Courses and Students ");
        System.out.println("(5)Get all Students ");
        System.out.println("(6)Get all Courses ");
    }
}
