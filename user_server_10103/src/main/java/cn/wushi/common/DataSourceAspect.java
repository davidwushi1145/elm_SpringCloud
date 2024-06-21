package cn.wushi.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import cn.wushi.common.DataSourceContextHolder;
import cn.wushi.common.DataSourceType;


@Aspect
@Component
public class DataSourceAspect {

    @Before("execution(* cn.wushi.service..*.save*(..)) || execution(* cn.wushi.service..*.update*(..)) || execution(* cn.wushi.service..*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
    }

    @Before("execution(* cn.wushi.service..*.find*(..)) || execution(* cn.wushi.service..*.get*(..)) || execution(* cn.wushi.service..*.query*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE);
    }
}

