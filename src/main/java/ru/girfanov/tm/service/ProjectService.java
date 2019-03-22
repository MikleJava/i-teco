package ru.girfanov.tm.service;

import ru.girfanov.tm.entity.Project;
import ru.girfanov.tm.repository.Repository;

import java.util.Collection;

public class ProjectService implements Service<Project> {

    private Repository<Project> repository;

    public ProjectService(Repository<Project> repository) {
        this.repository = repository;
    }

    @Override
    public void persist(Project entity) {
        if(entity != null) {
            repository.persistEntity(entity);
        }
    }

    @Override
    public void merge(String uuid, String name) {
        if(!"".equals(name) && !"".equals(uuid) && uuid != null && name != null) {
            repository.mergeEntityName(uuid, name);
        }
    }

    @Override
    public void remove(String uuid) {
        if(!"".equals(uuid) && uuid != null) {
            repository.removeEntityById(uuid);
        }
    }

    @Override
    public void removeAll() {
        repository.removeAllEntities();
    }

    @Override
    public Collection<Project> findAll() {
        return repository.findAllEntities();
    }

    @Override
    public Project findOne(String uuid) {
        if(!"".equals(uuid) && uuid != null) {
            return repository.findEntityById(uuid);
        }
        return null;
    }
}
