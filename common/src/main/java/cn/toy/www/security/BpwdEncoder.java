package cn.toy.www.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Created by zhaoby on 2017/5/31.
 */
public class BpwdEncoder {

    private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

    /**
     * 用BCryptPasswordEncoder
     * @param password
     * @return
     */
    public static String  bcryptPassword(String password){
        return ENCODER.encode(password);
    }

    /**
     *
     * @param rawPassword 原始密码
     * @param encodedPassword 加密后的密码
     * @return
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword){
        return ENCODER.matches(rawPassword,encodedPassword);
    }





    public static void main(String [] args) {
        System.out.println(bcryptPassword("123456"));


        System.out.println(matches("E8zvZXDqzB1QT1pO", "$2a$10$4YbQLWeUKjSZA9vTS8F9COI9mL5FyaEqbj0/t7xNwgWHmMHsQV7XG"));
    }
}
