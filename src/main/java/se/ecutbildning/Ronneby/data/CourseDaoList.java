package se.ecutbildning.Ronneby.data;

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
    public Course saveCourse(Course course) {
        if(!courses.contains(course)){
            courses.add(course);
            return course;
        }
        return null;
    }

    @Override
    public Course findById(int id) {
        for(Course course : courses){
            if(id==course.getId()){
                return course;
            }
        }
        return null;
    }

    @Override
    public List<Course> findByName(String name) {
        List <Course> temp = new ArrayList<>();
        for(Course course : courses){
            if(name.equals(course.getCourseName())){
                temp.add(course);
            }
        }
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
        return temp;
    }

    @Override
    public List<Course> findAll() {

        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        for(Course course1 : courses){
            if(course1.equals(course)){
                courses.remove(course1);
                return true;
            }
        }
        return false;
    }
}
