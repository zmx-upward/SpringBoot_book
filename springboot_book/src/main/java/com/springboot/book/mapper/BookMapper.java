package com.springboot.book.mapper;

import com.springboot.book.entity.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book")
    List<Book> listAll();

    @Select("select * from book where id=#{id}")
    Book findById(int id);

    @Insert("insert into book (name, num) + values (#{name,jdbcType=VARCHAR}, #{num,jdbcType=INTEGER})")
    int insert(Book book);

    @Delete("delete from book where id=#{id}")
    boolean delete(int id);

    @Update("update book set name=#{name},num=#{num} where id=#{id}")
    boolean update(Book book);

}
