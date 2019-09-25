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

        Student hala = new Student(1, "hala", "hala@ec.se","hemgatan 120");
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
        System.out.println("would you like to edit Student or Course (1/2)?");
    }

    private static void findStudentsAndCourses() {
    }

    private static void registerAndRemove() {
        System.out.println("would you like to register or remove (1/2)?");
    }

    private static void createNew() {
        boolean yes = false;
        List<Student> students = new ArrayList<>();
        scanner.next();
        System.out.println("enter Course id:");
        int id = scanner.nextInt();
        System.out.println("enter Course name:");
        String courseName = scanner.nextLine();
        System.out.println("enter Course start date:");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("enter Course week duration:");
        int weekDuration = scanner.nextInt();

        System.out.println("would you want to add a Student(yes/no)?");
        if(scanner.next().equalsIgnoreCase("y"))
         yes = true;
        while (yes) {
            System.out.println("enter Student id:");
            int studentId = scanner.nextInt();
            System.out.println("enter Student name:");
            String name = scanner.nextLine();
            System.out.println("enter Student email:");
            String email = scanner.next();
            System.out.println("enter Student address:");
            String address = scanner.nextLine();
            Student student = new Student(id, name, email, address);
            studentDaoList.saveStudent(student);
            students.add(student);
            System.out.println("would you want to add a Student (y/n)?");
            String yesOrNo = scanner.next();
            if(yesOrNo.equalsIgnoreCase("n")){
                yes=false;
            }
        }
            
            Course course = new Course(id, courseName,startDate,weekDuration,students );
            courseDaoList.saveCourse(course);
        
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
