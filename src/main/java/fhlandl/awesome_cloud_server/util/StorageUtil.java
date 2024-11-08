package fhlandl.awesome_cloud_server.util;

import fhlandl.awesome_cloud_server.domain.storage.Storage;
import fhlandl.awesome_cloud_server.domain.storage.StorageDirectory;
import fhlandl.awesome_cloud_server.domain.storage.StorageFile;
import fhlandl.awesome_cloud_server.domain.user.User;
import fhlandl.awesome_cloud_server.vo.CreateStorageItemVO;

import java.util.UUID;

public class StorageUtil {

    public static Storage createStorageItem(CreateStorageItemVO itemVO, User user, Storage parent, String uniqueId, String storedPath) {
        if (itemVO.getDType().equals("F")) {
            return StorageFile.builder()
                .name(itemVO.getName())
                .user(user)
                .parent(parent)
                .uniqueId(uniqueId)
                .size(itemVO.getFileSize())
                .storedPath(storedPath)
                .build();
        } else {
            return StorageDirectory.builder()
                .name(itemVO.getName())
                .user(user)
                .parent(parent)
                .uniqueId(uniqueId)
                .build();
        }
    }

    public static String createUniqueId() {
        return UUID.randomUUID().toString();
    }
}
