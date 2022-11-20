package app.util.sorter;

import java.util.Comparator;

public interface ContentSorter<T> {
	abstract Comparator<T> getByTimeAscendingOrder();
	abstract Comparator<T> getByTimeDescendingOrder();
}
