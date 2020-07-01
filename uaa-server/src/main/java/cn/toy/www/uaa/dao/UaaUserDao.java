package cn.toy.www.uaa.dao;


import cn.youroem.www.user.SecurityUser;
import org.springframework.stereotype.Repository;


@Repository
public interface UaaUserDao {


    /**
     * 根据id获取用户信息
     *
     * @param id 用户主键
     * @return 用户信息
     */
    SecurityUser findById(String id);

    /**
     * 根据用户名获取用户信息
     *
     * @param userName 账号
     * @return 用户信息
     */
    SecurityUser findByUserName(String userName);
}
