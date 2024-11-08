package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import fhlandl.awesome_cloud_server.vo.CreateStorageItemVO;
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

        List<Storage> nodes = storageService.findValidNodes(user.getId());
        for (Storage node : nodes) {
            Long parentId = node.getParent() != null ? node.getParent().getId() : null;
            System.out.println(
                "id: " + node.getId() +
                    " name: " + node.getName() +
                    " dType: " + node.getDType() +
                    " parentId: " + parentId
            );
        }

        assertThat(nodes.size()).isEqualTo(5);
        assertThat(nodes).extracting("name").containsExactlyInAnyOrder("file1", "file2", "file3", "dir1", "dir2");
    }

    @Test
    void deleteNode() {
        User user = initUser();
        Storage target = initUserStorageAndGetTargetNode(user);

        storageService.deleteNode(target.getId());
        Storage deletedNode = storageRepository.findOne(target.getId());
        assertThat(deletedNode.isDeleted()).isTrue();
        assertThat(deletedNode.getChildren().get(0).isDeleted()).isTrue();
    }

    private User initUser() {
        User user = User.builder()
            .loginId("id1234")
            .password("pw1234")
            .name("mkj")
            .build();
        return userRepository.save(user);
    }

    private Storage initUserStorageAndGetTargetNode(User user) {
        Storage root = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("root", "D", null), user, null, StorageUtil.createUniqueId(), ""));
        Storage dir1 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir1", "D", null), user, root, StorageUtil.createUniqueId(), ""));
        Storage dir2 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir2", "D", null), user, root, StorageUtil.createUniqueId(), ""));
        Storage dir3 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir3", "D", null), user, dir1, StorageUtil.createUniqueId(), ""));
        Storage dir4 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir4", "D", null), user, dir1, StorageUtil.createUniqueId(), ""));
        Storage dir5 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir5", "D", null), user, dir2, StorageUtil.createUniqueId(), ""));
        Storage dir6 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir6", "D", null), user, dir2, StorageUtil.createUniqueId(), ""));
        Storage dir7 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir7", "D", null), user, dir3, StorageUtil.createUniqueId(), ""));
        Storage dir8 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir8", "D", null), user, dir6, StorageUtil.createUniqueId(), ""));
        Storage dir10 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir10", "D", null), user, dir3, StorageUtil.createUniqueId(), ""));
        Storage dir11 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir11", "D", null), user, dir6, StorageUtil.createUniqueId(), ""));

        String uuid1 = StorageUtil.createUniqueId();
        Storage file1 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File1.txt", "F", 100L), user, dir3, uuid1, ""));

        String uuid2 = StorageUtil.createUniqueId();
        Storage file2 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File2.mp3", "F", 100L), user, dir4, uuid2, ""));

        String uuid3 = StorageUtil.createUniqueId();
        Storage file3 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File3.pdf", "F", 100L), user, dir5, uuid3, ""));

        String uuid4 = StorageUtil.createUniqueId();
        Storage file4 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File4.xls", "F", 100L), user, dir6, uuid4, ""));

        String uuid5 = StorageUtil.createUniqueId();
        Storage file5 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File5.xlsx", "F", 100L), user, dir3, uuid5, ""));

        String uuid6 = StorageUtil.createUniqueId();
        Storage file6 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File6.ppt", "F", 100L), user, dir3, uuid6, ""));

        String uuid7 = StorageUtil.createUniqueId();
        Storage file7 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File7.pptx", "F", 100L), user, dir3, uuid7, ""));

        String uuid8 = StorageUtil.createUniqueId();
        Storage file8 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File8.doc", "F", 100L), user, dir3, uuid8, ""));

        String uuid9 = StorageUtil.createUniqueId();
        Storage file9 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File9.docx", "F", 100L), user, dir3, uuid9, ""));

        String uuid10 = StorageUtil.createUniqueId();
        Storage file10 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File10.zip", "F", 100L), user, dir3, uuid10, ""));

        return dir3;
    }
}
