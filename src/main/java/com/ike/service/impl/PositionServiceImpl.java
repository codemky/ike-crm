package com.ike.service.impl;

import com.ike.mapper.PositionMapper;
import com.ike.pojo.Position;
import com.ike.pojo.PositionExample;
import com.ike.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> selectAll() {
        return positionMapper.selectByExample(null);
    }

    @Override
    public int insert(Position position) {
        return positionMapper.insert(position);
    }

    @Override
    public int update(Position position) {
        return positionMapper.updateByPrimaryKeySelective(position);
    }

    @Override
    public int deleteById(Long id) {
        return positionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Position selectById(Long id) {
        return positionMapper.selectByPrimaryKey(id);
    }

    @Override
    public Position selectByPositionName(String positionName) {
        PositionExample example = new PositionExample();
        example.or().andPositionNameEqualTo(positionName);
        List<Position> positions = positionMapper.selectByExample(example);
        if (positions.size() == 0) {
            return null;
        }
        return positions.get(0);
    }
}
