package org.wumbuk.utils;

//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.model.PutObjectRequest;
//import com.qcloud.cos.model.PutObjectResult;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


/**
 * @auther PeihaoYang
 * @date 2020/9/28 - 16:24
 */

/**
 * 该方法用来生成一个图形验证码、并且把图形验证码存储在本地，通过util的工具类来生成一个静态方法
 */
public class GraphicVerificationUtil {
    public static String sRand="";

    private  String graphicAddress;

    @Override
    public String toString() {
        return "GraphicVerificationUtil{" +
                "sRand='" + sRand + '\'' +

                ", graphicAddress='" + graphicAddress + '\'' +
                '}';
    }

    public String getsRand() {
        return sRand;
    }

    public void setsRand(String sRand) {
        this.sRand = sRand;
    }


    public String getGraphicAddress() {
        return graphicAddress;
    }

    public void setGraphicAddress(String graphicAddress) {
        this.graphicAddress = graphicAddress;
    }

    /**
     * 该方法用来在指定范围之内来获得随机颜色，最后返回的是Color的类、Color作为java的内置对象类
     * @param fc
     * @param bc
     * @return
     */
    public Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        //Random用来返回double值、该值在0到1之间
        Random random = new Random();
        //配色不能超过255。。。
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        //red、green、blue...
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
    public BufferedImage creatImage(){

        // 在内存中创建图象
        int width=60, height=20;
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文,如果组件是不可以显示的，则此方法会返回Null
        Graphics g = bufferedImage.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200,250));
//        填充指定的矩形，矩形的颜色就是上下文的颜色
        g.fillRect(0, 0, width, height);

        //设定字体
        g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++)
        {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }

        // 取随机产生的认证码(4位数字)
        //String rand = request.getParameter("rand");
        //rand = rand.substring(0,rand.indexOf("."));
        String ZiMu = "qwertyupasdfghjkzxcvbnmQWERTYUPASDFGJKLZXCVBNM23456789";

        for (int i=0;i<4;i++){
            int index = random.nextInt(ZiMu.length());
            char c = ZiMu.charAt(index);
            sRand+=c;
            String valueOfchar = String.valueOf(c);
            // 将认证码显示到图象中
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            g.drawString(valueOfchar,13*i+6,16);
        }
        // 图象生效
        g.dispose();
        return bufferedImage;
    }

    /**
     * 该方法作为静态方法、用来返回最后生成验证码的地址
     * @param
     * @return
     */
    public static  GraphicVerificationUtil Graphic(HttpServletRequest request)  {
        GraphicVerificationUtil graphicVerificationUtil = new GraphicVerificationUtil();
        //文件上传的地址

        String path = request.getSession().getServletContext().getRealPath("/uploads/");

        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file1=new File(path+"\\"+TimeStamp.getTimeStamp("g")+".jpg");
        System.out.println("这儿运行了");



        try{
            //像下面这种就是创建图片的图片流
            BufferedImage p_w_picpath=new GraphicVerificationUtil().creatImage();
            //图片输出流
            ImageOutputStream p_w_picpathIo= ImageIO.createImageOutputStream(file1);
            //将图片流写入到图片输出流，图片流乘坐公交车、公交车发车，生成图形验证码
            ImageIO.write(p_w_picpath, "jpg", p_w_picpathIo);
//           graphicVerificationUtil.setsRand();
            graphicVerificationUtil.setGraphicAddress(file1.getPath());
            System.out.println("最后得到的信息为");
            System.out.println(graphicVerificationUtil.toString());
            return  graphicVerificationUtil;

        }catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
            System.out.println("这儿报错？？？");
            return  graphicVerificationUtil;
        }

    }


    /**
     * 该静态类用来通过本地图片的地址将图片推送到服务器端，同时将图片的链接进行返回
     * @param path
     * @return
     */
    public static String getLinkOfPhoto(String path){
        return null;
    }



}
