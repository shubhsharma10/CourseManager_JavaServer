package com.example.springwebdevssharma.services;

import com.example.springwebdevssharma.models.Topic;
import com.example.springwebdevssharma.models.Widget;
import com.example.springwebdevssharma.repositories.TopicRepository;
import com.example.springwebdevssharma.repositories.WidgetRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class WidgetService {
  @Autowired
  TopicRepository topicRepository;
  @Autowired
  WidgetRepository widgetRepository;

  @GetMapping("/api/widget")
  public List<Widget> findAllWidgets() {
    return (List<Widget>)widgetRepository.findAll();
  }

  @GetMapping("/api/widget/{wid}")
  public Widget findWidgetById(@PathVariable("wid") int widgetId) {
    Optional<Widget> data = widgetRepository.findById(widgetId);
    if(data.isPresent()) {
      return data.get();
    }
    return null;
  }

  @GetMapping("/api/topic/{tid}/widget")
  public List<Widget> findWidgetsForTopic(@PathVariable("tid") int topicId) {
    Optional<Topic> data = topicRepository.findById(topicId);
    if(data.isPresent()) {
      Topic topic = data.get();
      return topic.getWidgets();
    }
    return null;
  }

  @PostMapping("/api/topic/{tid}/widget")
  public Widget createWidget(@PathVariable("tid") int topicId, @RequestBody Widget newWidget) {
    Optional<Topic> data = topicRepository.findById(topicId);
    if(data.isPresent()) {
      Topic topic = data.get();
      newWidget.setTopic(topic);
      return widgetRepository.save(newWidget);
    }
    return null;
  }

  @PostMapping("/api/topic/{tid}/widget/save")
  public void saveWidgetsForTopic(@PathVariable("tid") int topicId, @RequestBody List<Widget> widgets) {
    Optional<Topic> data = topicRepository.findById(topicId);
    if(data.isPresent()) {
      Topic topic = data.get();
      List<Widget> widgetList = topic.getWidgets();
      // First remove the parent entity connection
      for(Widget widget: widgetList)
      {
        widget.setTopic(null);
      }
      // Now remove existing children
      topic.getWidgets().clear();

      // Add new widgets
      for(Widget widget: widgets)
      {
        widget.setTopic(topic);
        widgetRepository.save(widget);
      }
    }
  }

  @PutMapping("/api/widget/{wid}")
  public Widget updateWidget(@PathVariable("wid") int widgetId, @RequestBody Widget newWidget) {
    Optional<Widget> data = widgetRepository.findById(widgetId);
    if(data.isPresent()) {
      Widget widget = data.get();
      return widgetRepository.save(widget);
    }
    return null;
  }

  @DeleteMapping("/api/widget/{wid}")
  public void deleteWidget(@PathVariable("wid") int widgetId)
  {
    widgetRepository.deleteById(widgetId);
  }

  @PostMapping("/api/widget/save")
  public void saveAllWidgets(@RequestBody List<Widget> widgets) {
    widgetRepository.deleteAll();
    for(Widget widget: widgets){
      widgetRepository.save(widget);
    }
  }


}
