package blockedArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class BlackBox<E> extends ArrayList<E> {
    @Override
    public E remove(int index) throws UnsupportedOperationException {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException(Constants.ERROR_MESSAGE);
    }
}