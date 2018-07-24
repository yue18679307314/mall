package com.yunxin.cb.storage;

import com.yunxin.cb.mall.entity.meta.ObjectType;
import com.yunxin.cb.mall.entity.meta.UploadType;

import java.io.InputStream;

public interface IStorageService {


    /**
     * 上传文件
     * @param inputStream
     * @return
     */
    String put(InputStream inputStream, UploadType type);

    /**
     * 上传文件
     * @param inputStream
     * @return
     */
    String put(InputStream inputStream, UploadType type,String key);

    /**
     * 上传文件
     * @param data
     * @return
     */
    String put(byte[] data, UploadType type);


    /**
     * 上传文件
     * @param
     * @return
     */
    public String put(InputStream inputStream, ObjectType objectType);
}
