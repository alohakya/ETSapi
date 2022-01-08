package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Student;
import com.project.etsapi.mapper.StudentMapper;
import com.project.etsapi.service.StudentService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addStudent(Student student){
        try {
            return studentMapper.addStudent(student);
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public int deleteStudent(String student_ID){
        return studentMapper.deleteStudent(student_ID);
    }

    @Override
    public int setStudent(Student student){
        if(studentMapper.getStudent(student.getStudent_ID())!=null){
            return studentMapper.setStudent(student);
        }
        // 不存在则返回-1
        else {
            return -1;
        }
    }

    @Override
    public Student getStudent(String student_ID){
        return studentMapper.getStudent(student_ID);
    }

    @Override
    public List<Student> getAll(){
        return studentMapper.getAll();
    }

    @Override
    public int addList(MultipartFile file) throws IOException {
        List<Student> studentList = new ArrayList<>();
        int result = 0;

        SqlSession batchSqlSession = null;
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        InputStream ins = file.getInputStream();

        Workbook wb = null;

        if(suffix.equals("xlsx")){
            wb = new XSSFWorkbook(ins);
        }else{
            wb = new HSSFWorkbook(ins);
        }

        Sheet sheet = wb.getSheetAt(0);

        if(null != sheet) {
            for (int line = 0; line <= sheet.getLastRowNum(); line++) {
                Row row = sheet.getRow(line);
                if (null == row) {
                    continue;
                }
                row.getCell(0).setCellType(CellType.STRING);
                String student_ID = row.getCell(0).getStringCellValue();
                row.getCell(1).setCellType(CellType.STRING);
                String name = row.getCell(1).getStringCellValue();
                row.getCell(2).setCellType(CellType.STRING);
                String ID_number = row.getCell(2).getStringCellValue();
                if(student_ID.length() != 7 || ID_number.length()!= 18){
                    continue;
                }
                studentList.add(new Student(student_ID, name, ID_number));
            }
        }
        int batchCount = 5;
        int batchLastIndex = batchCount;
        batchSqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        for(int index = 0; index < studentList.size();){
            /*
             * 如果数据量小于设置的批量数量，则最后的下标值为实际数量
             */
            if(batchLastIndex >= studentList.size()){
                batchLastIndex = studentList.size();
                result = result + studentMapper.addList(studentList.subList(index,batchLastIndex));
                //清除缓存
                batchSqlSession.clearCache();
                break;
            }else {
                result = result + studentMapper.addList(studentList.subList(index, batchLastIndex));
                batchSqlSession.clearCache();
                index = batchLastIndex;
                batchLastIndex = index + (batchCount -1);
            }
        }
        batchSqlSession.commit();
        return result;
    }
}
