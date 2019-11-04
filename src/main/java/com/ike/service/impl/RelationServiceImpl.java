package com.ike.service.impl;

import com.ike.mapper.RelationMapper;
import com.ike.pojo.Relation;
import com.ike.pojo.Relation;
import com.ike.pojo.RelationExample;
import com.ike.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName RelationServiceImpl
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/11 21:39
 **/
@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private RelationMapper relationMapper;

    @Override
    public List<Relation> selectAll() {
        return relationMapper.selectByExample(null);
    }

    @Override
    public int insert(Relation relation) {
        return relationMapper.insertSelective(relation);
    }

    @Override
    public int update(Relation relation) {
        return relationMapper.updateByPrimaryKeySelective(relation);
    }

    @Override
    public int deleteById(Long id) {
        return relationMapper.deleteByPrimaryKey(id)  ;
    }

    @Override
    public Relation selectById(Long id) {
        return relationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Relation selectPrimaryByCid(Long cid) {
        RelationExample relationExample = new RelationExample();
        relationExample.createCriteria().andRelationPrimaryEqualTo((byte) 0).andCustomerIdEqualTo(cid);
        List<Relation> relations = relationMapper.selectByExample(relationExample);
        return relations.size() > 0 ? relations.get(0) : null;
    }

    @Override
    public List<Relation> selectByCustomId(Long cid) {
        RelationExample relationExample = new RelationExample();
        relationExample.createCriteria().andCustomerIdEqualTo(cid);
        return relationMapper.selectByExample(relationExample);
    }

    @Override
    public int deleteByCustomerId(Long cid) {
        RelationExample relationExample = new RelationExample();
        relationExample.createCriteria().andCustomerIdEqualTo(cid);
        return relationMapper.deleteByExample(relationExample);
    }
}
