package com.ifox.zc.service.impl;

import com.ifox.zc.dao.AppointmentMapper;
import com.ifox.zc.dao.BookMapper;
import com.ifox.zc.dao.StudentMapper;
import com.ifox.zc.dto.AppointExecution;
import com.ifox.zc.entity.Appointment;
import com.ifox.zc.entity.Book;
import com.ifox.zc.entity.Student;
import com.ifox.zc.enums.AppointStateEnum;
import com.ifox.zc.exception.AppointException;
import com.ifox.zc.exception.NoNumberException;
import com.ifox.zc.exception.RepeatAppointException;
import com.ifox.zc.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author:zhongchao
 * @Organization: ifox
 * @Description:
 * @Date:Created in18:10 2018/4/24
 * @Modified By:
 */
@Service
public class BookServiceImpl implements BookService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AppointmentMapper appointmentMapper;
    @Override
    public Book getById(long bookId) {
        return bookMapper.queryById(bookId);
    }

    @Override
    public List<Book> getList() {
        return bookMapper.queryAll(0,1000);
    }

    @Override
    public Student validateStu(Long studentId, Long password) {
        return studentMapper.quaryStudent(studentId,password);
    }

    @Override
    public List<Book> getSomeList(String name) {
        return bookMapper.querySome(name);
    }

    @Override
    public List<Appointment> getAppointByStu(long studentId) {
        return appointmentMapper.quaryAndReturn(studentId);
    }

    @Override
    @Transactional
    public AppointExecution appoint(long bookId, long studentId) {
        try {
            int update = bookMapper.reduceNumber(bookId);
            if (update <= 0) { //无库存
                throw new NoNumberException("no number");
            } else {    //执行预约操作
                int insert = appointmentMapper.insertAppointment(bookId, studentId);
                if (insert <= 0) {  //重复预约
                    throw new RepeatAppointException("repeat appoint");
                }else { //预约成功
                    return new AppointExecution(bookId,AppointStateEnum.SUCCESS);
                }
            }
        } catch (NoNumberException e1) {
            throw e1;
        } catch (RepeatAppointException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 所有编译期异常转换为运行期异常
            throw new AppointException("appoint inner error:" + e.getMessage());
        }
    }
}
