package se.ecutbildning.Ronneby.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
import se.ecutbildning.Ronneby.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoListTest {

    Student peter;
    Student khalifa;
    Student hala;
    StudentDaoList studentList;


    @Before
    public void setUp() throws ResourceExist {
        peter = new Student("Peter", "peter@ec.com","Hemvägen 1");
        studentList = new StudentDaoList();
        khalifa = new Student("khalifa","khalifa@ec.com","halagatan");
        studentList.saveStudent(peter);
        studentList.saveStudent(khalifa);



    }
    @Test
    public  void testSaveStudent() throws ResourceExist {
        khalifa = new Student("khalifa","khalifa@ec.com","halagatan");
        //studentList.saveStudent(khalifa);
        Assert.assertEquals(khalifa,studentList.saveStudent(khalifa));
        Assert.assertEquals("khalifa", khalifa.getName());
        Assert.assertEquals("khalifa@ec.com", khalifa.getEmail());
        Assert.assertEquals("halagatan",khalifa.getAddress());
    }
    @Test
    public void testFindByEmail() throws ResourceNotExist {
        Assert.assertEquals(peter,studentList.findByEmail("peter@ec.com"));
        Assert.assertEquals("peter@ec.com", peter.getEmail());
    }

    @Test
    public void testFindByName(){
        List<Student> expected = new ArrayList<>();
        expected.add(peter);
        Assert.assertEquals(expected,studentList.findByName("peter"));
        Assert.assertEquals("Peter", peter.getName());
        //System.out.println(studentList.findAll());
    }

    @Test
    public void testFindById() throws ResourceNotExist {
        Assert.assertEquals(peter,studentList.findById(peter.getId()));
    }

    @Test
    public void testFindAllStudents(){
        List<Student> expected = new ArrayList<>();
        expected.add(peter);
        expected.add(khalifa);
        Assert.assertEquals(expected, studentList.findAll());

    }

    @Test
    public void testDeleteStudent() throws ResourceNotExist {
        Assert.assertTrue(studentList.deleteStudent(khalifa));
        System.out.println(studentList.findAll());
    }

}
