package se.ecutbildning.Ronneby.data;

import se.ecutbildning.Ronneby.model.Student;

import java.util.List;

public interface StudentDao {

    Student saveStudent(Student student);
    Student findByEmail(String email);
    List<Student> findByName(String name);
    Student findById(String id);
    List<Student> findAll();
    boolean deleteStudent(Student student);
}
