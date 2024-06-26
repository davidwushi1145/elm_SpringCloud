package cn.wushi.common;

import cn.wushi.exception.ConditionException;
import cn.wushi.util.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class UserSupport {
    public String getCurrentUserId() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader("token");
        JWTUtil jwtUtil = new JWTUtil();
        String userId = jwtUtil.verify(token);
        if (userId == null) {
            throw new ConditionException("非法用户！");
        }
        return userId;
    }
}
