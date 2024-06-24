package cn.wushi.mapper;

import cn.wushi.po.DeliveryAddress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface DeliveryAddressMapper extends BaseMapper<DeliveryAddress> {
    @Select("select * from deliveryaddress where userId=#{userId} and isDelete=0 order by daId")
    public List<DeliveryAddress> listDeliveryAddressByUserId(@Param("userId") String userId) throws SQLException;

    @Select("select * from deliveryaddress where daId=#{daId} and isDelete=0")
    public DeliveryAddress getDeliveryAddressById(@Param("daId") Integer daId) throws SQLException;

    @Insert("insert into deliveryaddress values(null,#{contactName},#{contactSex},#{contactTel},#{address},#{userId},0)")
    public int saveDeliveryAddress(@Param("userId") String userId,
                                   @Param("contactName") String contactName,
                                   @Param("contactSex") Integer contactSex,
                                   @Param("contactTel") String contactTel,
                                   @Param("address") String address) throws SQLException;

    @Update("update deliveryaddress set contactName=#{contactName},contactSex=#{contactSex},contactTel=#{contactTel},address=#{address} where daId=#{daId}")
    public int updateDeliveryAddress(@Param("daId") Integer daId,
                                     @Param("contactName") String contactName,
                                     @Param("contactSex") Integer contactSex,
                                     @Param("contactTel") String contactTel,
                                     @Param("address") String address) throws SQLException;

    /**
     * 新删除，保留元素
     *
     * @param daId
     * @throws SQLException
     * @returnand isDelete=0
     */
    @Update("update deliveryaddress set isDelete = 1 where daId=#{daId}")
    public int removeDeliveryAddress(@Param("daId") Integer daId) throws SQLException;

}
