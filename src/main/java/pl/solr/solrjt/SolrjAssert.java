package pl.solr.solrjt;

import org.apache.solr.client.solrj.response.QueryResponse;

import static org.junit.Assert.*;

public class SolrjAssert {

	public static final void assertNumFound(final QueryResponse response, final long count) {
		assertNotNull(response);
		assertEquals(count, response.getResults().getNumFound());
	}
}
