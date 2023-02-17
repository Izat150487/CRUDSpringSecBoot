package peaksoft.repository;

import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select t from Teacher t join t.course course where course.id= :id")
    List<Teacher> getTeachersByCourseId(@Param("id") Long id);

    @Query("select g from Group g join g.course course where course.id= :id")
    List<Group>getGroupsByCourseId(@Param("id") Long id);
}