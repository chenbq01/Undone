package com.iyangcong.search.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.suggest.Suggest.Suggestion;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iyangcong.search.services.SuggestService;

@Service
public class SearchSuggestService implements SuggestService {

	@Autowired
	private Client client;

	@Override
	public List<String> getSuggestByKeywords(String keywords) {

		//PhraseSuggestionBuilder phraseSuggestionBuilder = SuggestBuilder.phraseSuggestion("SearchSuggester").field("intro_zh").analyzer("ik").text(keywords).addCandidateGenerator(PhraseSuggestionBuilder.candidateGenerator("intro_zh"));
		TermSuggestionBuilder termSuggestionBuilder = SuggestBuilder.termSuggestion("SearchSuggester").field("intro_zh").analyzer("ik").text(keywords);

		SearchResponse suggestResponse = client.prepareSearch("onion").setTypes("readingmaterial").addSuggestion(termSuggestionBuilder).execute().actionGet();
		List<? extends Suggestion.Entry<? extends Option>> list = suggestResponse.getSuggest().getSuggestion("SearchSuggester").getEntries();
		List<String> suggestions = new ArrayList<String>();
		if (list == null) {
			return null;
		} else {
			for (Entry<? extends Option> e : list) {
				for (Option option : e) {
					suggestions.add(option.getText().toString());
				}
			}
			return suggestions;
		}

	}

}
