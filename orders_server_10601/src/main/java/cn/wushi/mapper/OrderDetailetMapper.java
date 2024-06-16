package cn.wushi.mapper;

import cn.wushi.po.OrderDetailet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface OrderDetailetMapper extends BaseMapper<OrderDetailet> {
    public void saveOrderDetailetBatch(List<OrderDetailet> list) throws SQLException;

    public List<OrderDetailet> listOrderDetailetByOrderId(Integer orderOd) throws SQLException;
}