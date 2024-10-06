package fhlandl.awesome_cloud_server.repository;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StorageRepositoryTest {

    @Autowired StorageRepository storageRepository;

    @Test
    @Transactional
    @Rollback(false)
    void save() {
        // given
        Storage file1 = new StorageFile();
        file1.setName("file1");
        StorageDirectory dir1 = new StorageDirectory();
        dir1.setName("dir1");

        // when
        Storage savedFile = storageRepository.save(file1);
        Storage foundFile = storageRepository.findOne(file1.getId());
        Storage savedDir = storageRepository.save(dir1);
        Storage foundDir = storageRepository.findOne(dir1.getId());

        // then
        assertThat(foundFile.getId()).isEqualTo(file1.getId());
        assertThat(foundFile.getName()).isEqualTo(file1.getName());
        assertThat(savedFile).isEqualTo(file1);
        assertThat(foundFile).isEqualTo(file1);
        assertThat(foundDir.getId()).isEqualTo(dir1.getId());
        assertThat(foundDir.getName()).isEqualTo(dir1.getName());
        assertThat(savedDir).isEqualTo(dir1);
        assertThat(foundDir).isEqualTo(dir1);
    }
}
