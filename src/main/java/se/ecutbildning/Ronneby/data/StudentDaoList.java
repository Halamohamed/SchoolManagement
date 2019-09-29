package se.ecutbildning.Ronneby.data;

import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
import se.ecutbildning.Ronneby.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {
    private  static List<Student> students;

    public StudentDaoList() {
        students = new ArrayList<>();
    }

    @Override
    public Student saveStudent(Student student) throws ResourceExist {
        if (!students.contains(student)){
            if(students.contains(student.getEmail()) ){
                System.out.println("this email is exist!");
            }
            students.add(student);
            return student;
        }
        throw new ResourceExist(student + " is already exist!");
    }

    @Override
    public Student findByEmail(String email) throws ResourceNotExist {
        for (Student student : students){
            if(email.equals(student.getEmail()))
                return student;
        }
        throw new ResourceNotExist("No student by this email " + email);
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> temp = new ArrayList<>();
        for (Student student : students){
            if (name.equalsIgnoreCase(student.getName())){
                temp.add(student);
            }
        }
        System.out.println( temp.size() + " student/s is found");
        return temp;
    }

    @Override
    public Student findById(String id) throws ResourceNotExist {
        for (Student student : students){
            if(id.equals(student.getId())){
                return student;
            }
        }
       throw new ResourceNotExist("No student by this id " + id + " found!");
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) throws ResourceNotExist {
        for (Student obj : students){
            if (student.equals(obj)){
                students.remove(obj);
                System.out.println("student is removed");
                return true;
            }
        }
        throw new ResourceNotExist("This student is not found");
    }
}
