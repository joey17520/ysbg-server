package com.example.server.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * fastDFS工具类
 * @author JoeyWong
 * @version 1.0.0
 * @since 2023/6/10
 */
public class FastDFSUtils {

    private static Logger logger = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * 初始化客户端
     * ClientGlobal.init: 读取配置文件，并初始化对应属性
     */
    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            logger.error("初始化FastDFS失败", e.getMessage());
        }
    }

    /**
     * 上传文件
     * @param file
     * @return
     */
    public static String[] upload(MultipartFile file) {
        String filename = file.getOriginalFilename();
        logger.info("文件名：", filename);
        StorageClient storageClient = null;
        String[] uploadResults = null;
        try {
            // 获取storage客户端
            storageClient = getStorageClient();
            // 上传
            uploadResults = storageClient.upload_file(file.getBytes(),
                    filename.substring(filename.lastIndexOf(".") + 1), null);
        } catch (Exception e) {
            logger.error("上传文件失败", e.getMessage());
        }
        if (uploadResults == null) {
            logger.error("上传失败", storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("文件信息获取失败", e.getMessage());
        }
        return null;
    }

    /**
     * 下载文件
     * @param groupName
     * @param remoteFileName
     * @return
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            byte[] fileBytes = storageClient.download_file(groupName, remoteFileName);
            InputStream inputStream = new ByteArrayInputStream(fileBytes);
            return inputStream;
        } catch (Exception e) {
            logger.error("文件下载失败", e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     * @param groupName
     * @param remoteFileName
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            storageClient = getStorageClient();
            storageClient.delete_file(groupName, remoteFileName);
        } catch (Exception e) {
            logger.error("文件删除失败", e.getMessage());
        }
    }

    public static String getTrackerUrl() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageStorage = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageStorage = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("文件路径获取失败", e.getMessage());
        }
        return "http://" + storageStorage.getInetSocketAddress().getHostString() + ":8888/";
    }

    /**
     * 生成Storage客户端
     * @return
     * @throws Exception
     */
    private static StorageClient getStorageClient() throws Exception {
        TrackerServer trackerServer = getTrackerServer();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        return storageClient;
    }


    /**
     * 生成Tracker服务器
     * @return
     */
    private static TrackerServer getTrackerServer() throws Exception {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getTrackerServer();
        return trackerServer;
    }
}
