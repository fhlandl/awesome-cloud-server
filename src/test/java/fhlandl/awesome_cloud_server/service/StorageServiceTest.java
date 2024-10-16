package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class StorageServiceTest {

    @Autowired
    StorageService storageService;

    @Autowired
    StorageRepository storageRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void findNodes() {
        User user = userRepository.save(User.builder()
                .loginId("id1")
                .password("pw1")
                .name("name1")
                .build());

        StorageFile file1 = StorageFile.builder()
                .name("file1")
                .user(user)
                .build();
        StorageFile file2 = StorageFile.builder()
                .name("file2")
                .user(user)
                .build();
        StorageFile file3 = StorageFile.builder()
                .name("file3")
                .user(user)
                .build();
        StorageDirectory dir1 = StorageDirectory.builder()
                .name("dir1")
                .user(user)
                .build();
        StorageDirectory dir2 = StorageDirectory.builder()
                .name("dir2")
                .user(user)
                .build();

        storageRepository.save(file1);
        storageRepository.save(file2);
        storageRepository.save(file3);
        storageRepository.save(dir1);
        storageRepository.save(dir2);

        List<Storage> nodes = storageService.findNodes(user.getId());
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
