package com.hoya.studying.springbootwebservice.service.posts;

import com.hoya.studying.springbootwebservice.domain.posts.Posts;
import com.hoya.studying.springbootwebservice.domain.posts.PostsRepository;
import com.hoya.studying.springbootwebservice.web.dto.PostsListsResponseDto;
import com.hoya.studying.springbootwebservice.web.dto.PostsResponseDto;
import com.hoya.studying.springbootwebservice.web.dto.PostsSaveRequestDto;
import com.hoya.studying.springbootwebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    private Posts findByOne(Long id) {
        return postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = " + id));
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = findByOne(id);
        posts.update(requestDto);

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id) {
        Posts entity = findByOne(id);
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = findByOne(id);
        postsRepository.delete(posts);
    }
}
