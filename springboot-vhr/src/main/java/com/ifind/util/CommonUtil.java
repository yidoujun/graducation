package com.ifind.util;

import com.ifind.common.exception.CustomException;
import com.ifind.common.exception.ResultStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Slf4j
public class CommonUtil {


    /**
     * 对象序列化
     *
     * @param obj       待序列化对象
     * @return
     */
    public static byte[] serialize(Object obj) throws CustomException {
        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(obj);
            byte[] byt = byteArrayOutputStream.toByteArray();
            return byt;
        } catch (Exception e){
            log.error("序列化异常", e);
            throw new CustomException(ResultStatusEnum.SERIALIZE);
        }finally {
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


    public static Object unserialize(byte[] byt) throws CustomException {
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(byt);
        try{
            oii = new ObjectInputStream(bis);
            Object obj = oii.readObject();
            return obj;
        } catch (Exception e) {
            log.error("反序列化异常", e);
            throw new CustomException(ResultStatusEnum.SERIALIZE);
        } finally {
            try {
                oii.close();
                bis.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }


}
