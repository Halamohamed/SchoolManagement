package se.ecutbildning.Ronneby.data;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.ecutbildning.Ronneby.data.CourseDaoList;
import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
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
    public void setUp() throws ResourceExist {
        khalifa = new Student("Khalifa","khalifa@ecutb.se","khalifagatan");
        java = new Course(1,"java", LocalDate.parse("2019-08-19"),6,new ArrayList<Student>());
        courseDaoList = new CourseDaoList();
        javaScript19 = new Course(3,"javaScript", LocalDate.parse("2019-11-05"), 5, new ArrayList<>());
        javaScript18 = new Course(4,"javaScript", LocalDate.parse("2019-11-05"), 5, new ArrayList<>());

        courseDaoList.saveCourse(java);
        courseDaoList.saveCourse(javaScript19);
        courseDaoList.saveCourse(javaScript18);

    }
    @Test
    public void testSaveCourse() throws ResourceExist {
        Course html = new Course(2, "html", LocalDate.parse("2019-09-30"), 5, new ArrayList<>());

        List<Student> expected = new ArrayList<>();
        Assert.assertEquals(html, courseDaoList.saveCourse(html));
        //System.out.println(courseDaoList.findAll());
        Assert.assertEquals(2, html.getId());
        Assert.assertEquals("html", html.getCourseName());
        Assert.assertEquals(LocalDate.parse("2019-09-30"), html.getStartDate());
        Assert.assertEquals(5, html.getWeekDuration());
        Assert.assertEquals(expected, html.getStudents());
    }
    @Test
    public void testFindById() throws ResourceNotExist {

        Assert.assertEquals(java, courseDaoList.findById(1));
        Assert.assertEquals(1, java.getId());
    }
    @Test
    public void testFindByName(){
        List<Course> temp = new ArrayList<>();
        temp.add(java);
        Assert.assertEquals(temp, courseDaoList.findByName("java"));
        Assert.assertEquals("java",java.getCourseName());

    }

    @Test
    public void testFindByDate(){
        List<Course> temp = new ArrayList<>();

        temp.add(javaScript19);
        temp.add(javaScript18);
        Assert.assertEquals(temp, courseDaoList.findByDate(LocalDate.parse("2019-11-05")));
        Assert.assertEquals(LocalDate.parse("2019-11-05"), javaScript19.getStartDate());
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
    public void testRemoveCourse() throws ResourceNotExist {
        Assert.assertTrue(courseDaoList.removeCourse(javaScript18));
        System.out.println(courseDaoList.findAll());
    }

    @Test
    public void testRegisterStudent() throws ResourceNotExist, ResourceExist {
        hala = new Student("hala","hala@ec.se","halavägen");
        Assert.assertTrue(java.register(hala));
        Assert.assertTrue(java.unregister(hala));
    }
}
