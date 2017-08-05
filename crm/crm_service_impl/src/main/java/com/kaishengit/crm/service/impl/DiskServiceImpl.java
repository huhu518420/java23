package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.entity.DiskExample;
import com.kaishengit.crm.mapper.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 公司网盘实现类
 * Created by huhu5 on 2017/7/26.
 */
@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;

    //读取上传文件的路径的配置文件
    @Value("${file.path}")
    private String uploadFilePath;


    /**
     * 根据pId查找disk对象集合
     * @param pId
     * @return
     */
    @Override
    public List<Disk> findByPId(Integer pId) {

        DiskExample diskExample = new DiskExample();
        //Criteria是DiskExample里面的一个静态了类，用于添加where添加
        DiskExample.Criteria criteria = diskExample.createCriteria();

        criteria.andPIdEqualTo(pId);   //相当于select * from disk where PId = pId;

        return diskMapper.selectByExample(diskExample);
    }

    /**
     * 根据当前文件/文件夹的id进行保存
     * @param id
     * @return
     */
    @Override
    public Disk findById(Integer id) {

        return diskMapper.selectByPrimaryKey(id);
    }

    /**
     * 保存新的文件夹
     * @param disk
     */
    @Override
    public void saveNewFolder(Disk disk) {
        //设置更新时间
        disk.setUpdateTime(new Date());
        //设置文件类型为文件夹
        disk.setType(Disk.DISK_FOLDER_TYPE);
        //实现保存
        diskMapper.insert(disk);

    }

    /**文件上传
     * @param disk
     * @param inputStream
     */
    @Override
    @Transactional
    public void saveUpLoadFile(Disk disk, InputStream inputStream) {
        //重命名文件进行保存
        String fileName = disk.getName();
        //UUID.randomUUID()产生一个唯一数，再加上截取"."后面的部分
        String saveName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));

        //封装Disk
        disk.setSaveName(saveName);
        disk.setUpdateTime(new Date());
        disk.setDownloadCount(0);
        disk.setType(Disk.DISK_FILE_TYPE);

        diskMapper.insert(disk);

        try {
            //保存文件到磁盘
            OutputStream outputStream = new FileOutputStream(new File(uploadFilePath,saveName));
            IOUtils.copy(inputStream,outputStream);

            //关闭流
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
           throw new ServiceException("上传文件失败",e);
        }

    }

    /**
     * 文件下载
     * @param outputStream
     * @param disk
     */
    @Override
    @Transactional
    public void downloadFile(OutputStream outputStream, Disk disk) {
        //设置和更新下载数量
        disk.setDownloadCount(disk.getDownloadCount()+1);
        diskMapper.updateByPrimaryKey(disk);


    }

    /**
     * 修改文件名
     * @param disk
     * @param newFileName
     */
    @Override
    public void renameDisk(Disk disk, String newFileName) {
        //设置属性
        disk.setName(newFileName);
        disk.setUpdateTime(new Date());
        diskMapper.updateByPrimaryKey(disk);
    }

    /**
     * 删除文件
     * @param disk
     */
    @Override
    public void deleteDisk(Disk disk) {
        diskMapper.deleteByPrimaryKey(disk.getId());
    }


}



