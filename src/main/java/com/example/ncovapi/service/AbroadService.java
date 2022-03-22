package com.example.ncovapi.service;

import com.example.ncovapi.entity.Abroad;

import java.util.List;

public interface AbroadService {
    List<Abroad> selectLatest();
    int insertAll(List<Abroad> list);
}
