package app.util;

import java.util.Comparator;

import app.entity.Comment;

public class CommentsSorter {
	public CommentTimeDescSorter getByTimeDescendingOrder() {
		return new CommentTimeDescSorter();
	}

	public CommentTimeAscSorter getByTimeAscendingOrder() {
		return new CommentTimeAscSorter();

	}

	private static class CommentTimeAscSorter implements Comparator<Comment> {

		@Override
		public int compare(Comment o1, Comment o2) {
			// TODO Auto-generated method stub
			return o1.getTime().compareTo(o2.getTime());
		}
	}

	private static class CommentTimeDescSorter implements Comparator<Comment> {

		@Override
		public int compare(Comment o1, Comment o2) {
			// TODO Auto-generated method stub
			return -1 * o1.getTime().compareTo(o2.getTime());
		}
	}
}
