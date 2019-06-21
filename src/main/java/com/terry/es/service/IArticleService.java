package com.terry.es.service;

import com.terry.es.entity.Article;
import org.springframework.data.domain.Page;

public interface IArticleService {

    Page<Article> searchArticle(String keyword, int page, int pageSize);

    void clearArticleEs();

    void syncAllArticleToEs();

    Article findArticleById(Long articleId);

    Article createArticle(Article article);
}
