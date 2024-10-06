package fhlandl.awesome_cloud_server.util;

import fhlandl.awesome_cloud_server.domain.Storage;
import fhlandl.awesome_cloud_server.domain.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.StorageFile;
import fhlandl.awesome_cloud_server.dto.CreateNodeDto;

import java.time.LocalDateTime;
import java.util.UUID;

public class StorageUtil {

    public static Storage createStorageItem(CreateNodeDto itemDto, String uniqueId, String storedPath) {
        LocalDateTime now = LocalDateTime.now();
        if (itemDto.getType().equals("F")) {
            StorageFile file = new StorageFile();
            file.setName(itemDto.getName());
            file.setUniqueId(uniqueId);
            file.setSize(itemDto.getFile().getSize());
            file.setStoredPath(storedPath);
            file.setCreatedAt(now);
            file.setLastModifiedAt(now);
            return file;
        } else {
            StorageDirectory directory = new StorageDirectory();
            directory.setUniqueId(uniqueId);
            directory.setName(itemDto.getName());
            directory.setCreatedAt(now);
            directory.setLastModifiedAt(now);
            return directory;
        }
    }

    public static String createUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static Storage createTestDirectory(String name, Long parentId) {
        StorageDirectory directory = new StorageDirectory();
        directory.setName(name);
        directory.setParentId(parentId);
        directory.setUserId(0L);

        LocalDateTime now = LocalDateTime.now();
        directory.setCreatedAt(now);
        directory.setLastModifiedAt(now);
        return directory;
    }
}
