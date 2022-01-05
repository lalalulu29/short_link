package ru.ifmo.sproing.short_link.repository;

import org.springframework.data.repository.CrudRepository;
import ru.ifmo.sproing.short_link.entity.LinkEntity;

public interface LinkRepository extends CrudRepository<LinkEntity, Long> {
}
