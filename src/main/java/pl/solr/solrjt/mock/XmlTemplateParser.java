package pl.solr.solrjt.mock;

import java.io.InputStream;

import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.util.NamedList;

public class XmlTemplateParser implements TemplateParser {
	private XMLResponseParser parser = new XMLResponseParser();

	public NamedList<Object> parse(InputStream stream) {
		return parser.processResponse(stream, "utf-8");
	}

}
