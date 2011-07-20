package pl.solr.solrjt.mock;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.solr.client.solrj.SolrRequest;

public class StrictTemplateResolver implements TemplateResolver {

	public String resolve(final SolrRequest req) {
		return DigestUtils.md5Hex(req.getParams().toString()) + ".xml";
	}

}
