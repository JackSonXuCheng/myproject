package com.myproject.myprojectelasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/7 9:46
 * @comment:
 */
@Data
@Document(indexName = "test", type = "article")
public class Article implements Serializable {

    @Id
    private Long id;

    @Field(type = FieldType.Text)
    private String content;

    private String title;
}
