package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.girfanov.tm.api.repository.ITaskRepository;
import ru.girfanov.tm.entity.Task;

@NoArgsConstructor
public final class TaskRepository extends AbstractRepository<Task> implements ITaskRepository {

    @Override
    public void mergeEntityName(@NotNull final String uuid, @NotNull final String name) {
        final Task task = map.get(uuid);
        task.setName(name);
        map.merge(uuid, task, (oldVal, newVal) -> newVal);
    }

    @Override
    public void removeEntityById(@NotNull final String uuid) {
        map.remove(uuid);
    }

    @Override
    public void removeAllEntitiesById(String uuid) {
        map.clear();
    }
}
