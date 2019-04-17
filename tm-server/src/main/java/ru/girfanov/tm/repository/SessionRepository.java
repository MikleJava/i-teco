package ru.girfanov.tm.repository;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@NoArgsConstructor
@RequiredArgsConstructor
public class SessionRepository {

    @NonNull private EntityManager em;

}
