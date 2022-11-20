package app.util.sorter;

import java.util.Comparator;

import app.entity.Post;

public class PostSorter implements ContentSorter<Post> {
	public PostTimeAscendingSorter getByTimeAscendingOrder() {
		return new PostTimeAscendingSorter();

	}

	public PostTimeDescendingSorter getByTimeDescendingOrder() {
		return new PostTimeDescendingSorter();
	}

	private static class PostTimeAscendingSorter implements Comparator<Post> {

		@Override
		public int compare(Post p1, Post p2) {
			// TODO Auto-generated method stub
			return p1.getTime().compareTo(p2.getTime());
		}
	}

	private static class PostTimeDescendingSorter implements Comparator<Post> {

		@Override
		public int compare(Post p1, Post p2) {
			// TODO Auto-generated method stub
			return -1 * p1.getTime().compareTo(p2.getTime());
		}
	}
}
