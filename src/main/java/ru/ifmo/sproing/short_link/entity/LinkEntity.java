package ru.ifmo.sproing.short_link.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table("links")
public class LinkEntity {
    @Id
    @With
    private Long id;
    @Column("short_link")
    private String shortLink;
    @Column("long_link")
    private String longLink;
    @Column("created_at")
    private LocalDateTime createdAt;
    @Column("life_time")
    private Long lifeTime;
}
