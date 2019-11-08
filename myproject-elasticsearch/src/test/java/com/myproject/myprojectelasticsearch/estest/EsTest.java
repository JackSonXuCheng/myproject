package com.myproject.myprojectelasticsearch.estest;

import com.myproject.myprojectelasticsearch.MyprojectElasticsearchApplicationTests;
import com.myproject.myprojectelasticsearch.dao.ArticleDao;
import com.myproject.myprojectelasticsearch.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.IterableUtil;
import org.elasticsearch.index.query.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.*;

/**
 * @author jackson
 * @version 1.0
 * @date 2019/11/7 9:31
 * @comment:
 */
@Slf4j
public class EsTest extends MyprojectElasticsearchApplicationTests {

    @Autowired
    private ArticleDao articleDao;


    /**
     * 测试添加es 文章
     */
    @Test
    public void saveTest() {
        Article article = new Article();
        for (int i = 0; i < 100; i++) {
            article.setId((long) i);
            article.setTitle("es Title" + i);
            article.setContent("es Content" + i);

            articleDao.save(article);

        }

    }

    /**
     * 根据主键查询
     */
    @Test
    public void findByIdTest() {
        Optional<Article> optionalArticle = articleDao.findById(1L);
        optionalArticle.ifPresent(article -> log.info(article.toString()));

    }


    /**
     * 查询全部数据
     */
    @Test
    public void searchAllTest() {
        Iterable<Article> articleIterable = articleDao.findAll();
        List<Article> articles = new ArrayList<>();
        articleIterable.forEach(article -> articles.add(article));
        Collections.sort(articles, Comparator.comparingLong(Article::getId));
        log.warn("总数：" + articles.toString());
    }

    /**
     * 通过字段查询
     */
    @Test
    public void searchByFieldTest() {
        //匹配确切的短语或单词接近匹配
        MatchPhraseQueryBuilder matchPhraseQueryBuilder = QueryBuilders.matchPhraseQuery("title", "es Title0");
        //用于执行全文查询的标准查询，包括模糊匹配和短语或接近查询。
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "es Title0");

        /**
         * 对最后一个单词进行通配符搜索
         * {
         *   "match_phrase_prefix" : {
         *     "title" : {
         *       "query" : "es Title0",
         *       "slop" : 0,
         *       "max_expansions" : 50,
         *       "boost" : 1.0
         *     }
         *   }
         * }
         *select * from table.Name where title like 'es%'
         */
        MatchPhrasePrefixQueryBuilder matchPhrasePrefixQueryBuilder = QueryBuilders.matchPhrasePrefixQuery("title",
                "es");

        /**
         * match查询的多字段
         * {
         *   "multi_match" : {
         *     "query" : "1",
         *     "fields" : [
         *       "content^1.0",
         *       "title^1.0"
         *     ],
         *     "type" : "best_fields",
         *     "operator" : "OR",
         *     "slop" : 0,
         *     "prefix_length" : 0,
         *     "max_expansions" : 50,
         *     "zero_terms_query" : "NONE",
         *     "auto_generate_synonyms_phrase_query" : true,
         *     "fuzzy_transpositions" : true,
         *     "boost" : 1.0
         *   }
         * }
         * 等同于 select * from tableName where title=1 or id =1
         */

        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("1", "title", "id");

        /**
         * 常用词查询
         * {
         *   "common" : {
         *     "id" : {
         *       "query" : "4",
         *       "high_freq_operator" : "OR",
         *       "low_freq_operator" : "OR",
         *       "cutoff_frequency" : 0.01,
         *       "boost" : 1.0
         *     }
         *   }
         * }
         */
        CommonTermsQueryBuilder commonTermsQueryBuilder = QueryBuilders.commonTermsQuery("comment", "4");

