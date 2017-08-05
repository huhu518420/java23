package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 公司网盘接口
 * Created by huhu5 on 2017/7/26.
 */
public interface DiskService {


    /**
     * 根据PId进行查询
     * @param pId
     * @return
     */
    List<Disk> findByPId(Integer pId);

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    Disk findById(Integer id);

    /**
     * 保存新建的文件夹
     * @param disk
     */
    void saveNewFolder(Disk disk);

    void saveUpLoadFile(Disk disk, InputStream inputStream);

    void downloadFile(OutputStream outputStream, Disk disk);

    void renameDisk(Disk disk, String newFileName);

    void deleteDisk(Disk disk);

}
