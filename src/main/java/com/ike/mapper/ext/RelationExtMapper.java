package com.ike.mapper.ext;

import com.ike.pojo.Relation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * InterfaceName RelationExtMapper
 * Description TODO
 *
 * @author mokuanyuan
 * @version 1.0
 * @date 2019/10/22 10:10
 **/
@Repository
public interface RelationExtMapper {

    /**
     * Author: mokuanyuan
     *
     * @param relationList 客户联系人列表
     * @return int 更新的记录数
     * @apiNote: 批量导入客户联系人
     * @since: 2019/10/22 9:15
     */
    int importExcel(@Param("RelationList") List<Relation> relationList);

}
