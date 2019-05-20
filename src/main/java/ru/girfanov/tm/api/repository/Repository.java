package ru.girfanov.tm.api.repository;

import ru.girfanov.tm.entity.AbstractEntity;

import java.util.Collection;

public interface Repository<T extends AbstractEntity> {
    Collection<T> findAll();
}