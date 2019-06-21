package com.terry.es.controller;

import com.terry.es.entity.Article;
import com.terry.es.service.IArticleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @GetMapping("/search")
    @ResponseBody
    public Page search(@Param(value = "keyword") String keyword, @RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize) {
        return articleService.searchArticle(keyword, pageNum, pageSize);
    }

    @GetMapping("/list")
    public ModelAndView list(@Param(value = "keyword") String keyword, @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize) {
        Page articles = articleService.searchArticle(keyword, pageNum, pageSize);
        return new ModelAndView("article", Collections.singletonMap("articles", articles));
    }

    @GetMapping("/detail/{articleId}")
    public ModelAndView detail(@PathVariable("articleId") Long articleId) {
        Article article = articleService.findArticleById(articleId);
        return new ModelAndView("article_detail", Collections.singletonMap("article", article));
    }

    @GetMapping("/sync")
    public String sync() {
        articleService.syncAllArticleToEs();
        return "redirect:/article/list";
    }

    @GetMapping("/clear")
    public String clearElasticSearch() {
        articleService.clearArticleEs();
        return "redirect:/article/list";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity create(@RequestBody Article article) {
        return new ResponseEntity(articleService.createArticle(article), HttpStatus.OK);
    }

}
