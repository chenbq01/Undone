package com.iyangcong.search.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.highlight.HighlightBuilder.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.FacetedPageImpl;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import com.iyangcong.search.entities.QuestionRecommend;
import com.iyangcong.search.repositories.QuestionRecommendRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class QuestionRecommendRepositoryImpl implements QuestionRecommendRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<QuestionRecommend> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<QuestionRecommend> questionRecommendList = elasticsearchTemplate.queryForPage(searchQuery, QuestionRecommend.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<QuestionRecommend> chunk = new ArrayList<QuestionRecommend>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					QuestionRecommend questionRecommend = new QuestionRecommend();
					questionRecommend.set_id(searchHit.getId());
					questionRecommend.set_version(searchHit.getVersion());
					questionRecommend.setAnswer(SearchHitHelper.getHighlightField(searchHit, "answer"));
					questionRecommend.setBack_uid(SearchHitHelper.getHighlightField(searchHit, "back_uid"));
					questionRecommend.setCategory(SearchHitHelper.getHighlightField(searchHit, "category"));
					questionRecommend.setCreate_time(SearchHitHelper.getHighlightField(searchHit, "create_time"));
					questionRecommend.setQuestion(SearchHitHelper.getHighlightField(searchHit, "question"));
					questionRecommend.setUpdate_time(SearchHitHelper.getHighlightField(searchHit, "update_time"));
					chunk.add(questionRecommend);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return questionRecommendList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, QuestionRecommend.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field answer = new Field("answer");
		answer.preTags("<span class=\"highlight\">");
		answer.postTags("</span>");

		Field question = new Field("question");
		question.preTags("<span class=\"highlight\">");
		question.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withHighlightFields(answer, question).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "answer", "question"));
		return builder;
	}

}
