package com.kevin.demo.controller;

import com.kevin.demo.entity.DataGrid;
import com.kevin.demo.util.OSSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Hukai
 * 2018-08-20 0020 下午 5:10
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(UploadController.class);

    private static final String DIR = "test/";
    private static final String FILE_PATH = "tmp/img";

    /**
    * @Author: HuKai
    * @Date: 2018-08-20 0020 下午 6:03
    * @Description: 本地文件上传
    */
    @RequestMapping(value = "/uploadTest", method = RequestMethod.POST)
    public DataGrid upload() throws IOException {
        DataGrid dataGrid = new DataGrid();

        try {
            File file = new File("E:\\profile.jpg");
            FileInputStream fis = new FileInputStream(file);

            dataGrid = OSSUtils.uploadImgByStream(fis, DIR, file.getName());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage(), e);
        }

        return dataGrid;
    }

    /**
    * @Author: HuKai
    * @Date: 2018-08-20 0020 下午 6:03
    * @Description: 文件流上传
    */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public DataGrid upload(@RequestParam("file") MultipartFile file) {
        DataGrid dataGrid = new DataGrid();

        try {
            File f = File.createTempFile(FILE_PATH, null);
            file.transferTo(f);
            f.deleteOnExit();

            FileInputStream fis = new FileInputStream(f);

            dataGrid = OSSUtils.uploadImgByStream(fis, DIR, file.getOriginalFilename());
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return dataGrid;
    }
}