        /**
         * 单个查询字符串中指定AND | OR | NOT条件和多字段搜索
         * {
         *   "query_string" : {
         *     "query" : "Title",
         *     "fields" : [
         *       "content^1.0",
         *       "title^1.0"
         *     ],
         *     "type" : "best_fields",
         *     "default_operator" : "and",
         *     "max_determinized_states" : 10000,
         *     "enable_position_increments" : true,
         *     "fuzziness" : "AUTO",
         *     "fuzzy_prefix_length" : 0,
         *     "fuzzy_max_expansions" : 50,
         *     "phrase_slop" : 0,
         *     "escape" : false,
         *     "auto_generate_synonyms_phrase_query" : true,
         *     "fuzzy_transpositions" : true,
         *     "boost" : 1.0
         *   }
         * }
         */
        QueryStringQueryBuilder queryStringQueryBuilder = QueryBuilders.queryStringQuery("Title").field("title")
                .defaultOperator(Operator.AND).field("content");

        /**
         * 模糊查询
         * {
         *   "fuzzy" : {
         *     "title" : {
         *       "value" : "1",
         *       "fuzziness" : "AUTO",
         *       "prefix_length" : 0,
         *       "max_expansions" : 50,
         *       "transpositions" : false,
         *       "boost" : 1.0
         *     }
         *   }
         * }
         */
        FuzzyQueryBuilder fuzzyQueryBuilder = QueryBuilders.fuzzyQuery("title", "1");
        /**
         * 范围查询
         * {
         *   "range" : {
         *     "id" : {
         *       "from" : 30,
         *       "to" : 40,
         *       "include_lower" : true,
         *       "include_upper" : true,
         *       "boost" : 1.0
         *     }
         *   }
         * }
         * select * from table.name where id between 30 and 40
         */
        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("id").from(30).to(40);

        /**
         * 范围查询(不包前后)
         * {
         *   "range" : {
         *     "id" : {
         *       "from" : 20,
         *       "to" : 25,
         *       "include_lower" : false,
         *       "include_upper" : false,
         *       "boost" : 1.0
         *     }
         *   }
         * }
         * select * from table.name  where id >20 and id<25
         */
        RangeQueryBuilder rangeQueryBuilder1 = QueryBuilders.rangeQuery("id").gt(20).lt(25);

        /**
         * 拼接查询
         * {
         *   "bool" : {
         *     "must" : [
         *       {
         *         "multi_match" : {
         *           "query" : "1",
         *           "fields" : [
         *             "title^1.0"
         *           ],
         *           "type" : "best_fields",
         *           "operator" : "OR",
         *           "slop" : 0,
         *           "prefix_length" : 0,
         *           "max_expansions" : 50,
         *           "zero_terms_query" : "NONE",
         *           "auto_generate_synonyms_phrase_query" : true,
         *           "fuzzy_transpositions" : true,
         *           "boost" : 1.0
         *         }
         *       }
         *     ],
         *     "adjust_pure_negative" : true,
         *     "boost" : 1.0
         *   }
         * }
         */
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(QueryBuilders.multiMatchQuery("1", "title"))
                .must(QueryBuilders.multiMatchQuery("title", "title"));

        log.info("查询语句" + boolQueryBuilder.toString());
        Iterable<Article> articleIterable = articleDao.search(boolQueryBuilder);
        List<Article> articles = (List<Article>) IterableUtil.toCollection(articleIterable);
        log.info("结果：" + articles.toString());
    }

    /**
     * 分页查询
     */
    @Test
    public void pageSearchTest() {
        PageRequest pageRequest = PageRequest.of(0, 50, Sort.Direction.DESC, "id");
        //模糊查询
        Page<Article> articlePage = articleDao.search(QueryBuilders.fuzzyQuery("title", "es"), pageRequest);

        Page<Article> page = articleDao.search(QueryBuilders.boolQuery().filter(QueryBuilders.fuzzyQuery("title",
                "title")), pageRequest);

        Page<Article> articlePage1 = articleDao.search(QueryBuilders.fuzzyQuery("title", "title"), pageRequest);
        log.warn("分页查询" + Objects.toString(articlePage1.getContent()));
    }


    /**
     * 通过主键修改相当于覆盖
     */
    @Test
    public void updateTest() {
        Article article = new Article();
        article.setId(99l);
        article.setContent("es");
        article.setTitle("title");
        articleDao.save(article);
    }

}
