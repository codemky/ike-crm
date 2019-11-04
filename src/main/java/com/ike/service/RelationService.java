package com.ike.service;

import com.ike.pojo.Customer;
import com.ike.pojo.Relation;

import java.util.List;

/**
 * InterfaceName RelationService
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/11 21:38
 **/
public interface RelationService {

    /**
     * Author: mokuanyuan
     * @since:  2019/10/11 21:39
     */
    List<Relation> selectAll();

    int insert(Relation relation);

    int update(Relation relation);

    int deleteById(Long id);

    Relation selectById(Long id);

    Relation selectPrimaryByCid(Long cid);

    List<Relation> selectByCustomId(Long cid);

    int deleteByCustomerId(Long cid);

}
