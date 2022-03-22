package com.example.ncovapi.dao;

import com.example.ncovapi.entity.Abroad;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AbroadMapper {
    Abroad selectCountry(String countryName);

    ArrayList<Abroad> selectLatest();

    int insertAll(Abroad abroad);

}
