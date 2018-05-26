package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Course;
import com.example.springwebdevssharma.models.Module;
import com.example.springwebdevssharma.repositories.CourseRepository;
import com.example.springwebdevssharma.repositories.ModuleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "*")
public class ModuleService {
  @Autowired
  CourseRepository courseRepository;

  @Autowired
  ModuleRepository moduleRepository;

  @PostMapping("/api/course/{courseId}/module")
  public Module createModule(
          @PathVariable("courseId") int courseId,
          @RequestBody Module newModule) {
    Optional<Course> data = courseRepository.findById(courseId);

    if(data.isPresent()) {
      Course course = data.get();
      newModule.setCourse(course);
      return moduleRepository.save(newModule);
    }
    return null;
  }

  @GetMapping("/api/course/{courseId}/module")
  public List<Module> findAllModulesForCourse(
          @PathVariable("courseId") int courseId) {
    Optional<Course> data = courseRepository.findById(courseId);
    if(data.isPresent()) {
      Course course = data.get();
      return course.getModules();
    }
    return null;
  }

  @DeleteMapping("/api/module/{moduleId}")
  public void deleteModule(@PathVariable("moduleId") int moduleId)
  {
    moduleRepository.deleteById(moduleId);
  }

  @GetMapping("/api/module")
  public List<Module> findAllModules()
  {
    return (List<Module>) moduleRepository.findAll();
  }
}