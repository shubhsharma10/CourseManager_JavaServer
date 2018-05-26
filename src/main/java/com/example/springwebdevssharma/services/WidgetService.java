package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Widget;
import com.example.springwebdevssharma.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {

  @Autowired
  WidgetRepository widgetRepository;

  @GetMapping("/api/widget")
  public List<Widget> findAllWidgets() {
    return (List<Widget>)widgetRepository.findAll();
  }

  @PostMapping("/api/widget/save")
  public void saveAllWidgets(@RequestBody List<Widget> widgets) {
    widgetRepository.deleteAll();
    for(Widget widget: widgets){
      widgetRepository.save(widget);
    }
  }
}
