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

import com.iyangcong.search.entities.OpenClassSegment;
import com.iyangcong.search.repositories.OpenClassSegmentRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class OpenClassSegmentRepositoryImpl implements OpenClassSegmentRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<OpenClassSegment> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<OpenClassSegment> userList = elasticsearchTemplate.queryForPage(searchQuery, OpenClassSegment.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<OpenClassSegment> chunk = new ArrayList<OpenClassSegment>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					OpenClassSegment openClassSegment = new OpenClassSegment();
					openClassSegment.set_id(searchHit.getId());
					openClassSegment.set_version(searchHit.getVersion());
					openClassSegment.setBookid(SearchHitHelper.getHighlightField(searchHit, "bookid"));
					openClassSegment.setChapterid(SearchHitHelper.getHighlightField(searchHit, "chapterid"));
					openClassSegment.setLanguage(SearchHitHelper.getHighlightField(searchHit, "language"));
					openClassSegment.setSegment_en(SearchHitHelper.getHighlightField(searchHit, "segment_en"));
					openClassSegment.setSegment_zh(SearchHitHelper.getHighlightField(searchHit, "segment_zh"));
					chunk.add(openClassSegment);
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

		long hits = elasticsearchTemplate.count(searchQuery, OpenClassSegment.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field segment_en = new Field("segment_en");
		segment_en.preTags("<span class=\"highlight\">");
		segment_en.postTags("</span>");

		Field segment_zh = new Field("segment_zh");
		segment_zh.preTags("<span class=\"highlight\">");
		segment_zh.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withHighlightFields(segment_en, segment_zh).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "segment_en", "segment_zh"));
		return builder;
	}

}
