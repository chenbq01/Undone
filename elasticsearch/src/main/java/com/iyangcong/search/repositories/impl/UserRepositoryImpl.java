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

import com.iyangcong.search.entities.User;
import com.iyangcong.search.repositories.UserRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<User> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<User> userList = elasticsearchTemplate.queryForPage(searchQuery, User.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<User> chunk = new ArrayList<User>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					User user = new User();
					user.set_id(searchHit.getId());
					user.set_version(searchHit.getVersion());
					user.setActivestatus(SearchHitHelper.getHighlightField(searchHit, "activestatus"));
					user.setAttentionnum(SearchHitHelper.getHighlightField(searchHit, "attentionnum"));
					user.setDescription(SearchHitHelper.getHighlightField(searchHit, "description"));
					user.setLikenum(SearchHitHelper.getHighlightField(searchHit, "likenum"));
					user.setName(SearchHitHelper.getHighlightField(searchHit, "name"));
					user.setNickname(SearchHitHelper.getHighlightField(searchHit, "nickname"));
					user.setReadnum(SearchHitHelper.getHighlightField(searchHit, "readnum"));
					chunk.add(user);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return userList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, User.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("activestatus", 1l)).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field name = new Field("name");
		name.preTags("<span class=\"highlight\">");
		name.postTags("</span>");

		Field nickname = new Field("nickname");
		nickname.preTags("<span class=\"highlight\">");
		nickname.postTags("</span>");

		Field description = new Field("description");
		description.preTags("<span class=\"highlight\">");
		description.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("activestatus", 1l)).withHighlightFields(name, nickname, description).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "name", "nickname", "description"));
		return builder;
	}

}
