package org.example.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springbootdeveloper.domain.Article;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    public Article toEntity() {
        // toEntity(): 빌더 패턴을 사용하여 DTO를 엔티티로 만들어주는 메서드
        // 추후에 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도로 사용
        return Article.builder().title(title).content(content).build();
    }
}
