package com.cidic.design.service;

import java.util.List;


public interface HomeService {
	
	public List<Object> getHomeContentData();
	
	public List<Object> getSearchResultByKeywards(List<String> keywords);
}
