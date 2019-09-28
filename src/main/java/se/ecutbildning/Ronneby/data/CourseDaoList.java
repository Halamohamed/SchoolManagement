package se.ecutbildning.Ronneby.data;

import se.ecutbildning.Ronneby.exeptions.ResourceExist;
import se.ecutbildning.Ronneby.exeptions.ResourceNotExist;
import se.ecutbildning.Ronneby.model.Course;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoList implements CourseDao {

    private static List<Course> courses;

    public CourseDaoList() {
        courses = new ArrayList<>();
    }

    @Override
    public Course saveCourse(Course course) throws ResourceExist {
        if(!courses.contains(course)){
            if (courses.contains(course.getId())){
                throw new ResourceExist("this course id is exist!");
            }
            courses.add(course);
            System.out.println("course is saved.");
            return course;
        }
        throw new ResourceExist("This course is already exist! " + course);
    }

    @Override
    public Course findById(int id) throws ResourceNotExist {
        for(Course course : courses){
            if(id==course.getId()){
                return course;
            }
        }
        throw new ResourceNotExist("This course id " + id  + " is not exist! " );
    }

    @Override
    public List<Course> findByName(String name) {
        List <Course> temp = new ArrayList<>();
        for(Course course : courses){
            if(name.equals(course.getCourseName())){
                temp.add(course);
            }
        }
        System.out.println(temp.size() + " course/s found by name " + name);
        return temp;
    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        List <Course> temp = new ArrayList<>();
        for(Course course : courses){
            if(date.equals(course.getStartDate())){
                temp.add(course);
            }
        }
        System.out.println(temp.size() + " course/s found by date " + date);
        return temp;
    }

    @Override
    public List<Course> findAll() {

        return courses;
    }

    @Override
    public boolean removeCourse(Course course) throws ResourceNotExist {
        for(Course course1 : courses){
            if(course1.equals(course)){
                courses.remove(course1);
                System.out.println("course " + course + " is removed.");
                return true;
            }
        }
        throw new ResourceNotExist("Course not found!");
    }
}
