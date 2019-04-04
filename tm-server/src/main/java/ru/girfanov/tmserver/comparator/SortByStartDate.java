package ru.girfanov.tmserver.comparator;

import org.jetbrains.annotations.NotNull;
import ru.girfanov.tmserver.entity.AbstractSortedEntity;

import java.util.Comparator;

public class SortByStartDate<T extends AbstractSortedEntity> implements Comparator<T> {
    @Override
    public int compare(@NotNull final T o1, @NotNull final T o2) {
        return o1.getDateStart().compareTo(o2.getDateStart());
    }
}
