package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.dto.CreatedFileDto;
import fhlandl.awesome_cloud_server.dto.CreateItemDto;
import fhlandl.awesome_cloud_server.dto.CreateResultDto;
import fhlandl.awesome_cloud_server.file.FileStore;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import fhlandl.awesome_cloud_server.vo.StoreFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final FileStore fileStore;

    @Transactional
    public CreateResultDto createItem(CreateItemDto createItemDto) throws IOException {

        String uniqueId = StorageUtil.createUniqueId();
        String storedPath = null;

        if (createItemDto.getType().equals("F")) {
            CreatedFileDto createdFileDto = fileStore.storeFile(new StoreFileVO(createItemDto.getFile(), createItemDto.getUserName(), uniqueId));
            storedPath = createdFileDto.getStoredPath();
        }
        storageRepository.save(StorageUtil.createStorageItem(createItemDto, uniqueId, storedPath));

        return new CreateResultDto(createItemDto.getName(), createItemDto.getType(), uniqueId);
    }
}
