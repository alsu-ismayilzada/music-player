package com.example.music_player.auth.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles",
        indexes = {
                @Index(name = "role_name", columnList = "name"),
                @Index(name = "role_keyword", columnList = "keyword")
        })
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_gen")
    @SequenceGenerator(name = "role_gen", sequenceName = "role_seq", allocationSize = 1)
    @Column(name = "id")
    @Comment(value = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    @Comment(value = "Rolların adı")
    private String name;

    @Column(name = "keyword", nullable = false)
    @Comment(value = "Açar söz")
    private String keyword;

    @Column(name = "enabled", nullable = false)
    @Comment(value = "Aktivləşdirildi")
    private boolean enabled;

    public Role(String name, String keyword) {
        this.name = name;
        this.keyword = keyword;
    }

    @Override
    public String getAuthority() {
        return this.keyword;
    }
}