package cn.toy.www.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * 获取token
 * @author qimy
 */
@Log4j2
@Component
public class TokenUtils {

//    @Autowired
//    private UaaService uaaService;
//
//    /**
//     * 获取调用接口获取token
//     * @return token
//     */
//    public String getAdminToken(){
//        JWT data = uaaService.getToken(com.xiongmaoxingchu.common.Constant.Auth.HTTP_BASIC_PREV +
//                        com.xiongmaoxingchu.common.Constant.ServiceClient.BASIC_AUTHENTICATION,
//                "password", com.xiongmaoxingchu.common.Constant.USER_LOGIN_USERNAME,
//                com.xiongmaoxingchu.common.Constant.USER_LOGIN_PASSWORD);
//        if(Objects.nonNull(data)){
//            return com.xiongmaoxingchu.common.Constant.Auth.TOKEN_VALUE_PREV + data.getAccess_token();
//        }
//        return null;
//    }
}
