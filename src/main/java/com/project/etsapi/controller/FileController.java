package com.project.etsapi.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public void uploadFile(MultipartFile file){
        System.out.println("文件类型：" + file.getContentType());
        System.out.println("文件组件名称：" + file.getName());
        System.out.println("文件大小：" + file.getSize()/1024 + "KB");
        System.out.println("文件名称：" + file.getOriginalFilename());

//        if(file != null){
//            String fileName = file.getName();
//            System.out.println(fileName);
//            try {
//                File mkdir = new File("E:\\PC\\Desktop\\test");
//                if(!mkdir.exists()) {
//                    mkdir.mkdirs();
//                }
//
//                FileOutputStream os = new FileOutputStream(mkdir.getPath()+"\\"+fileName);
//                InputStream in = new FileInputStream(file);
//
//                int b = 0;
//                while((b=in.read())!=-1){ //读取文件
//                    os.write(b);
//                }
//                os.flush(); //关闭流
//                in.close();
//                os.close();
//                System.out.println("成功！");
//            }
//            catch (Exception e){
//                e.printStackTrace();
//                System.out.println("发生错误");
//            }
//        }
//        else {
//            System.out.println("找不到文件");
//        }
    }

    @PostMapping("/multiUpload")
    public void multiUpload(HttpServletRequest request){
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
        }
    }
}
