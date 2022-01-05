package ru.ifmo.sproing.short_link.service.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ifmo.sproing.short_link.entity.LinkEntity;
import ru.ifmo.sproing.short_link.model.Link;
import ru.ifmo.sproing.short_link.repository.LinkRepository;
import ru.ifmo.sproing.short_link.service.LinkService;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {
    private final LinkRepository linkRepository;


    @Override
    public Link createShortLink(Link link) {
        LinkEntity linkEntity = toEntity(link)
                .toBuilder()
                .id(null)
                .createdAt(LocalDateTime.now())
                .lifeTime(86400L)
                .build();

        log.debug("was created link {}", linkEntity);
        System.out.println(linkEntity);
        return toLink(linkRepository.save(linkEntity));
    }

    @Override
    public LinkEntity findShort(String shortLink) {
        Iterable<LinkEntity> listLinks = linkRepository.findAll();
        for (LinkEntity listLink : listLinks) {
            if(listLink.getShortLink().equals(shortLink)) return listLink;
        }
        return null;
    }

    @Override
    public LinkEntity findLong(String longLink) {
        Iterable<LinkEntity> listLinks = linkRepository.findAll();
        for (LinkEntity listLink : listLinks) {
            if(listLink.getLongLink().equals(longLink)) return listLink;
        }
        return null;
    }


    private boolean deleteShortLink(Link link) {
        return false;
    }

    private boolean checkShortLink(Link link) {
        return false;
    }
    private LinkEntity toEntity(Link link) {
        return new LinkEntity(link.getId(),
                link.getShortLink(),
                link.getLongLink(),
                link.getCreatedAt(),
                link.getLifeTime());
    }
    private Link toLink(LinkEntity linkEntity) {
        return new Link(linkEntity.getId(),
                linkEntity.getShortLink(),
                linkEntity.getLongLink(),
                linkEntity.getCreatedAt(),
                linkEntity.getLifeTime());
    }
}
