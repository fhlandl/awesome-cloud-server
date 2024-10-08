package fhlandl.awesome_cloud_server.util;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class StorageUtil {

    public static Storage createStorageItem(CreateNodeDto itemDto,Long userId, String uniqueId, String storedPath) {
        LocalDateTime now = LocalDateTime.now();
        if (itemDto.getDType().equals("F")) {
            StorageFile file = new StorageFile();
            file.setName(itemDto.getName());
            file.setUserId(userId);
            file.setParentId(itemDto.getParentId());
            file.setUniqueId(uniqueId);
            if (itemDto.getFile() != null) {
                file.setSize(itemDto.getFile().getSize());
            }
            file.setStoredPath(storedPath);
            file.setCreatedAt(now);
            file.setLastModifiedAt(now);
            return file;
        } else {
            StorageDirectory directory = new StorageDirectory();
            directory.setName(itemDto.getName());
            directory.setUserId(userId);
            directory.setParentId(itemDto.getParentId());
            directory.setUniqueId(uniqueId);
            directory.setCreatedAt(now);
            directory.setLastModifiedAt(now);
            return directory;
        }
    }

    public static String createUniqueId() {
        return UUID.randomUUID().toString();
    }
}
