package org.example.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동으로 1씩 증가
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // 'title'이라는 not null 컬럼과 매핑
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @CreatedDate // 엔티티 생성 시간을 저장
    @Column(name = "created_at")
    private LocalDateTime creatdAt;

    @LastModifiedDate // 마지막으로 수정된 시간을 저장
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /* @Builder: 롬복에서 지원하는 에너테이션
    * 생성자 위에 입력하면 빌더 패턴 방식으로 객체를 생성할 수 있어 편리
    * 빌더 패턴을 사용하면 객체를 유연하고 직관적으로 생성가능하기 때문에 개발자들이 애용하는 디자인 패턴
    * 즉, 어느 필드에 어떤 값이 들어가는지를 명시적으로 파악 가능*/
    @Builder // 빌더 패턴으로 객체 생성
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
