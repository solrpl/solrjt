package pl.solr.solrjt.example;

import java.net.MalformedURLException;
import java.util.Collection;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import pl.solr.solrjt.mock.ByExampleSolrServerMock;

public class ExampleTest extends AbstractSolrjTest {
	private Example example;
	
	@Before
	public void bootstrap() throws MalformedURLException {
		example = new ExampleMock("0", 0, "");
		
	}

	@Test
	public void checkGetByTitle() throws SolrServerException {
		Collection<Document> docs = example.getByName("title", 1, 20);
		assertEquals(2, docs.size());
	}
	
	
	public class ExampleMock extends Example {

		public ExampleMock(String host, int port, String core)
				throws MalformedURLException {
			super(host, port, core);
		}

		@Override
		protected SolrServer createSolrServer(String url)
				throws MalformedURLException {
			return new ByExampleSolrServerMock(ExampleTest.class);
		}
		
		
	}
}
