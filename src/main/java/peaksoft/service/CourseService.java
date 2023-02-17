package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourse();
    void addCourse(Course course, Long companyId);
    void updateCourse(Long id, Course course,Long companyId);
    Course getById(Long id);
    void deleteCourse(Course course);
    List<Teacher>getTeachersByCourseId(Long id);
    List<Group>getGroupsByCourseId(Long id);

}
