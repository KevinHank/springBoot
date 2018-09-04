package com.kevin.applets.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.kevin.applets.entity.DataGrid;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OSSUtils {
    // 华东为例oss-cn-shanghai.aliyuncs.com
    //public static final String CDN = "http://cdn.kevin.com";
    public static final String BASE = "cn-beijing.aliyuncs.com";
    public static final String accessKeyId = "LTAINeKbhVBTli0Z";
    public static final String accessKeySecret = "MrRzptIDAkIEm3JiqOcWkGwgPpFXtd";
    public static final String bucketName = "kevin5689";

    public static final String imgendpoint = "http://img-" + BASE;
    public static final String endpoint = "http://oss-" + BASE;
    public static final String imgoutUri = "http://" + bucketName + ".img-" + BASE;
    public static final String outUri = "http://" + bucketName + ".oss-" + BASE;
    public static Map<String, String> errorMap = new HashMap<String, String>();

    public static final String CDN = "http://" + bucketName + ".oss-" + BASE;
    static {
        errorMap.put("AccessDenied", "拒绝访问");
        errorMap.put("BucketAlreadyExists", "Bucket已经存在");
        errorMap.put("BucketNotEmpty", "Bucket不为空");
        errorMap.put("EntityTooLarge", "实体过大");
        errorMap.put("EntityTooSmall", "实体过小");
        errorMap.put("FileGroupTooLarge", "文件组过大");
        errorMap.put("FilePartNotExist", "文件Part不存在");
        errorMap.put("FilePartStale", "文件Part过时");
        errorMap.put("InvalidArgument", "参数格式错误");
        errorMap.put("InvalidAccessKeyId", "AccessKeyId不存在");
        errorMap.put("InvalidBucketName", "无效的Bucket名字");
        errorMap.put("InvalidDigest", "无效的摘要");
        errorMap.put("InvalidObjectName", "无效的Object名字");
        errorMap.put("InvalidPart", "效的Part");
        errorMap.put("InvalidPartOrder", "无效的part顺序");
        errorMap.put("InvalidTargetBucketForLogging", "Logging操作中有无效的目标bucket");
        errorMap.put("InternalError", "OSS内部发生错误");
        errorMap.put("MalformedXML", "XML格式非法");
        errorMap.put("MethodNotAllowed", "不支持的方法");
        errorMap.put("MissingArgument", "缺少参数");
        errorMap.put("MissingContentLength", "缺少内容长度");
        errorMap.put("NoSuchBucket", "Bucket不存在");
        errorMap.put("NoSuchKey", "文件不存在");
        errorMap.put("NoSuchUpload", "Multipart Upload ID不存在");
        errorMap.put("NotImplemented", "无法处理的方法");
        errorMap.put("PreconditionFailed", "预处理错误");
        errorMap.put("RequestTimeTooSkewed", "发起请求的时间和服务器时间超出15分钟");
        errorMap.put("RequestTimeout", "请求超时");
        errorMap.put("SignatureDoesNotMatch", "签名错误");
        errorMap.put("InvalidEncryptionAlgorithmError", "指定的熵编码加密算法错误");
    }

    /***
     * 网络上传(推荐小文件,非断点续传)
     * 
     * @author fs185085781
     * @param url
     *            网络资源路径
     * @param fileName
     *            文件名,请不要以'/'开头,如:222.jpg
     * */
    public static DataGrid uploadFileByUrl(String url, String dir, String fileName) {
        DataGrid dataGrid = new DataGrid();
        try {
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 上传
            InputStream inputStream = new URL(url).openStream();
            String fileUri = dir+fileName;
            ossClient.putObject(bucketName, fileUri, inputStream);
            // 关闭client
            ossClient.shutdown();
            dataGrid.setFlag(true);
            dataGrid.setObj(outUri + "/" + fileUri);
            dataGrid.setMsg("上传网络文件成功");
        } catch (OSSException e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(errorMap.get(e.getErrorCode()));
        } catch (Exception e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(e.getMessage());
        }
        return dataGrid;
    }

    /***
     * 流式上传(推荐小文件,非断点续传)
     * 
     * @author fs185085781
     * @param inputStream
     *            文件流,可以是从本地读取的,也可以是网络传过来的
     * @param fileName
     *            文件名,请不要以'/'开头,如:abcde/222.jpg
     * */
    public static DataGrid uploadFileByStream(InputStream inputStream,String dir, String fileName) {
        DataGrid dataGrid = new DataGrid();
        try {
            // 创建OSSClient实例
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            String fileUri = dir+fileName;
            ossClient.putObject(bucketName, fileUri, inputStream);
            // 关闭client
            ossClient.shutdown();
            dataGrid.setFlag(true);
            dataGrid.setObj(outUri + "/" + fileUri);
            dataGrid.setMsg("上传文件成功");
        } catch (OSSException e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(errorMap.get(e.getErrorCode()));
        } catch (Exception e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(e.getMessage());
        }
        return dataGrid;
    }

    /***
     * 网络图片上传(推荐小文件,非断点续传)
     * 
     * @author fs185085781
     * @param url
     *            网络资源路径
     * @param fileName
     *            文件名,请不要以'/'开头,如:abcde/222.jpg
     * */
    public static DataGrid uploadImgByUrl(String url,String dir, String fileName) {
        DataGrid dataGrid = new DataGrid();
        try {
            if (isImg(fileName)) {
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(imgendpoint, accessKeyId, accessKeySecret);
                // 上传
                InputStream inputStream = new URL(url).openStream();
                String fileUri = dir+fileName;
                ossClient.putObject(bucketName, fileUri, inputStream);
                // 关闭client
                ossClient.shutdown();
                dataGrid.setFlag(true);
                dataGrid.setObj(CDN + "/" + fileUri);
                dataGrid.setMsg("上传网络图片成功");
            } else {
                dataGrid.setFlag(false);
                dataGrid.setMsg("不支持的格式文件");
            }
        } catch (OSSException e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(errorMap.get(e.getErrorCode()));
        } catch (Exception e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(e.getMessage());
        }
        return dataGrid;
    }

    /***
     * 流式图片上传(推荐小文件,非断点续传)
     * 
     * @author fs185085781
     * @param inputStream
     *            文件流,可以是从本地读取的,也可以是网络传过来的
     * @param fileName
     *            文件名,必须jpg, png, bmp, webp, gif 请不要以'/'开头,如:abcde/222.jpg
     * */
    public static DataGrid uploadImgByStream(InputStream inputStream,String dir, String fileName) {
        DataGrid dataGrid = new DataGrid();
        try {
            if (isImg(fileName)) {
                // 创建OSSClient实例
                OSSClient ossClient = new OSSClient(imgendpoint, accessKeyId, accessKeySecret);
                String fileUri = dir+fileName;
                ossClient.putObject(bucketName, fileUri, inputStream);
                // 关闭client
                ossClient.shutdown();
                dataGrid.setFlag(true);
                dataGrid.setObj(CDN + "/" + fileUri);
                dataGrid.setMsg("上传图片成功");
            } else {
                dataGrid.setFlag(false);
                dataGrid.setMsg("不支持的格式文件");
            }

        } catch (OSSException e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(errorMap.get(e.getErrorCode()));
        } catch (Exception e) {
            dataGrid.setFlag(false);
            dataGrid.setMsg(e.getMessage());
        }
        return dataGrid;
    }

    public static boolean isImg(String fileName) {
        // 验证规则
        String regEx = ".+\\.(jpg|png|bmp|webp|gif|amr|wav|xls|xlsx)$";
        // 编译正则表达式
        Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pat.matcher(fileName);
        // 字符串是否与正则表达式相匹配
        boolean rs = matcher.matches();
        return rs;
    }
    public static Map listFile(String prefix) {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // "/" 为文件夹的分隔符
        listObjectsRequest.setDelimiter("/");
        // 列出fun目录下的所有文件和文件夹
        listObjectsRequest.setPrefix(prefix);
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        Map<String,List> map=new HashMap<String, List>();
        //遍历所有目录
        //prefix = aaa/dsd/fdsfdsg/gfdgfd/hgfhf/
        List<Map<String,String>> dirList=new ArrayList<Map<String,String>>();
        if(StringUtils.isNotBlank(prefix)){
            Pattern pattern = Pattern.compile("(.*\\/).+\\/$");
            Matcher matcher = pattern.matcher(prefix);
            Map<String,String> dirMap=new HashMap<String, String>();
            dirMap.put("name", "...");
            if(matcher.find()){
                dirMap.put("prefix", matcher.group(1));
            }else{
                dirMap.put("prefix", "");
            }
            dirMap.put("key","");
            dirList.add(dirMap);
        }
        for (String commonPrefix : listing.getCommonPrefixes()) {
            Map<String,String> dirMap=new HashMap<String, String>();
            dirMap.put("prefix", commonPrefix);
            dirMap.put("key", commonPrefix);
            String str=commonPrefix.substring(0, commonPrefix.length()-1);
            int index=str.lastIndexOf("/");
            dirMap.put("name", str.substring(index+1));
            dirList.add(dirMap);
        }
        // 遍历所有文件
        List<Map<String,String>> fileList=new ArrayList<Map<String,String>>();
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            String key=objectSummary.getKey();
            if(key.equals(prefix)){
                continue;  
            }
            
            
            if(key.indexOf(".")==-1){
                //我们认为它是文件夹
                Map<String,String> dirMap=new HashMap<String, String>();
                dirMap.put("prefix",key+"/");
                dirMap.put("key", key);
                int index2=key.lastIndexOf("/");
                String name2=key;
                if(index2!=-1){
                    name2=name2.substring(index2+1);
                }
                dirMap.put("name", name2);
                dirList.add(dirMap);  
            }else{
                Map<String,String> fileMap=new HashMap<String, String>();
                fileMap.put("key", key);
                fileMap.put("url", outUri+"/"+key);
                int index=key.lastIndexOf("/");
                String name=key;
                if(index!=-1){
                    name=key.substring(index+1);
                }
                fileMap.put("name", name);
                index=name.lastIndexOf(".");
                fileMap.put("type", name.substring(index+1));
                fileList.add(fileMap);
            }
        }
        map.put("files", fileList);
        map.put("dirs", dirList);
        ossClient.shutdown();
        return map;
    }
    public static void removeAllFile(String prefix) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 构造ListObjectsRequest请求
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        // "/" 为文件夹的分隔符
        listObjectsRequest.setDelimiter("/");
        // 列出fun目录下的所有文件和文件夹
        listObjectsRequest.setPrefix(prefix);
        ObjectListing listing = ossClient.listObjects(listObjectsRequest);
        List<String> keys=new ArrayList<String>();
        for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
            keys.add(objectSummary.getKey());
        }
        if(keys.size()>0){
            ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
        }
        for (String commonPrefix : listing.getCommonPrefixes()) {
            removeAllFile(commonPrefix);     
        }
        ossClient.shutdown();
    }
    public static void removeFiles(List<String> keys) {
        for(String key:keys){
            removeAllFile(key); 
        }
    }
    public static void main(String[] args) {
        String dir="";
        String dirName="你好";
        InputStream is=new InputStream() {
            public int read() throws IOException {
                return -1;
            }
        };
        OSSUtils.uploadFileByStream(is, dir, dirName);
    }
}
