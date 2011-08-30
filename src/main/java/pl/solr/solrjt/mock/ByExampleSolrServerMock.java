package pl.solr.solrjt.mock;

import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.util.NamedList;

public class ByExampleSolrServerMock extends SolrServer {
	
	/** uid. */
	private static final long serialVersionUID = 7171094991751787853L;
	
	
	private Class<?> underTestClass;
	private TemplateResolver templateResolver = new StrictTemplateResolver();
	private TemplateParser templateParser = new XmlTemplateParser();

	public ByExampleSolrServerMock(final Class<?> underTestClass) {
		this.underTestClass = underTestClass;
	}

	

	public ByExampleSolrServerMock setTemplateResolver(TemplateResolver templateResolver) {
		this.templateResolver = templateResolver;
		return this;
	}



	public ByExampleSolrServerMock setTemplateParser(TemplateParser templateParser) {
		this.templateParser = templateParser;
		return this;
	}

	@Override
	public NamedList<Object> request(SolrRequest req)
			throws SolrServerException, IOException {
		
		Class<?> loader = underTestClass;
		String file;
		if (req instanceof UpdateRequest && ((UpdateRequest) req).getAction() != null) {
			switch(((UpdateRequest) req).getAction()) {
				case COMMIT:
					file = "commit.xml";
					loader = ByExampleSolrServerMock.class;
					break;
				case OPTIMIZE:
					file = "optimize.xml";
					loader = ByExampleSolrServerMock.class;
					break;
				default:
					file = templateResolver.resolve(req);
					break;
			}
		} else {
			file = templateResolver.resolve(req);			
		}
		InputStream stream = loader.getResourceAsStream(file);
		if (stream == null) {
			fail("Response: " + file + " not found. You should define this file with response for request: "
					+ templateResolver.getFileBase(req) + ". If this query is incorrect, you have some errors in tested code.");
		}
		return templateParser.parse(stream);

	}

}
