package org.wumbuk.utils;

import org.wumbuk.entity.ResultMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @auther PeihaoYang
 * @date 2020/9/20 - 18:51
 */
//该工具类用来快速的给ResultMessage对象进行赋值
public class MessageUtils {

    public static String getMessage(boolean isSystemMessage,String fromName,Object message){
        try {
            ResultMessage result = new ResultMessage();
            result.setSystem(isSystemMessage);
            result.setMessage(message);
            if(null!=fromName)
            {
                result.setFromName(fromName);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }



}
