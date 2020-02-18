package com.milly.springbootdemo.service;

import com.milly.springbootdemo.domain.Book;
import com.milly.springbootdemo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    //  find all book list
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // find by page
    public Page<Book> findAllByPage(Pageable pageable) {
       // Sort sort = new Sort(Sort.Direction.DESC, "id");
        //Pageable pageable = new PageRequest(1,5,  Sort.unsorted());
        return bookRepository.findAll(pageable);
    }


    //  add one book
    public Book save(Book book) {
        return bookRepository.save(book);

    }
    //  find one book
//    public Optional<Book> findOne(long id) {
//        return bookRepository.findById(id);
//    }
    public Book findOne(long id) {
        Optional<Book> o = bookRepository.findById(id);
        Book book = new Book();
        if (o.isPresent()) {
            book = bookRepository.findById(id).get();
            return book;
        }
        return null;
    }

    //  delete one book
    public void deleteOne(long id) {
        bookRepository.deleteById(id);
    }

    //find book list by author
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    //find by author and status
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }

    //find by description
    public List<Book> findByDescriptionEndsWith(String des) {
        return bookRepository.findByDescriptionEndsWith(des);
    }

    // find by JPQL 自定义查询
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }

    //自定义更新
    @Transactional
    public int updateByJPQL(int status, long id) {
        return bookRepository.updateByJPQL(status, id);
    }

    //自定义删除
    @Transactional
    public int deleteByJPQL(long id) {
        return bookRepository.deleteByJPQL(id);
    }
    //测试事务操作方法
    public int deleteAndUpdate(long id, int status, long uid) {
        int dCount = bookRepository.deleteByJPQL(id);
        int uCount = bookRepository.updateByJPQL(status, uid);
        return dCount + uCount;
    }
}
