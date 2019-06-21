package com.terry.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * Document注解 默认情况下这个实体中所有的属性都会被建立索引、并且分词
 * Document 与 Field注解说明：https://blog.csdn.net/qq_28364999/article/details/81109666
 */
@Document(indexName = "index_cmf", type = "article", shards = 2, replicas = 0)
@Data
public class Article {

    @Id
    private Long articleId;

    private String author;

    private String title;

    private String content;

    /**
     * DateFormat中各个枚举日期格式说明：
     * https://www.cnblogs.com/chengyangyang/p/10256370.html
     *
     * 日期从ES中取出来要 记得加上时区 +8小时
     */
    @Field(type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date creationDate;

}
