package pl.solr.solrjt.example;

import java.net.MalformedURLException;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import com.google.common.collect.Lists;

public class Example {
	
	private SolrServer solrServer;

	public Example(final String host, final int port, final String core) throws MalformedURLException {
		String url = "http://" + host + ":" + port + "/solr/" + core;
		solrServer = createSolrServer(url);
	}
	
	protected SolrServer createSolrServer(final String url) throws MalformedURLException {
		return new CommonsHttpSolrServer(url);
	}
	
	public Collection<Document> getByName(String title, int start, int count) throws SolrServerException {
		List<Document> docs = Lists.newArrayList();
		SolrQuery query = new SolrQuery(title);
		query.setStart(start);
		query.setRows(count);
		QueryResponse response = solrServer.query(query);
		SolrDocumentList list = response.getResults();
		for( SolrDocument doc : list) {
			docs.add(new Document(doc.get("id").toString(), doc.get("name").toString()));
		}
		return docs;
	}

}
