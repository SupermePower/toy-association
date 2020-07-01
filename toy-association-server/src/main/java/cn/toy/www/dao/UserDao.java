package cn.toy.www.dao;

import cn.toy.www.dingding.User;
import cn.toy.www.request.QueryUserListRequest;
import cn.toy.www.vo.UserListVO;
import cn.toy.www.vo.UserSelectedListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Zero
 * @Description: 用户DAO
 * @Date: 2020/4/8
 */
@Repository
public interface UserDao {

    User getUserByCorpUserId(String userId);

    User getUserByMobile(String mobile);

    void updateUser(User user);

    User findById(Long id);

    void updateStatus(@Param("status") Byte status, @Param("id") Long id, @Param("updateBy") Long updateBy);

    void deleteUser(@Param("id") Long id, @Param("updateBy") Long updateBy);

    void resetPassword(@Param("id") Long id, @Param("updateBy") Long updateBy, @Param("password") String password);

    void saveUser(User user);

    List<UserListVO> selectPage(QueryUserListRequest request);

    List<UserSelectedListVO> queryUserSelectedList();
}
