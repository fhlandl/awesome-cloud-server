package fhlandl.awesome_cloud_server.util;

import fhlandl.awesome_cloud_server.domain.Storage;
import fhlandl.awesome_cloud_server.domain.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.StorageFile;
import fhlandl.awesome_cloud_server.dto.CreateItemDto;

import java.util.UUID;

public class StorageUtil {

    public static Storage createStorageItem(CreateItemDto itemDto, String uniqueId, String storedPath) {
        if (itemDto.getType().equals("F")) {
            StorageFile file = new StorageFile();
            file.setName(itemDto.getName());
            file.setUniqueId(uniqueId);
            file.setSize(itemDto.getFile().getSize());
            file.setStoredPath(storedPath);
            return file;
        } else {
            StorageDirectory directory = new StorageDirectory();
            directory.setUniqueId(uniqueId);
            directory.setName(itemDto.getName());
            return directory;
        }
    }

    public static String createUniqueId() {
        return UUID.randomUUID().toString();
    }
}
