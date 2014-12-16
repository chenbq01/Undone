package com.iyangcong.search.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilders;
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

import com.iyangcong.search.entities.Review;
import com.iyangcong.search.repositories.ReviewRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<Review> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<Review> keywordList = elasticsearchTemplate.queryForPage(searchQuery, Review.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Review> chunk = new ArrayList<Review>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					Review review = new Review();
					review.set_id(searchHit.getId());
					review.set_version(searchHit.getVersion());
					review.setCheckstatus(SearchHitHelper.getHighlightField(searchHit, "checkstatus"));
					review.setContent(SearchHitHelper.getHighlightField(searchHit, "content"));
					review.setNickname(SearchHitHelper.getHighlightField(searchHit, "nickname"));
					review.setTitle(SearchHitHelper.getHighlightField(searchHit, "title"));
					chunk.add(review);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return keywordList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, Review.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field content = new Field("content");
		content.preTags("<span class=\"highlight\">");
		content.postTags("</span>");

		Field nickname = new Field("nickname");
		nickname.preTags("<span class=\"highlight\">");
		nickname.postTags("</span>");

		Field title = new Field("title");
		title.preTags("<span class=\"highlight\">");
		title.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).withHighlightFields(content, nickname, title).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "content", "nickname", "title"));
		return builder;
	}

}
