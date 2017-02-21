package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface TagRepository extends CrudRepository<Tag,Integer> {

}
