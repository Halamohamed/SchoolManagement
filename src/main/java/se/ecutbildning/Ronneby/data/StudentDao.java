package se.ecutbildning.Ronneby.data;

import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
import se.ecutbildning.Ronneby.model.Student;

import java.util.List;

public interface StudentDao {

    Student saveStudent(Student student) throws ResourceExist;
    Student findByEmail(String email) throws ResourceNotExist;
    List<Student> findByName(String name);
    Student findById(String id) throws ResourceNotExist;
    List<Student> findAll();
    boolean deleteStudent(Student student) throws ResourceNotExist;
}
