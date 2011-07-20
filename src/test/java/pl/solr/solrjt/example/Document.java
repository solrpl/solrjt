package pl.solr.solrjt.example;

public class Document {

	private final String id;
	private final String title;

	public Document(final String id, final String title) {
		this.id = id;
		this.title = title;
	}

	public final String getId() {
		return id;
	}

	public final String getTitle() {
		return title;
	}
	
	

}
