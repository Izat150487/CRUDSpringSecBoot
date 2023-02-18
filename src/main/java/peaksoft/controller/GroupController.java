package peaksoft.controller;

import peaksoft.entity.Course;
import peaksoft.entity.Group;
import peaksoft.entity.Student;
import peaksoft.config.repository.GroupRepository;
import peaksoft.service.CourseService;
import peaksoft.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("groups")
public class GroupController {
    private final GroupService groupService;
    private final CourseService courseService;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupController(GroupService groupService, CourseService courseService, GroupRepository groupRepository) {
        this.groupService = groupService;
        this.courseService = courseService;
        this.groupRepository = groupRepository;
    }


    @GetMapping
    public String groups(Model model){
        model.addAttribute("groups",groupService.getAllGroups());
        return "group/groups";
    }

    @GetMapping("/addGroup")
    public String add(Model model){
        model.addAttribute("group",new Group());
        model.addAttribute("courses",courseService.getAllCourse());
        return "group/addGroup";
    }
    @PostMapping("/saveGroup")
    public String save(@ModelAttribute("group") Group group){
        groupService.addGroup(group,group.getCourseId());
        return "redirect:/groups";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        List<Course>courses = courseService.getAllCourse();
        Group group = groupService.getById(id);
        model.addAttribute("group",group);
        model.addAttribute("courses",courses);
        return "group/updateGroup";
    }
    @RequestMapping(value = "/{id}", method = {RequestMethod.PATCH, RequestMethod.GET})
    public String updateCourse9(@PathVariable("id") Long id,@ModelAttribute("group") Group group){
        groupService.updateGroup(id,group,group.getCourseId());
        return "redirect:/groups";
    }
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE, RequestMethod.GET})
    public String delete(@PathVariable("id") Long id){
        groupRepository.deleteById(id);
        return "redirect:/groups";
    }


    @GetMapping("/courses/{id}")
    public String courses(@PathVariable("id") Long id, Model model){
        List<Course>courses = groupService.getCoursesByGroupId(id);
        model.addAttribute("courses",courses);
        return "group/courses";
    }

    @GetMapping("/students/{id}")
    public String students(@PathVariable("id") Long id, Model model,@ModelAttribute("student")Student student){
        List<Student>students = groupService.getStudentsByGroupId(id);
        model.addAttribute("group",groupService.getById(id));
        model.addAttribute("students",students);
        return "group/students";
    }

    @GetMapping("/search/{id}")
    public String search(@PathVariable("id") Long id, Model model,@RequestParam("name")String name){
        List<Student>students = groupService.search(name,id);
        model.addAttribute("students",students);
        return "group/search";
    }

}
