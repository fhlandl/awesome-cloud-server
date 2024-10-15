package fhlandl.awesome_cloud_server.domain.storage;

import fhlandl.awesome_cloud_server.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Storage extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "dtype", insertable = false, updatable = false)
    private String dType;

    @Column(name = "unique_id")
    private String uniqueId;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "user_id")
    private Long userId;
}
