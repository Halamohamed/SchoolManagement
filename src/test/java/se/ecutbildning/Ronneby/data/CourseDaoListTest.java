package se.ecutbildning.Ronneby.data;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutbildning.Ronneby.data.CourseDaoList;
import se.ecutbildning.Ronneby.model.Course;
import se.ecutbildning.Ronneby.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class CourseDaoListTest
{
    /**
     * Rigorous Test :-)
     */
    Course java;
    Course javaScript19;
    Course javaScript18;
    CourseDaoList courseDaoList;
    Student khalifa;
    Student hala;
    Student peter;
    @Before
    public void setUp(){
        khalifa = new Student(1,"Khalifa","khalifa@ecutb.se","khalifagatan");
        java = new Course(1,"java", LocalDate.parse("2019-08-19"),6,new ArrayList<Student>());
        courseDaoList = new CourseDaoList();
        javaScript19 = new Course(3,"javaScript", LocalDate.parse("2019-11-05"), 5, new ArrayList<>());
        javaScript18 = new Course(4,"javaScript", LocalDate.parse("2019-11-05"), 5, new ArrayList<>());

        courseDaoList.saveCourse(java);
        courseDaoList.saveCourse(javaScript19);
        courseDaoList.saveCourse(javaScript18);

    }
    @Test
    public void testSaveCourse() {
        Course html = new Course(2, "html", LocalDate.parse("2019-09-30"), 5, new ArrayList<>());

        Assert.assertEquals(html, courseDaoList.saveCourse(html));
        System.out.println(courseDaoList.findAll());
    }
    @Test
    public void testFindById(){
        Assert.assertEquals(java, courseDaoList.findById(1));
    }
    @Test
    public void testFindByName(){
        List<Course> temp = new ArrayList<>();
        temp.add(java);
        Assert.assertEquals(temp, courseDaoList.findByName("java"));
    }

    @Test
    public void testFindByDate(){
        List<Course> temp = new ArrayList<>();

        temp.add(javaScript19);
        temp.add(javaScript18);
        Assert.assertEquals(temp, courseDaoList.findByDate(LocalDate.parse("2019-11-05")));
    }
    @Test
    public void testFindAll(){
        List<Course> temp = new ArrayList<>();
        temp.add(java);
        temp.add(javaScript19);
        temp.add(javaScript18);
        Assert.assertEquals(temp, courseDaoList.findAll());

    }
    @Test
    public void testRemoveCourse(){
        Assert.assertTrue(courseDaoList.removeCourse(javaScript18));
        System.out.println(courseDaoList.findAll());
    }
}
