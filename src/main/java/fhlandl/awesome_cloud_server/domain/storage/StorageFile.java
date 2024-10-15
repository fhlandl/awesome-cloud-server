package fhlandl.awesome_cloud_server.domain.storage;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("F")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StorageFile extends Storage {

    private long size;

    @Column(name = "stored_path")
    private String storedPath;
}
