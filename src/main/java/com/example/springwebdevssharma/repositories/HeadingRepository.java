package com.example.springwebdevssharma.repositories;


import com.example.springwebdevssharma.models.Heading;

import javax.transaction.Transactional;

@Transactional
public interface HeadingRepository extends WidgetBaseRepository<Heading> {
}
