package com.kyk.soundstory.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "index")
public class PlaylistEntity {
    private int index;
    private String email;
}
