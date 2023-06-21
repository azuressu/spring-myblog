package org.example.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.AddArticleRequest;
import org.example.springbootdeveloper.dto.ArticleResponse;
import org.example.springbootdeveloper.dto.UpdateArticleRequest;
import org.example.springbootdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor  // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 전체 블로그 글 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 하나를 조회
    public Article findById(Long id) {
        // JPA에서 제공하는 findById() 메서드를 사용해 ID를 받아 엔티티를 조회하고 없으면 IllegalArgumentException 예외를 발생시킴
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " +  id));
    }

    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    // 매칭한 메서드를 하나의 트랜잭션으로 묶는 역할
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return article;  
    }
}
