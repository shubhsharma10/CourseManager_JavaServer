package com.example.springwebdevssharma.repositories;

import com.example.springwebdevssharma.models.Image;

import javax.transaction.Transactional;

@Transactional
public interface ImageRepository extends WidgetBaseRepository<Image>{
}
