package com.graduation.education.user.service.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.graduation.education.util.enums.PlatformEnum;
import com.graduation.education.util.tools.JSONUtil;
import com.graduation.education.util.tools.StrUtil;
import org.junit.Test;

import java.io.File;

/**
 * @author 戴灵飞 dailingfei
 * @version 1.0
 * @date 2020/3/8 19:47
 */
public class OssTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    String accessKeyId = "LTAIkosVEjPq0OdF";
    String accessKeySecret = "aTbi6bJSufGSc7z2pyt00pgRQmKce7";

    @Test
    public void uploadPic() {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 创建PutObjectRequest对象。
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpg");
        PutObjectRequest putObjectRequest = new PutObjectRequest("coursehelper", "test3.jpg", new File("C:\\Users\\DLF\\Desktop\\素材\\bk2.jpg"), objectMetadata);

        // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        // ObjectMetadata metadata = new ObjectMetadata();
        // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
        // metadata.setObjectAcl(CannedAccessControlList.Private);
        // putObjectRequest.setMetadata(metadata);
        // 上传文件。
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        System.out.println("结果是:" + JSONUtil.toJSONString(putObjectResult));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void filePathTest(){
        String name = "bk.jpg";
        String filePath = PlatformEnum.COURSE.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
        System.out.println(filePath);
    }
}
