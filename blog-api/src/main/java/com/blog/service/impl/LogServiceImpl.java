package com.blog.service.impl;

import com.blog.mapper.LogMapper;
import com.blog.pojo.Log;
import com.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    public List<Log> all() {
        return logMapper.all();
    }
}
