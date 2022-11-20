package app.util.sorter;

public class ContentSorterFactory {
	private static ContentSorterFactory INSTANCE;

	public static ContentSorterFactory instance() {
		if (INSTANCE == null) {
			INSTANCE = new ContentSorterFactory();
		}
		return INSTANCE;
	}

	private ContentSorterFactory() {
	}

	public ContentSorter<?> createSorter(ContentType type) {
		ContentSorter<?> sorter = null;
		switch (type) {
		case POST:
			sorter = new PostSorter();
			break;
		case COMMENT:
			sorter = new CommentSorter();
			break;
		}
		return sorter;
	}
	@Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("object cloning not supported");
    }
}
