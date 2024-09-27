package fhlandl.awesome_cloud_server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "stype")
@Getter @Setter
public abstract class Storage {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "user_id")
    private Long userId;

    private String path;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_modified_at")
    private LocalDateTime lastModifiedAt;
}
