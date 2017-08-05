package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.dto.AjaxResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 公司网盘控制器
 * Created by huhu5 on 2017/7/26.
 */
@Controller
@RequestMapping("/disk")
public class DiskController {
    @Autowired
    private DiskService diskService;

    /**
     * 公司网盘首页
     * @return
     */
    @GetMapping
    public String companyDiskHome(@RequestParam(required = false,defaultValue = "0",value = "_")Integer pId,
                                  Model model) {
        //需要获取的值，根据设计思想第一次访问如果PId不存在默认为0，Model
        //根据pId查询disk对象集合
        List<Disk> diskList = diskService.findByPId(pId);
        //判断pId==0？，如果等于零说明查询的是父文件夹，否则说明查询的是子文件夹，此时的pId换位disk对象的id了
        if(!(pId == 0)) {
            Integer id = pId;
            Disk disk = diskService.findById(id);
            model.addAttribute("disk",disk);
        }

        model.addAttribute("diskList",diskList);

        return "disk/home";
    }


    /**
     * //新建文件夹
     * 以Ajax的形式进行请求和回显,需要指定回显的是内容
     * @return
     */
    @PostMapping("/new/folder")
    @ResponseBody
    public AjaxResult saveNewFolder(Disk disk) {
        //1.获取Disk对象并进行保存
        diskService.saveNewFolder(disk);

        //2.根据当前增加的Disk对象的pId(查询当钱父文件夹的所有子文件夹)进行查找
        List<Disk> diskList = diskService.findByPId(disk.getpId());
        //3.吧查找结果当作AjaxResult的参数进行返回到页面
        return AjaxResult.success(diskList);
    }


    /**
     * //文件上传
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam MultipartFile file,Integer pId,Integer accountId) throws IOException {
        //需要获取的参数，上传的文件file，pId,accountId
        //1.获取文件的原始名称
        String fileName = file.getOriginalFilename();
        //2.文件的大小
        Long fileSize = file.getSize();
        //3.获取文件的输入流
        InputStream inputStream = file.getInputStream();

        //封装Disk
        Disk disk = new Disk();
        disk.setpId(pId);
        disk.setAccountId(accountId);
        disk.setName(fileName);
        //利用commons-io依赖将文件大小转为可读类型大小
        disk.setFileSize(FileUtils.byteCountToDisplaySize(fileSize));

        //实现保存
        diskService.saveUpLoadFile(disk,inputStream);

        //根据当前文件夹的pId查找所有子文件/文件夹
        List<Disk> diskList = diskService.findByPId(pId);
        //返回Ajax
        return AjaxResult.success(diskList);
    }

    //文件下载
    @GetMapping("download")
    public void fileDownload(HttpServletResponse response,
                               @RequestParam(name = "_")Integer id) throws IOException {
        //需要获取的参数，response,id
        //根据id查找Disk对象，并判断是否为空和是否为文件夹
        Disk disk = diskService.findById(id);
        if(disk==null&&disk.getType().equals(Disk.DISK_FOLDER_TYPE)) {
            throw new NotFoundException();
        }

        //设置响应头
        response.setContentType("application/octet-stream");
        //设置弹出下载对话框中的文件名
        //处理中文的文件名 UTF-8->ISO8859-1
        String fileName = new String(disk.getName().getBytes("UTF-8"),"ISO8859-1");
        response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
        //获取响应输出流
        OutputStream outputStream = response.getOutputStream();

        //执行下载
        diskService.downloadFile(outputStream,disk);

    }

    /**打开
     * @param pId
     * @param model
     * @return
     */
    @GetMapping("/open")
    public String openDisk(@RequestParam(name = "_")Integer pId,Model model) {
        //需要获取的参数有父id,
        //根据点击的对象id查询当前对象下的所有
       List<Disk> diskList = diskService.findByPId(pId);

        //判断pId==0？，如果等于零说明查询的是父文件夹，否则说明查询的是子文件夹，此时的pId换位disk对象的id了
        if(!(pId == 0)) {
            Integer id = pId;
            Disk disk = diskService.findById(id);
            model.addAttribute("disk",disk);
        }
        model.addAttribute("diskList",diskList);

        return "/disk/home";
    }


    //重命名
    @PostMapping("/rename")
    @ResponseBody
    public AjaxResult renameDisk(@RequestParam(name = "id")Integer id,
                                 @RequestParam(name = "newFileName")String newFileName) {
        //需要获取的值，当前修改的文件的id,输入的内容
        //根据id查找disk对象并返回给页面回显
        Disk disk = diskService.findById(id);
        //实现修改
        diskService.renameDisk(disk,newFileName);

        return AjaxResult.success(disk);
    }

    //删除
    @PostMapping("/delete")
    public String deleteDisk(@RequestParam(name = "id")Integer id) {
        //需要获取点击的文件夹或文件的id
        //先根据id查找对象的disk对象
        Disk disk = diskService.findById(id);
        //判断是否是文件,如果是则删除
        if(disk.getType().equals(Disk.DISK_FILE_TYPE)||disk==null) {
            diskService.deleteDisk(disk);
        }else {
            //判断文件夹中是否还有文件
            List<Disk> diskList =diskService.findByPId(id);
            for(Disk disks : diskList) {
                deleteDisk(disks.getId());
            }
        }

        return "redirect:/disk";
    }












}
