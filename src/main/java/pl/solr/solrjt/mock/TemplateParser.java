package pl.solr.solrjt.mock;

import java.io.InputStream;

import org.apache.solr.common.util.NamedList;

public interface TemplateParser {

	NamedList<Object> parse(InputStream stream);

}
