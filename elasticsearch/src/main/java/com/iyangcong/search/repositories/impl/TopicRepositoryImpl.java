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

import com.iyangcong.search.entities.Topic;
import com.iyangcong.search.repositories.TopicRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class TopicRepositoryImpl implements TopicRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<Topic> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<Topic> topicList = elasticsearchTemplate.queryForPage(searchQuery, Topic.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Topic> chunk = new ArrayList<Topic>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					Topic topic = new Topic();
					topic.set_id(searchHit.getId());
					topic.set_version(searchHit.getVersion());
					topic.setName(SearchHitHelper.getHighlightField(searchHit, "NAME"));
					topic.setCheckstatus(SearchHitHelper.getHighlightField(searchHit, "checkstatus"));
					topic.setNickname(SearchHitHelper.getHighlightField(searchHit, "nickname"));
					topic.setDescription(SearchHitHelper.getHighlightField(searchHit, "description"));
					chunk.add(topic);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return topicList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, Topic.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field name = new Field("NAME");
		name.preTags("<span class=\"highlight\">");
		name.postTags("</span>");

		Field nickname = new Field("nickname");
		nickname.preTags("<span class=\"highlight\">");
		nickname.postTags("</span>");

		Field description = new Field("description");
		description.preTags("<span class=\"highlight\">");
		description.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).withHighlightFields(name, nickname, description).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "NAME", "nickname", "description"));
		return builder;
	}

}
