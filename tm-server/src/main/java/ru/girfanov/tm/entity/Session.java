package ru.girfanov.tm.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Session extends AbstractEntity implements Serializable {

    private static final long serialVersionUID = -4054345421899926821L;

    @NonNull private Date timestamp;

    @Nullable private String signature;

    @NonNull private String userId;
}
