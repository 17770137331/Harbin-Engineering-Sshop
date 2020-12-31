//package org.chatcat.utils;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.exception.CosClientException;
//import com.qcloud.cos.exception.CosServiceException;
//import com.qcloud.cos.http.HttpMethodName;
//import com.qcloud.cos.model.*;
//import com.qcloud.cos.region.Region;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Date;
//import java.util.List;
//
//
///**
// * @auther PeihaoYang
// * @date 2020/9/28 - 19:22
// */
//
//public class Cosutll {
//    // 1 初始化用户身份信息（secretId, secretKey）。
//    String secretId = "AKIDHK7evfKRQCsJTMMl6B9AxUllX1FD7QJv";
//    String secretKey = "SQ9PO5WhcGLAj24e9OO7xY2pPlSv87tP";
//
//    //该方法用来关闭连接
////    cosClient.shutdown();
//
//
//    public COSClient Initialization() {
//        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
//// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
//        Region region = new Region("ap-nanjing");
//        ClientConfig clientConfig = new ClientConfig(region);
//        // 3 生成 cos 客户端。
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        return cosClient;
//    }
//
//    /**
//     * 该方法用来创建存储桶
//     * @param cosClient
//     * @return
//     */
//    public boolean CreatBucket(COSClient cosClient){
//        String bucket = "examplebucket-125000110000"; //存储桶名称，格式：BucketName-APPID
//        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
//// 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
//        createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
//        try{
//            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
//            return true;
//        } catch (CosServiceException serverException) {
//            serverException.printStackTrace();
//            return false;
//        } catch (CosClientException clientException) {
//            clientException.printStackTrace();
//            return false;
//        }
//    }
//
//    /**
//     * 该方法用来上传文件
//     * @param cosClient
//     */
//    public void upload(COSClient cosClient) {
//        // 指定要上传的文件
//        File localFile = new File("D:/11111.jpg");
//        // 指定要上传到的存储桶
//        String bucketName = "examplebucket-1250000000";
//        // 指定要上传到 COS 上对象键
//        String key = "exampleobject";
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
//        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//    }
//
//    //该方法用来获得存储桶中对象列表
//    public void  getobjects(COSClient cosClient) {
//        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
//        String bucketName = "examplebucket-1250000000";
//        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
//// 设置bucket名称
//        listObjectsRequest.setBucketName(bucketName);
//// prefix表示列出的object的key以prefix开始
//        listObjectsRequest.setPrefix("images/");
//// deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
//        listObjectsRequest.setDelimiter("/");
//// 设置最大遍历出多少个对象, 一次listobject最大支持1000
//        listObjectsRequest.setMaxKeys(1000);
//        ObjectListing objectListing = null;
//        do {
//            try {
//                objectListing = cosClient.listObjects(listObjectsRequest);
//            } catch (CosServiceException e) {
//                e.printStackTrace();
//                return;
//            } catch (CosClientException e) {
//                e.printStackTrace();
//                return;
//            }
//            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
//            List<String> commonPrefixs = objectListing.getCommonPrefixes();
//
//            // object summary表示所有列出的object列表
//            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
//            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
//                // 文件的路径key
//                String key = cosObjectSummary.getKey();
//                // 文件的etag
//                String etag = cosObjectSummary.getETag();
//                // 文件的长度
//                long fileSize = cosObjectSummary.getSize();
//                // 文件的存储类型
//                String storageClasses = cosObjectSummary.getStorageClass();
//            }
//
//            String nextMarker = objectListing.getNextMarker();
//            listObjectsRequest.setMarker(nextMarker);
//        } while (objectListing.isTruncated());
//    }
//
//
//
////该方法用来获得一个永久的密钥、允许别人来访问该图片
//   public String getAddress(){
//       // 初始化永久密钥信息
//       String secretId = "COS_SECRETID";
//       String secretKey = "COS_SECRETKEY";
//       COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//       Region region = new Region("COS_REGION");
//       ClientConfig clientConfig = new ClientConfig(region);
//// 生成 cos 客户端。
//       COSClient cosClient = new COSClient(cred, clientConfig);
//// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
//       String bucketName = "examplebucket-1250000000";
//       String key = "exampleobject";
//       GeneratePresignedUrlRequest req =
//               new GeneratePresignedUrlRequest(bucketName, key, HttpMethodName.GET);
//// 设置签名过期时间(可选), 若未进行设置, 则默认使用 ClientConfig 中的签名过期时间(1小时)
//// 这里设置签名在半个小时后过期
//       Date expirationDate = new Date(System.currentTimeMillis() + 30L * 60L * 1000L);
//       req.setExpiration(expirationDate);
//       URL url = cosClient.generatePresignedUrl(req);
//       System.out.println(url.toString());
//       cosClient.shutdown();
//       return url.toString();
//   }
//
//    /**
//     * 该方法用来关闭客户端
//     * @return
//     */
//    public boolean deleteobject(COSClient cosClient){
//        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
//        String bucketName = "examplebucket-1250000000";
//        String key = "exampleobject";
//        cosClient.deleteObject(bucketName, key);
//        return  true;
//    }
//
//
//}
