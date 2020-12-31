package org.wumbuk.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @auther PeihaoYang
 * @date 2020/7/24 - 10:15
 */

public class UploadFile {


    /**
     * 上传图片的静态方法，值得注意的是最后在部署项目前端进行图片的显示的时候
     * 注意访问的路径！！！
     *
     * @param upload
     * @return  返回文件的上传的路径中uploads中的文件名称
     * @throws IOException
     */
    public static String fileupload(HttpServletRequest request, MultipartFile upload) throws IOException {
        System.out.println("文件开始存储。。。");
        String path = request.getSession().getServletContext().getRealPath("/uploads/");

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replace("-", " ");
        filename = uuid + "_" + filename;
        upload.transferTo(new File(path, filename));

        return filename;
    }

}
