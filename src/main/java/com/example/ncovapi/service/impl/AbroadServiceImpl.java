package com.example.ncovapi.service.impl;

import com.example.ncovapi.dao.AbroadMapper;
import com.example.ncovapi.entity.Abroad;
import com.example.ncovapi.service.AbroadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AbroadServiceImpl implements AbroadService {
    @Resource
    AbroadMapper mapper;
    @Override
    public List<Abroad> selectLatest() {
        return mapper.selectLatest();
    }

    @Override
    public int insertAll(List<Abroad> list) {
        int res = 0;
        for(Abroad i : list) {
            res += mapper.insertAll(i);
        }
        return res;
    }
}
