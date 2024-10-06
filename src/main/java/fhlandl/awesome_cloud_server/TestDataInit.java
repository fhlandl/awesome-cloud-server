package fhlandl.awesome_cloud_server;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final StorageRepository storageRepository;

    @PostConstruct
    public void init() {
        log.info("test data init");
        Storage name1 = storageRepository.save(StorageUtil.createTestDirectory("name1", null));
        Storage name2 = storageRepository.save(StorageUtil.createTestDirectory("name2", null));
        Storage name3 = storageRepository.save(StorageUtil.createTestDirectory("name3", name1.getId()));
        Storage name4 = storageRepository.save(StorageUtil.createTestDirectory("name4", name1.getId()));
        Storage name5 = storageRepository.save(StorageUtil.createTestDirectory("name5", name2.getId()));
        Storage name6 = storageRepository.save(StorageUtil.createTestDirectory("name6", name2.getId()));
        Storage name7 = storageRepository.save(StorageUtil.createTestDirectory("name7", name3.getId()));
        Storage name8 = storageRepository.save(StorageUtil.createTestDirectory("name8", name6.getId()));
    }
}
