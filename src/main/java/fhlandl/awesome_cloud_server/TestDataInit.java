package fhlandl.awesome_cloud_server;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.repository.UserRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import fhlandl.awesome_cloud_server.vo.CreateStorageItemVO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class TestDataInit {

    @Value("${file.dir}")
    private String rootPath;

    private final StorageRepository storageRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        log.info("test data init");
        User user1 = initUser("kyungjun_min", "qwer1234!", "mkj");
        User user2 = initUser("seoyeun_lee", "qwer1234!", "lsy");
        initUserStorage(user1);
        initUserStorage(user2);
    }

    private User initUser(String loginId, String password, String name) {
        User user = User.builder()
                .loginId(loginId)
                .password(bCryptPasswordEncoder.encode(password))
                .name(name)
                .build();
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    private void initUserStorage(User user) {
        String tag = "_" + user.getName();

        Storage root = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("root" + tag, "D", null), user, null, StorageUtil.createUniqueId(), ""));
        Storage dir1 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir1" + tag, "D", null), user, root, StorageUtil.createUniqueId(), ""));
        Storage dir2 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir2" + tag, "D", null), user, root, StorageUtil.createUniqueId(), ""));
        Storage dir3 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir3" + tag, "D", null), user, dir1, StorageUtil.createUniqueId(), ""));
        Storage dir4 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir4" + tag, "D", null), user, dir1, StorageUtil.createUniqueId(), ""));
        Storage dir5 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir5" + tag, "D", null), user, dir2, StorageUtil.createUniqueId(), ""));
        Storage dir6 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir6" + tag, "D", null), user, dir2, StorageUtil.createUniqueId(), ""));
        Storage dir7 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir7" + tag, "D", null), user, dir3, StorageUtil.createUniqueId(), ""));
        Storage dir8 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir8" + tag, "D", null), user, dir6, StorageUtil.createUniqueId(), ""));
        Storage dir10 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir10" + tag, "D", null), user, dir3, StorageUtil.createUniqueId(), ""));
        Storage dir11 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("dir11" + tag, "D", null), user, dir6, StorageUtil.createUniqueId(), ""));

        String userDirectoryPath = rootPath + user.getId() + "/";
        validateExistenceAndClearUserDirectory(userDirectoryPath);

        String uuid1 = StorageUtil.createUniqueId();
        String filePath1 = userDirectoryPath + uuid1 + ".txt";
        createFile(filePath1);
        Long fileSize1 = getFileSize(filePath1);
        Storage file1 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File1" + tag + ".txt", "F", fileSize1), user, dir3, uuid1, filePath1));

        String uuid2 = StorageUtil.createUniqueId();
        String filePath2 = userDirectoryPath + uuid2 + ".mp3";
        createFile(filePath2);
        Long fileSize2 = getFileSize(filePath2);
        Storage file2 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File2" + tag + ".mp3", "F", fileSize2), user, dir4, uuid2, filePath2));

        String uuid3 = StorageUtil.createUniqueId();
        String filePath3 = userDirectoryPath + uuid3 + ".pdf";
        createFile(filePath3);
        Long fileSize3 = getFileSize(filePath3);
        Storage file3 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File3" + tag + ".pdf", "F", fileSize3), user, dir5, uuid3, filePath3));

        String uuid4 = StorageUtil.createUniqueId();
        String filePath4 = userDirectoryPath + uuid4 + ".xls";
        createFile(filePath4);
        Long fileSize4 = getFileSize(filePath4);
        Storage file4 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File4" + tag + ".xls", "F", fileSize4), user, dir6, uuid4, filePath4));

        String uuid5 = StorageUtil.createUniqueId();
        String filePath5 = userDirectoryPath + uuid5 + ".xlsx";
        createFile(filePath5);
        Long fileSize5 = getFileSize(filePath5);
        Storage file5 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File5" + tag + ".xlsx", "F", fileSize5), user, dir3, uuid5, filePath5));

        String uuid6 = StorageUtil.createUniqueId();
        String filePath6 = userDirectoryPath + uuid6 + ".ppt";
        createFile(filePath6);
        Long fileSize6 = getFileSize(filePath6);
        Storage file6 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File6" + tag + ".ppt", "F", fileSize6), user, dir3, uuid6, filePath6));

        String uuid7 = StorageUtil.createUniqueId();
        String filePath7 = userDirectoryPath + uuid7 + ".pptx";
        createFile(filePath7);
        Long fileSize7 = getFileSize(filePath7);
        Storage file7 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File7" + tag + ".pptx", "F", fileSize7), user, dir3, uuid7, filePath7));

        String uuid8 = StorageUtil.createUniqueId();
        String filePath8 = userDirectoryPath + uuid8 + ".doc";
        createFile(filePath8);
        Long fileSize8 = getFileSize(filePath8);
        Storage file8 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File8" + tag + ".doc", "F", fileSize8), user, dir3, uuid8, filePath8));

        String uuid9 = StorageUtil.createUniqueId();
        String filePath9 = userDirectoryPath + uuid9 + ".docx";
        createFile(filePath9);
        Long fileSize9 = getFileSize(filePath9);
        Storage file9 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File9" + tag + ".docx", "F", fileSize9), user, dir3, uuid9, filePath9));

        String uuid10 = StorageUtil.createUniqueId();
        String filePath10 = userDirectoryPath + uuid10 + ".zip";
        createFile(filePath10);
        Long fileSize10 = getFileSize(filePath10);
        Storage file10 = storageRepository.save(StorageUtil.createStorageItem(new CreateStorageItemVO("File10" + tag + ".zip", "F", fileSize10), user, dir3, uuid10, filePath10));
    }

    private void createFile(String path) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            writer.write(path);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Long getFileSize(String path) {
        File file = new File(path);
        return file.length();
    }

    private void validateExistenceAndClearUserDirectory(String path) {
        File userDir = new File(path);

        if (!userDir.exists()) {
            boolean isDirCreated = userDir.mkdirs();
            if (!isDirCreated) {
                log.error("Failed to create parent directory: " + userDir.getAbsolutePath());
            }
        } else {
            clearDirectory(path);
        }
    }

    private void clearDirectory(String path) {
        File deleteDir = new File(path);
        File[] files = deleteDir.listFiles();
        Arrays.stream(Objects.requireNonNull(files)).forEach(file -> file.delete());
    }
}
