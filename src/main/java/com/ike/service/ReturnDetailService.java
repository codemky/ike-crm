package com.ike.service;

import com.ike.pojo.ReturnDetail;
import com.ike.pojo.ReturnPlan;
import com.ike.pojo.vo.ReturnDetailSearchVo;
import com.ike.pojo.vo.ReturnDetailStatisitcVo;
import com.ike.pojo.vo.ReturnDetailVo;

import java.util.List;
import java.util.Map;

public interface ReturnDetailService {

    /**
     * 显示所有回款记录
     * @return
     */
    List<ReturnDetail> selectAll();

    /**
     * 新增回款记录
     * @param returnDetail
     * @return
     */
    int insert(ReturnDetail returnDetail);

    /**
     * 更新回款记录
     * @param returnDetail
     * @return
     */
    int update(ReturnDetail returnDetail);

    /**
     * 根据主键id删除回款记录，同时修改相应订单的回款金额和订单状态为2(付款中)
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据id选择获取回款记录
     * @param id
     * @return
     */
    ReturnDetail selectById(Long id);

    /**
     * 根据订单ID查询回款记录，并添加对应ID的人员名字
    * @params [orderBaseId]
    * @Return java.util.List<com.ike.pojo.ReturnDetail>
    * @Date 2019/10/12 2:16
    **/
    List<ReturnDetailVo> selectByOrderBaseId(Long orderBaseId);

    /**
     * 根据客户联系人ID查询回款记录，并添加对应ID的人员名字
     * @params [orderBaseId]
     * @Return java.util.List<com.ike.pojo.ReturnDetail>
     * @Date 2019/10/12 2:16
     **/
    List<ReturnDetailVo> selectByRelationId(Long relationId);

    /**
    * 根据订单ID查询所有的回款记录个数
    * @params [orderBaseId]
    * @Return int
    * @Date 2019/10/12 2:26
    **/
    int countId(Long orderBaseId);

    /**
     * 更新负责人id
     * @param oldEmployeeId 旧的负责人id
     * @param newEmployeeId 新的负责人id
     * @return
     */
    int updateAllByEmployeeId(Long oldEmployeeId,Long newEmployeeId);

    /**
     * 通过订单ID查询该订单的回款进度
     *
     * @params [id]
     * @Return java.lang.Double
     * @Date 2019/10/18 21:18
     **/
    double selectProgress(Long id);

    /**
     * 多条件搜索回款记录
     *
     * @Description TODO
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailSearchVo>
     * @Author shidacaizi
     * @Date 2019/10/20 22:19
     **/
    List<ReturnDetailVo> listReturnDetailSearch(ReturnDetailSearchVo returnDetailSearchVo);

    /**
     * 新增回款记录信息，并修改订单的相应信息
     *
     * @params [returnDetail]
     * @Return int
     * @Date 2019/10/21 23:11
     **/
    int insertReturnDetail(ReturnDetail returnDetail);

    /**
     * 根据时间,负责人id,状态进行多条件查询回款记录
     *
     * @params [returnDetailStatisitcVo]
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/22 8:52
     **/
    List<ReturnDetailVo> searchReturnDetailByTime(ReturnDetailStatisitcVo returnDetailStatisitcVo);

    /**
     * 批量插入回款记录
     * @param returnDetailList
     * @return
     */
    int insertList(List<ReturnDetail> returnDetailList);

    /**
     * @params []
     * @Return java.util.List<com.ike.pojo.vo.ReturnDetailVo>
     * @Date 2019/10/24 10:27
     **/
    List<ReturnDetailVo> selectAllList();

    /**
     * 导入excel回款信息到数据库表，并更新订单相关数据
     * @param getSumMap 以订单为key，订单总回款金额为value的map集合
     * @param stateMap 以订单为key，状态为value的map集合
     * @param returnDetail 要插入的汇款记录集合
     * @return
     */
    int importExcel(Map<String, Double> getSumMap, Map<String, Byte> stateMap, List<ReturnDetail> returnDetail);

    /**
     * 根据回款ID的数组进行批量删除，同时修改相应订单的回款金额和订单状态为3(付款中)
     *
     * @params [returnDetailId]
     * @Return int
     * @Date 2019/10/28 18:08
     **/
    int deleteBatch(Long[] returnDetailId);
}
