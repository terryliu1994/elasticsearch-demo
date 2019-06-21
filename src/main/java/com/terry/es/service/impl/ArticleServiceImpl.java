package com.terry.es.service.impl;

import com.terry.es.entity.Article;
import com.terry.es.mapper.ArticleMapper;
import com.terry.es.repository.ArticleRepository;
import com.terry.es.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {


    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> searchArticle(String keyword, int page, int pageSize) {
        // ES的页码下标从0开始
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

        if (StringUtils.isEmpty(keyword)) {
            return articleRepository.findAll(pageRequest);
        }
        return articleRepository.findArticleByContentOrTitleOrAuthor(keyword, keyword, keyword, pageRequest);
    }

    @Override
    public Article findArticleById(Long articleId) {
        return articleRepository.findById(articleId).get();
    }

    @Override
    public void clearArticleEs() {
        articleRepository.deleteAll();
    }

    @Override
    public void syncAllArticleToEs() {
        List<Article> articles = articleMapper.selectAll();
        articleRepository.saveAll(articles).forEach(System.out::println);
    }

    @Override
    public Article createArticle(Article article) {
        // 新增到关系数据库
        int count = articleMapper.insertSelective(article);
        if (count <= 0) {
            throw new RuntimeException("文章新增失败");
        }
        article = articleMapper.selectByPrimaryKey(article.getArticleId());
        // 同步到ES
        articleRepository.save(article);
        return article;
    }
}
