package fhlandl.awesome_cloud_server.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("F")
@Getter @Setter
public class StorageFile extends Storage {

    private long size;

    @Column(name = "stored_path")
    private String storedPath;
}
