package com.ike.service;

import com.ike.pojo.Department;
import com.ike.pojo.Position;

import java.util.List;

public interface PositionService {
    public List<Position> selectAll();

    public int insert(Position position);

    public int update(Position position) ;

    public int deleteById(Long id);

    public Position selectById(Long id);

    public Position selectByPositionName(String positionName);
}
