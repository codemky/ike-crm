package com.ike.file;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.ike.common.constans.CodeMsg;
import com.ike.common.exception.IKEException;
import com.ike.common.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author wgm
 * @since 2019/6/28
 */
@Service
@Slf4j
public class FileService {

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public String uploadFile(MultipartFile file) {

        String endPoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;
        String fileHost = ConstantPropertiesUtil.FILE_HOST;
        String uploadUrl = null;

        try {
            // 创建OSSClient实例。
            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if (!ossClient.doesBucketExist(bucketName)) {
                ossClient.createBucket(bucketName);
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取原始文件名
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            //组装新的文件名
            String newName = fileName + fileType;
            //创建文件上传目录
            String filePath = new DateTime().toString("yyyy/MM/dd");
            //文件上传至服务器的具体位置
            String fileUrl = fileHost + "/" + filePath + "/" + newName;
            // 上传文件流。
            ossClient.putObject(bucketName, fileUrl, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //组装文件最终的url地址
            uploadUrl = "https://" + bucketName + "." + endPoint + "/" + fileUrl;

        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IKEException(CodeMsg.FILE_UPLOAD_ERROR);
        } catch (Exception e) {
            log.info("上传错误============================");
            log.error(e.getMessage());
            throw new IKEException(CodeMsg.UNKNOWN_REASON);
        }
        return uploadUrl;
    }

    /**
     * 上传文件 - 指定空间
     *
     * @param file
     * @return
     */
    public String uploadFileToHost(MultipartFile file, String hostPath) {
        if (!StringUtils.isEmpty(hostPath)) {
            ConstantPropertiesUtil.FILE_HOST = hostPath;
        }
        return this.uploadFile(file);
    }


    /**
     * 批量上传文件 - 指定空间
     *
     * @param files
     * @param hostPath
     * @return
     */
    public List<String> batchUploadFileToHost(MultipartFile[] files, String hostPath) {

        if (!StringUtils.isEmpty(hostPath)) {
            ConstantPropertiesUtil.FILE_HOST = hostPath;
        }
        List<String> urls = new LinkedList<>();
        for (MultipartFile file : files) {
            String url = this.uploadFile(file);
            urls.add(url);
        }
        return urls;

    }
}
