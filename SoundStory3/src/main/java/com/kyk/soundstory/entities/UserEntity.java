package com.kyk.soundstory.entities;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@EqualsAndHashCode(of ="email") // PRIMARY KEY
@AllArgsConstructor
public class UserEntity {
    private String email;
    private String password;
    private String nickname;
    private LocalDateTime createdAt;
    @Builder.Default
    private boolean isAdmin=false;
    @Builder.Default
    private boolean isDeleted=false;
    @Builder.Default
    private boolean isSuspended=false;

    public UserEntity() {
        this.isAdmin=false;
        this.isDeleted=false;
        this.isSuspended=false;
    }
}