package fhlandl.awesome_cloud_server;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@Profile("dev")
public class TestDataInit {

    private final StorageRepository storageRepository;

    @PostConstruct
    public void init() {
        log.info("test data init");
        Storage root = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("root", "D", null, null), 0L, "", ""));
        Storage dir1 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir1", "D", root.getId(), null), 0L, "", ""));
        Storage dir2 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir2", "D", root.getId(), null), 0L, "", ""));
        Storage dir3 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir3", "D", dir1.getId(), null), 0L, "", ""));
        Storage dir4 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir4", "D", dir1.getId(), null), 0L, "", ""));
        Storage dir5 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir5", "D", dir2.getId(), null), 0L, "", ""));
        Storage dir6 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir6", "D", dir2.getId(), null), 0L, "", ""));
        Storage dir7 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir7", "D", dir3.getId(), null), 0L, "", ""));
        Storage dir8 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir8", "D", dir6.getId(), null), 0L, "", ""));
        Storage dir10 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir10", "D", dir3.getId(), null), 0L, "", ""));
        Storage dir11 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("dir11", "D", dir6.getId(), null), 0L, "", ""));
        Storage file1 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File1.txt", "F", dir3.getId(), null), 0L, "", ""));
        Storage file2 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File2.mp3", "F", dir4.getId(), null), 0L, "", ""));
        Storage file3 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File3.pdf", "F", dir5.getId(), null), 0L, "", ""));
        Storage file4 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File4.xls", "F", dir6.getId(), null), 0L, "", ""));
        Storage file5 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File5.xlsx", "F", dir3.getId(), null), 0L, "", ""));
        Storage file6 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File6.ppt", "F", dir3.getId(), null), 0L, "", ""));
        Storage file7 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File7.pptx", "F", dir3.getId(), null), 0L, "", ""));
        Storage file8 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File8.doc", "F", dir3.getId(), null), 0L, "", ""));
        Storage file9 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File9.docx", "F", dir3.getId(), null), 0L, "", ""));
        Storage file10 = storageRepository.save(StorageUtil.createStorageItem(new CreateNodeDto("File10.zip", "F", dir3.getId(), null), 0L, "", ""));
    }
}
