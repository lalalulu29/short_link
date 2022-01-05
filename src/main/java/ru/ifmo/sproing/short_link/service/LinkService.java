package ru.ifmo.sproing.short_link.service;

import ru.ifmo.sproing.short_link.entity.LinkEntity;
import ru.ifmo.sproing.short_link.model.Link;

public interface LinkService {
    Link createShortLink(Link link);
    LinkEntity findShort(String shortLink);
    LinkEntity findLong(String longLink);
}
