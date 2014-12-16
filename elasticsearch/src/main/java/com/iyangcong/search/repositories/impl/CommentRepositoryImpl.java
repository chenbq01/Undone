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

import com.iyangcong.search.entities.Comment;
import com.iyangcong.search.repositories.CommentRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<Comment> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<Comment> commentList = elasticsearchTemplate.queryForPage(searchQuery, Comment.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<Comment> chunk = new ArrayList<Comment>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					Comment comment = new Comment();
					comment.set_id(searchHit.getId());
					comment.set_version(searchHit.getVersion());
					comment.setBookid(SearchHitHelper.getHighlightField(searchHit, "bookid"));
					comment.setChapterid(SearchHitHelper.getHighlightField(searchHit, "chapterid"));
					comment.setCheckstatus(SearchHitHelper.getHighlightField(searchHit, "checkstatus"));
					comment.setCommented_content(SearchHitHelper.getHighlightField(searchHit, "commented_content"));
					comment.setContent(SearchHitHelper.getHighlightField(searchHit, "content"));
					comment.setLanguage(SearchHitHelper.getHighlightField(searchHit, "LANGUAGE"));
					comment.setNickname(SearchHitHelper.getHighlightField(searchHit, "nickname"));
					comment.setSegmentid(SearchHitHelper.getHighlightField(searchHit, "segmentid"));
					chunk.add(comment);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return commentList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, Comment.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field commented_content = new Field("commented_content");
		commented_content.preTags("<span class=\"highlight\">");
		commented_content.postTags("</span>");

		Field content = new Field("content");
		content.preTags("<span class=\"highlight\">");
		content.postTags("</span>");

		Field nickname = new Field("nickname");
		nickname.preTags("<span class=\"highlight\">");
		nickname.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withHighlightFields(commented_content, content, nickname).withFilter(FilterBuilders.inFilter("checkstatus", new long[] { 1, 2 })).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "commented_content", "content", "nickname"));
		return builder;
	}

}
