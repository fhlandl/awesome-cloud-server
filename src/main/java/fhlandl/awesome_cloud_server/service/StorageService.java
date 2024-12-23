package fhlandl.awesome_cloud_server.service;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.dto.CreatedFileDto;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;
import fhlandl.awesome_cloud_server.dto.CreateResultDto;
import fhlandl.awesome_cloud_server.file.FileStore;
import fhlandl.awesome_cloud_server.repository.StorageRepository;
import fhlandl.awesome_cloud_server.util.StorageUtil;
import fhlandl.awesome_cloud_server.vo.CreateStorageItemVO;
import fhlandl.awesome_cloud_server.vo.StoreFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StorageService {

    private final StorageRepository storageRepository;
    private final FileStore fileStore;

    @Transactional
    public CreateResultDto saveNode(User user, CreateNodeDto createNodeDto) throws IOException {
        String uniqueId = StorageUtil.createUniqueId();
        String storedPath = null;
        Long fileSize = null;

        if (createNodeDto.getDType().equals("F")) {
            CreatedFileDto createdFileDto = fileStore.storeFile(new StoreFileVO(createNodeDto.getFile(), user.getId(), uniqueId));
            storedPath = createdFileDto.getStoredPath();
            fileSize = createNodeDto.getFile().getSize();
        }
        CreateStorageItemVO createStorageItemVO = new CreateStorageItemVO(createNodeDto.getName(), createNodeDto.getDType(), createNodeDto.getParentId(), fileSize);
        storageRepository.save(StorageUtil.createStorageItem(createStorageItemVO, user, uniqueId, storedPath));

        return new CreateResultDto(createNodeDto.getName(), createNodeDto.getDType(), uniqueId);
    }

    public Storage findNode(long nodeId) {
        return storageRepository.findOne(nodeId);
    }

    public List<Storage> findNodes(long userId) {
        return storageRepository.findAll(userId);
    }
}
