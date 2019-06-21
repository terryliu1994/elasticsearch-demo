package com.terry.es.repository;

import com.terry.es.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * ElasticsearchRepository 中自定义方法的实现，非常粗暴，对于简单的查询用起来非常爽
 * https://docs.spring.io/spring-data/elasticsearch/docs/current/reference/html/#elasticsearch.repositories
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    /**
     *
     * 1、可以通过方法命名的方式实现规则，find[T]By[Field][And/Or][Field],注意参数要和命名的字段数一致
     * 2、最后可以通过添加Pageable参数实现分页查询
     *
     * 还可以通过注解的方式
     */
    Page<Article> findArticleByContentOrTitleOrAuthor(String content, String title, String author, Pageable pageable);


    Article findByArticleId(Long articleId);
}
