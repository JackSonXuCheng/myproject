package com.myproject.myprojectelasticsearch.dao;

import com.myproject.myprojectelasticsearch.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/7 9:50
 * @comment:
 */
public interface ArticleDao extends ElasticsearchRepository<Article, Long> {
}
