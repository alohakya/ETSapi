package com.project.etsapi.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public void uploadFile(@RequestBody File file){
        if(file != null){
            String fileName = file.getName();
            System.out.println(fileName);
            try {
                File mkdir = new File("E:\\PC\\Desktop\\test");
                if(!mkdir.exists()) {
                    mkdir.mkdirs();
                }

                FileOutputStream os = new FileOutputStream(mkdir.getPath()+"\\"+fileName);
                InputStream in = new FileInputStream(file);

                int b = 0;
                while((b=in.read())!=-1){ //读取文件
                    os.write(b);
                }
                os.flush(); //关闭流
                in.close();
                os.close();
                System.out.println("成功！");
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("发生错误");
            }
        }
        else {
            System.out.println("找不到文件");
        }
    }
}
