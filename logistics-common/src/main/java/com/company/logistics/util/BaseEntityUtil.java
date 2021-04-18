package com.company.logistics.util;

import com.company.logistics.base.BaseEntity;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author wzj
 * @date 2020/12/26 15:16
 */
public class BaseEntityUtil {

    public static String[] ignoreProperties = new String[]{"id", "deleted", "createDate", "updateDate", "createUser", "updateUser"};

    public static <T extends BaseEntity> void createEntityInfo(T t){
        addEntityDate(t);
        addEntityUser(t);
        addEntityDeleted(t);
    }

    public static <T extends BaseEntity> void updateEntityInfo(T t){
        updateEntityDate(t);
        updateEntityUser(t);
    }

    public static <T extends BaseEntity> void addEntityDate(T t){
        Date now = new Date();
        if (null == t.getCreateDate()){
            t.setCreateDate(now);
        }
        if (null == t.getUpdateDate()){
            t.setUpdateDate(now);
        }
    }

    public static <T extends BaseEntity> void updateEntityDate(T t){
        t.setUpdateDate(new Date());
    }

    public static <T extends BaseEntity> void addEntityDeleted(T t){
        if (null == t.getDeleted()){
            t.setDeleted(Boolean.FALSE);
        }
    }

    public static <T extends BaseEntity> void addEntityUser(T t){
        //todo 补充获取用户信息
        t.setCreateUser("1");
        t.setUpdateUser("1");
    }

    public static <T extends BaseEntity> void updateEntityUser(T t){
        //todo 补充获取用户信息
        t.setUpdateUser("1");
    }
}
