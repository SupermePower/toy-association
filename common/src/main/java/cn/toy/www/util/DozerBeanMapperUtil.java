package cn.toy.www.util;


import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * @author zby
 * @version 1.0.0
 * 2018/07/12
 */
public class DozerBeanMapperUtil {

    private DozerBeanMapperUtil(){};

    private final static Mapper MAPPER = new DozerBeanMapper();

    public static <T> T dozerBeanMapper(Object obj1, Class<T> obj2){
        return MAPPER.map(obj1,obj2);
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        MAPPER.map(source, destinationObject);
    }
}