package org.example.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.domain.Article;
import org.example.springbootdeveloper.dto.ArticleListViewResponse;
import org.example.springbootdeveloper.dto.ArticleViewResponse;
import org.example.springbootdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        // articles라는 키 값에 articles 리스트 저장
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장
        
        return "articleList"; // articleList.html 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article"; // article.html 뷰 조회
    }

    @GetMapping("/new-article")
    // id 키를 가진 쿼리 파라미터의 값을 id 변수에 매핑 (id는 없을 수도 있음)
    public String newArticle(@RequestParam(required=false) Long id, Model model) {
        if (id == null) { // id가 없으면 설정
            // 기본 생성자로 빈 객체를 만듦
            model.addAttribute("article", new ArticleViewResponse());
        } else { // id가 있으면 수정
            Article article = blogService.findById(id);
            // 기존 값을 가져오는 findById 메서드 호출
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        
        return "newArticle";
    }
}
