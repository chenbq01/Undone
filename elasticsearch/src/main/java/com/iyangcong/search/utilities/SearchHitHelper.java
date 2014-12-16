package com.iyangcong.search.utilities;

import org.elasticsearch.search.SearchHit;

public class SearchHitHelper {

	public static String getHighlightField(SearchHit searchHit, String field) {
		return searchHit.getHighlightFields().get(field) == null ?  searchHit.getSource().get(field)==null?"":searchHit.getSource().get(field).toString() : searchHit.getHighlightFields().get(field).fragments()[0].toString();
	}

}
