package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StorageServiceTest {

    @Autowired
    StorageService storageService;

    @Autowired
    StorageRepository storageRepository;
    @Test
    void findNodes() {
        StorageFile file1 = new StorageFile();
        file1.setName("file1");
        file1.setUserId(0L);
        StorageFile file2 = new StorageFile();
        file2.setName("file2");
        file2.setUserId(0L);
        StorageFile file3 = new StorageFile();
        file3.setName("file3");
        file3.setUserId(0L);
        StorageDirectory dir1 = new StorageDirectory();
        dir1.setName("dir1");
        dir1.setUserId(0L);
        StorageDirectory dir2 = new StorageDirectory();
        dir2.setName("dir2");
        dir2.setUserId(0L);

        storageRepository.save(file1);
        storageRepository.save(file2);
        storageRepository.save(file3);
        storageRepository.save(dir1);
        storageRepository.save(dir2);

        List<Storage> nodes = storageService.findNodes(0L);
        for (Storage node : nodes) {
            System.out.println(
                "id: " + node.getId() +
                    " name: " + node.getName() +
                    " dType: " + node.getDType() +
                    " parentId: " + node.getParentId()
            );
        }

        assertThat(nodes.size()).isEqualTo(5);
        assertThat(nodes).extracting("name").containsExactlyInAnyOrder("file1", "file2", "file3", "dir1", "dir2");
    }
}