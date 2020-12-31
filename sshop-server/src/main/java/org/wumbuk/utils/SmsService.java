package org.wumbuk.utils;


import org.wumbuk.entity.Message;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.5.3</version>
</dependency>
*/
public class SmsService {

    Message message;

    //抽取一个方法，方便复用

    /**
     * 该方法作为一个工具类，用来给用户发送信息，最后返回的值为一个javabean，用来表示短信的发送结果
     * @param phone
     * @return message
     */
    public static Message SendMessage(String phone){

        Message message = new Message();
        message.setPhone(phone);

        //连接阿里云，写自己的accessid和secret
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "",
                "");
        IAcsClient client = new DefaultAcsClient(profile);

        //构建请求
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        //生成六位随机数作为验证码
        int code = (int)((Math.random()*9+1)*100000);
        System.out.println("得到的code为"+code);
        message.setCode(new Long(code));

        //自定义参数，手机号、签名、模版号、验证码
        request.putQueryParameter("RegionId", "cn-hangzhou");   //不改
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "ChatCat小组");
        request.putQueryParameter("TemplateCode", "");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
        //等把下条给注释掉、把剩下的给保存




        //*************************************************************
//        message.setRs(true);

        try {

           CommonResponse response = client.getCommonResponse(request);
           System.out.println(response.getData());
           if (response.getData().contains("invalid")){
               System.out.println("判断得到的字符串非法");
           }else {
               message.setRs(true);
           }

        } catch (ServerException e) {
            message.setRs(false);
            e.printStackTrace();
            return message;
        } catch (ClientException e) {
            message.setRs(false);
            e.printStackTrace();
            return  message;
        }


        return message;
    }


}
