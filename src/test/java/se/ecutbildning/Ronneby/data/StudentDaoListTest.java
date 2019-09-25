package se.ecutbildning.Ronneby.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutbildning.Ronneby.model.Student;

import java.util.ArrayList;

public class StudentDaoListTest {

    Student peter;
    Student khalifa;
    Student hala;
    StudentDaoList studentList;


    @Before
    public void setUp(){
        peter = new Student(5,"Peter", "peter@ec.com","Hemvägen 1");
        studentList = new StudentDaoList();
        studentList.saveStudent(peter);



    }
    @Test
    public  void testSaveStudent(){
        khalifa = new Student(6,"khalifa","khalifa@ec.com","halagatan");
        //studentList.saveStudent(khalifa);
        Assert.assertEquals(khalifa,studentList.saveStudent(khalifa));
        //System.out.println(studentList.findAll());
    }

}
