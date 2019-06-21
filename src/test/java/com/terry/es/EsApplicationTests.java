package com.terry.es;

import com.terry.es.entity.Article;
import com.terry.es.mapper.ArticleMapper;
import com.terry.es.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsApplicationTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Test
    public void testDelete() {
        //bookRepository.deleteAll();
        //elasticsearchTemplate.deleteIndex("demo");
        articleRepository.deleteAll();
    }

    @Test
    public void testUpdateDocumentType() {
        //Map mapping = elasticsearchTemplate.getMapping("demo", "book");
        //System.out.println(mapping);
        System.out.println(new Date());
    }

    @Test
    public void testInsertArticle() {
        List<Article> articles = articleMapper.selectAll();
        Iterable<Article> all = articleRepository.saveAll(articles);
        all.forEach(System.out::println);
    }
}
