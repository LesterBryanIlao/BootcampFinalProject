package app.util.sorter;

import java.util.Comparator;

import app.entity.Comment;

public class CommentSorter implements ContentSorter<Comment>{
	public CommentTimeAscendingSorter getByTimeAscendingOrder() {
		return new CommentTimeAscendingSorter();

	}

	public CommentTimeDescendingSorter getByTimeDescendingOrder() {
		return new CommentTimeDescendingSorter();
	}

	private static class CommentTimeAscendingSorter implements Comparator<Comment> {

		@Override
		public int compare(Comment c1, Comment c2) {
			// TODO Auto-generated method stub
			return c1.getTime().compareTo(c2.getTime());
		}
	}

	private static class CommentTimeDescendingSorter implements Comparator<Comment> {

		@Override
		public int compare(Comment c1, Comment c2) {
			// TODO Auto-generated method stub
			return -1*c1.getTime().compareTo(c2.getTime());
		}
	}
}
