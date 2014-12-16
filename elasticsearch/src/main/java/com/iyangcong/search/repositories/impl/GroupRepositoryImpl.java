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

import com.iyangcong.search.entities.Group;
import com.iyangcong.search.repositories.GroupRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<Group> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<Group> groupList = elasticsearchTemplate.queryForPage(searchQuery, Group.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Group> chunk = new ArrayList<Group>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					Group group = new Group();
					group.set_id(searchHit.getId());
					group.set_version(searchHit.getVersion());
					group.setName(SearchHitHelper.getHighlightField(searchHit, "NAME"));
					group.setCheckstatus(SearchHitHelper.getHighlightField(searchHit, "checkstatus"));
					group.setDescription(SearchHitHelper.getHighlightField(searchHit, "description"));
					group.setNickname(SearchHitHelper.getHighlightField(searchHit, "nickname"));
					group.setTopicnum(SearchHitHelper.getHighlightField(searchHit, "topicnum"));
					group.setUsernum(SearchHitHelper.getHighlightField(searchHit, "usernum"));
					chunk.add(group);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return groupList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, Group.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("checkstatus", 2l)).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field name = new Field("NAME");
		name.preTags("<span class=\"highlight\">");
		name.postTags("</span>");

		Field description = new Field("description");
		description.preTags("<span class=\"highlight\">");
		description.postTags("</span>");

		Field nickname = new Field("nickname");
		nickname.preTags("<span class=\"highlight\">");
		nickname.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("checkstatus", 2l)).withHighlightFields(name, description, nickname).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "NAME", "description", "nickname"));
		return builder;
	}

}
