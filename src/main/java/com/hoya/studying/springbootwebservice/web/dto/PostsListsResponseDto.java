package com.hoya.studying.springbootwebservice.web.dto;

import com.hoya.studying.springbootwebservice.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsListsResponseDto {
    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime modifiedDate;
    private final String formattedDate;

    public PostsListsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.formattedDate = this.modifiedDate.format(formatter);
    }
}

