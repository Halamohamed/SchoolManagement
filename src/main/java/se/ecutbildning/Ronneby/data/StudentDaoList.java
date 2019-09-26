package se.ecutbildning.Ronneby.data;

import se.ecutbildning.Ronneby.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {
    private  static List<Student> students;

    public StudentDaoList() {
        students = new ArrayList<>();
    }

    @Override
    public Student saveStudent(Student student) {
        if (!students.contains(student)){
            students.add(student);
            return student;
        }
        return null;
    }

    @Override
    public Student findByEmail(String email) {
        for (Student student : students){
            if(email.equals(student.getEmail()))
                return student;
        }
        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        List<Student> temp = new ArrayList<>();
        for (Student student : students){
            if (name.equalsIgnoreCase(student.getName())){
                temp.add(student);
            }
        }
        return temp;
    }

    @Override
    public Student findById(String id) {
        for (Student student : students){
            if(id.equals(student.getId())){
                return student;
            }
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {
        for (Student obj : students){
            if (student.equals(obj)){
                students.remove(obj);
                System.out.println("student is removed");
                return true;
            }
        }
        return false;
    }
}
