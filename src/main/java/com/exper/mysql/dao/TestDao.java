package com.exper.mysql.dao;

import com.exper.mysql.config.DynamicDataSource;
import com.exper.mysql.enums.DataBaseType;
import com.exper.mysql.model.TestRes;
import com.exper.mysql.model.req.TestReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 1E7753
 * @date 2022/8/13 16:40
 * @todo
 */
@Mapper
public interface TestDao {

   /**
    * 通过注解实现读写分离
    * @param req
    * @return
    */
   @Select("select a a, b b FROM test_t WHERE id=#{id}")
   @DynamicDataSource(name = DataBaseType.SLAVE_FIRST)
   TestRes selectRes(TestReq req);

   /**
    * 注解实现读写分离
    * @param req
    * @return
    */
   @DynamicDataSource(name=DataBaseType.MASTER)
   @Insert("INSERT  INTO test_t(a,b)VALUE(#{a},#{b})")
   int insertRes(TestReq req);

   /**
    * ShardingJDBC实现读写分离
    * @param req
    * @return
    */
   @Update("update test_t set a=#{a} where id=#{id}")
   int hcwTest(TestReq req);

   /**
    * ShardingJDBC实现读写分离
    * @param req
    * @return
    */
   @Select("select a a, b b FROM test_t WHERE id=#{id}")
   TestRes selectResInfo(TestReq req);
}
