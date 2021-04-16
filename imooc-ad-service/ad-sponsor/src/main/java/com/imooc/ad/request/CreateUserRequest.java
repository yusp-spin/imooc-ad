package com.imooc.ad.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

/**
 * @author spin
 * @date 2021/4/1613:46
 * @description: TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    private String userName;

    /**
    * @description: TODO 验证参数是否有效
    * @author yusp
    * @date 2021/4/16 13:47
    */
    public boolean validate() {
        return !StringUtils.isEmpty(userName);
    }

}
