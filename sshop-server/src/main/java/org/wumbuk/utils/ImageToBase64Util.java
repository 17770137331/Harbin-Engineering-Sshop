package org.wumbuk.utils;



/**
 * @auther PeihaoYang
 * @date 2020/9/30 - 7:53
 */

 import java.io.FileInputStream;
  import java.io.FileOutputStream;
  import java.io.IOException;
  import java.io.InputStream;
  import java.io.OutputStream;
  import org.apache.commons.codec.binary.Base64;


//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.OutputStream;


/**
 * 该工具类用来将图片流转变成BASE64字符串编码、工具类
 */
public class ImageToBase64Util {

/**
       * 将图片转换成Base64编码、并且将编码进行返回
       * @param imgFile 待处理图片
       * @return
       */
             public static String getImgStr(String imgFile){
                 //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
                 InputStream in = null;
                 byte[] data = null;
                 //读取图片字节数组
                 try
                 {
                         in = new FileInputStream(imgFile);

                         //也就是说in.available返回的长度、Returns an estimate of the number of bytes that can be read
                         data = new byte[in.available()];
                         in.read(data);
                         in.close();
                     }
                 catch (IOException e)
                 {
                         e.printStackTrace();
                     }
                 return new String(Base64.encodeBase64(data));
             }

             /**
       * 对字节数组字符串进行Base64解码并生成图片
       * @param imgStr 图片数据
       * @param imgFilePath 保存图片全路径地址
       * @return
       */
             public static boolean generateImage(String imgStr,String imgFilePath){
                 //
                 if (imgStr == null) //图像数据为空
                         return false;

                 try
                 {
                         //Base64解码
                         byte[] b = Base64.decodeBase64(imgStr);
                         for(int i=0;i<b.length;++i)
                             {
                                 if(b[i]<0)
                                     {//调整异常数据
                                         b[i]+=256;
                                     }
                             }
                         //生成jpeg图片
                        OutputStream out = new FileOutputStream(imgFilePath);
                         out.write(b);
                         out.flush();
                         out.close();
                         return true;
                     }
                 catch (Exception e)
                 {
                         return false;
                     }
             }


}
