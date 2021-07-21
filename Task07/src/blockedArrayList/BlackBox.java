package blockedArrayList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Predicate;

public class BlackBox<E> extends ArrayList<E> {
    @Override
    public E remove(int index) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return null;
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
    }

    @Override
    public boolean remove(Object o) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return false;
    }

    @Override
    public E set(int index, E element) {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
        return null;
    }

    @Override
    public void clear() {
        ConsoleHelper.printError(Constants.ERROR_MESSAGE);
    }
}