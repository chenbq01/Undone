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

import com.iyangcong.search.entities.ReadingMatter;
import com.iyangcong.search.repositories.ReadingMatterRepository;
import com.iyangcong.search.utilities.SearchHitHelper;

@Repository
public class ReadingMatterRepositoryImpl implements ReadingMatterRepository {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Override
	public Page<ReadingMatter> searchContentByKeywords(String keywords, Pageable pageable) {

		SearchQuery searchQuery = buildSearchByKeywordsQuery(keywords, pageable);

		Page<ReadingMatter> readingMatterList = elasticsearchTemplate.queryForPage(searchQuery, ReadingMatter.class, new SearchResultMapper() {

			@Override
			public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
				List<ReadingMatter> chunk = new ArrayList<ReadingMatter>();
				for (SearchHit searchHit : response.getHits()) {
					if (response.getHits().getHits().length <= 0) {
						return null;
					}
					ReadingMatter readingMatter = new ReadingMatter();
					readingMatter.set_id(searchHit.getId());
					readingMatter.setType(SearchHitHelper.getHighlightField(searchHit, "type"));
					readingMatter.setTitle_en(SearchHitHelper.getHighlightField(searchHit, "title_en"));
					readingMatter.setTitle_zh(SearchHitHelper.getHighlightField(searchHit, "title_zh"));
					readingMatter.setAuthor_zh(SearchHitHelper.getHighlightField(searchHit, "author_zh"));
					readingMatter.setAuthor_en(SearchHitHelper.getHighlightField(searchHit, "author_en"));
					readingMatter.setCategory(SearchHitHelper.getHighlightField(searchHit, "category"));
					readingMatter.setCover(SearchHitHelper.getHighlightField(searchHit, "cover"));
					readingMatter.setPrice(SearchHitHelper.getHighlightField(searchHit, "price"));
					readingMatter.setPriceUSD(SearchHitHelper.getHighlightField(searchHit, "priceUSD"));
					readingMatter.setSpecial_price(SearchHitHelper.getHighlightField(searchHit, "special_price"));
					readingMatter.setPrice_type(SearchHitHelper.getHighlightField(searchHit, "price_type"));
					readingMatter.setLanguage(SearchHitHelper.getHighlightField(searchHit, "language"));
					readingMatter.setTerminal(SearchHitHelper.getHighlightField(searchHit, "terminal"));
					readingMatter.setStatus(SearchHitHelper.getHighlightField(searchHit, "status"));
					readingMatter.setIntro_zh(SearchHitHelper.getHighlightField(searchHit, "intro_zh"));
					readingMatter.setIntro_en(SearchHitHelper.getHighlightField(searchHit, "intro_en"));
					readingMatter.setTranslator(SearchHitHelper.getHighlightField(searchHit, "translator"));
					readingMatter.setPublishing_zh(SearchHitHelper.getHighlightField(searchHit, "publishing_zh"));
					readingMatter.setPublishing_en(SearchHitHelper.getHighlightField(searchHit, "publishing_en"));
					readingMatter.set_version(searchHit.getVersion());
					chunk.add(readingMatter);
				}
				if (chunk.size() > 0) {
					return new FacetedPageImpl<T>((List<T>) chunk);
				}
				return null;
			}

		});

		return readingMatterList;

	}

	@Override
	public long hitsByKeywords(String keywords) {

		SearchQuery searchQuery = buildHitsByKeywordsQuery(keywords);

		long hits = elasticsearchTemplate.count(searchQuery, ReadingMatter.class);

		return hits;

	}

	private SearchQuery buildHitsByKeywordsQuery(String keywords) {
		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("status", 1l)).build();
		return searchQuery;
	}

	private SearchQuery buildSearchByKeywordsQuery(String keywords, Pageable pageable) {
		Field intro_zh = new Field("intro_zh");
		intro_zh.preTags("<span class=\"highlight\">");
		intro_zh.postTags("</span>");

		Field title_en = new Field("title_en");
		title_en.preTags("<span class=\"highlight\">");
		title_en.postTags("</span>");

		Field title_zh = new Field("title_zh");
		title_zh.preTags("<span class=\"highlight\">");
		title_zh.postTags("</span>");

		Field author_zh = new Field("author_zh");
		author_zh.preTags("<span class=\"highlight\">");
		author_zh.postTags("</span>");

		Field translator = new Field("translator");
		translator.preTags("<span class=\"highlight\">");
		translator.postTags("</span>");

		Field name_zh = new Field("publishing_zh");
		name_zh.preTags("<span class=\"highlight\">");
		name_zh.postTags("</span>");

		QueryBuilder builder = generateQueryBuilder(keywords);
		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withFilter(FilterBuilders.termFilter("status", 1l)).withHighlightFields(intro_zh, title_en, title_zh, author_zh, translator, name_zh).withPageable(pageable).build();
		return searchQuery;
	}

	private QueryBuilder generateQueryBuilder(String keywords) {
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		builder.should(QueryBuilders.multiMatchQuery(keywords, "intro_zh", "title_en", "title_zh", "author_zh", "translator", "publishing_zh"));
		return builder;
	}

}
