package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Student;
import com.project.etsapi.entity.Teacher;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.TeacherService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TeacherServiceImpl
 * @Description
 * @Author llj
 * @Date 2021/11/17 13:00
 **/

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public int addTeacher(Teacher teacher) {
        if(teacherMapper.getTeacher(teacher.getTeacher_ID()) != null){
            // 已经存在，返回-1
            return -1;
        }
        else{
            return teacherMapper.addTeacher(teacher);
        }
    }

    @Override
    public int deleteTeacher(String teacher_ID) {
        if(teacherMapper.getTeacher(teacher_ID) != null){
            return teacherMapper.deleteTeacher(teacher_ID);
        }
        else{
            // 不存在则返回-1
            return -1;
        }
    }

    @Override
    public int setTeacher(Teacher teacher) {
        if(teacherMapper.getTeacher(teacher.getTeacher_ID()) != null){
            return teacherMapper.setTeacher(teacher);
        }
        else{
            // 不存在则返回-1
            return -1;
        }
    }

    @Override
    public Teacher getTeacher(String teacher_ID) {
        return teacherMapper.getTeacher(teacher_ID);
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherMapper.getAll();
    }

    @Override
    public List<String> getAllTeacherId() {
        List<String> Ids = new ArrayList<>();
        for(Teacher teacher : getAllTeacher()){
            Ids.add(teacher.getTeacher_ID());
        }
        return Ids;
    }

    @Override
    public int addList(MultipartFile file) throws IOException {
        List<Teacher> teacherList = new ArrayList<>();
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
                String teacher_ID = row.getCell(0).getStringCellValue();
                row.getCell(1).setCellType(CellType.STRING);
                String name = row.getCell(1).getStringCellValue();
                row.getCell(2).setCellType(CellType.STRING);
                String ID_number = row.getCell(2).getStringCellValue();
                if(teacher_ID.length() != 5 || ID_number.length()!= 18){
                    continue;
                }
                teacherList.add(new Teacher(teacher_ID, name, ID_number));
            }
        }
        int batchCount = 5;
        int batchLastIndex = batchCount;
        batchSqlSession = this.sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        for(int index = 0; index < teacherList.size();){
            /*
             * 如果数据量小于设置的批量数量，则最后的下标值为实际数量
             */
            if(batchLastIndex >= teacherList.size()){
                batchLastIndex = teacherList.size();
                result = result + teacherMapper.addList(teacherList.subList(index,batchLastIndex));
                //清除缓存
                batchSqlSession.clearCache();
                break;
            }else {
                result = result + teacherMapper.addList(teacherList.subList(index, batchLastIndex));
                batchSqlSession.clearCache();
                index = batchLastIndex;
                batchLastIndex = index + (batchCount -1);
            }
        }
        batchSqlSession.commit();
        return result;
    }
}
