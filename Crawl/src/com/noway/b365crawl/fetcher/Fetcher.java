package com.noway.b365crawl.fetcher;

import java.util.List;

import org.apache.solr.common.SolrInputDocument;

public interface Fetcher {
	List<SolrInputDocument> fetch();
	//void setPolicy(KeyWordsPolicy policy);
	void setDepth(int depth);
}
