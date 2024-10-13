package fhlandl.awesome_cloud_server;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
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

    private long userId = 0L;

    @Autowired
    private final StorageRepository storageRepository;

    @PostConstruct
    public void init() {
        log.info("test data init");

        Storage root = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("root", "D", null, null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir1 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir1", "D", root.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir2 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir2", "D", root.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir3 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir3", "D", dir1.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir4 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir4", "D", dir1.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir5 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir5", "D", dir2.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir6 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir6", "D", dir2.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir7 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir7", "D", dir3.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir8 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir8", "D", dir6.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir10 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir10", "D", dir3.getId(), null), 0L, StorageUtil.createUniqueId(), ""));
        Storage dir11 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir11", "D", dir6.getId(), null), 0L, StorageUtil.createUniqueId(), ""));

        String userDirectoryPath = rootPath + userId + "/";
        validateExistenceAndClearUserDirectory(userDirectoryPath);

        String uuid1 = StorageUtil.createUniqueId();
        String filePath1 = userDirectoryPath + uuid1 + ".txt";
        Storage file1 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File1.txt", "F", dir3.getId(), null), 0L, uuid1, filePath1));
        createFile(filePath1);

        String uuid2 = StorageUtil.createUniqueId();
        String filePath2 = userDirectoryPath + uuid2 + ".mp3";
        Storage file2 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File2.mp3", "F", dir4.getId(), null), 0L, uuid2, filePath2));
        createFile(filePath2);

        String uuid3 = StorageUtil.createUniqueId();
        String filePath3 = userDirectoryPath + uuid3 + ".pdf";
        Storage file3 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File3.pdf", "F", dir5.getId(), null), 0L, uuid3, filePath3));
        createFile(filePath3);

        String uuid4 = StorageUtil.createUniqueId();
        String filePath4 = userDirectoryPath + uuid4 + ".xls";
        Storage file4 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File4.xls", "F", dir6.getId(), null), 0L, uuid4, filePath4));
        createFile(filePath4);

        String uuid5 = StorageUtil.createUniqueId();
        String filePath5 = userDirectoryPath + uuid5 + ".xlsx";
        Storage file5 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File5.xlsx", "F", dir3.getId(), null), 0L, uuid5, filePath5));
        createFile(filePath5);

        String uuid6 = StorageUtil.createUniqueId();
        String filePath6 = userDirectoryPath + uuid6 + ".ppt";
        Storage file6 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File6.ppt", "F", dir3.getId(), null), 0L, uuid6, filePath6));
        createFile(filePath6);

        String uuid7 = StorageUtil.createUniqueId();
        String filePath7 = userDirectoryPath + uuid7 + ".pptx";
        Storage file7 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File7.pptx", "F", dir3.getId(), null), 0L, uuid7, filePath7));
        createFile(filePath7);

        String uuid8 = StorageUtil.createUniqueId();
        String filePath8 = userDirectoryPath + uuid8 + ".doc";
        Storage file8 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File8.doc", "F", dir3.getId(), null), 0L, uuid8, filePath8));
        createFile(filePath8);

        String uuid9 = StorageUtil.createUniqueId();
        String filePath9 = userDirectoryPath + uuid9 + ".docx";
        Storage file9 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File9.docx", "F", dir3.getId(), null), 0L, uuid9, filePath9));
        createFile(filePath9);

        String uuid10 = StorageUtil.createUniqueId();
        String filePath10 = userDirectoryPath + uuid10 + ".zip";
        Storage file10 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File10.zip", "F", dir3.getId(), null), 0L, uuid10, filePath10));
        createFile(filePath10);
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
