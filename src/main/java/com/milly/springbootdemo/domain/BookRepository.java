package com.milly.springbootdemo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(Pageable pageable);
    List<Book> findByAuthor(String author);
    List<Book> findByAuthorAndStatus(String author, int status);

    List<Book> findByDescriptionEndsWith(String des);

    //自定义查询
//    @Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book where length(name) > ?1", nativeQuery = true)
    List<Book>findByJPQL(int len);

    //自定义更新
    @Modifying
    @Transactional
    @Query("update Book b set b.status= ?1 where b.id = ?2")
    int updateByJPQL(int status, long id);

    //自定义更新
    @Modifying
    @Transactional
    @Query("delete from Book b where b.id = ?1")
    int deleteByJPQL(long id);
}
