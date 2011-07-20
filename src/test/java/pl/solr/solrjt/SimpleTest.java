package pl.solr.solrjt;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.junit.Test;

import pl.solr.solrjt.mock.ByExampleSolrServerMock;

public class SimpleTest {

	@Test
	public void simpleQuery() throws SolrServerException {
		SolrServer server = new ByExampleSolrServerMock(SimpleTest.class);
		QueryResponse response = server.query(new SolrQuery("1"));
	}
}
