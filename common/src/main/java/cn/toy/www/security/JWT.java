package cn.toy.www.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zby
 * 2018/07/12
 */
@Getter@Setter
public class JWT {

    @ApiModelProperty(value="access_token")
    private String access_token;

    @ApiModelProperty(value="token 类型")
    private String token_type;

    @ApiModelProperty(value="refresh_token")
    private String refresh_token;


    @ApiModelProperty(value="过期时间")
    private int expires_in;

    @ApiModelProperty(value="scope")
    private String scope;

    @ApiModelProperty(value="jti")
    private String jti;



    @Override
    public String toString() {
        return "JWT{" +
                "access_token='" + access_token + '\'' +
                ", token_type='" + token_type + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                ", scope='" + scope + '\'' +
                ", jti='" + jti + '\'' +
                '}';
    }
}
