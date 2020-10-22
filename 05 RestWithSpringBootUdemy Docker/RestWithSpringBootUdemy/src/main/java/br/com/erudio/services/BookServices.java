package br.com.erudio.services;

import br.com.erudio.converter.DozerConverter;
import br.com.erudio.data.model.Book;
import br.com.erudio.data.vo.v1.BookVO;
import br.com.erudio.exception.ResourceNotFoundException;
import br.com.erudio.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public BookVO create(BookVO book) {
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;
    }

    public Page<BookVO> findAll(Pageable pageable) {
        var page = bookRepository.findAll(pageable);
        return page.map(this::convertToBookVO);
    }

    private BookVO convertToBookVO(Book entity) {
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public BookVO findById(Long id) {
        var entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerConverter.parseObject(entity, BookVO.class);
    }

    public BookVO update(BookVO book) {
        var entity = bookRepository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        entity.setAuthor(book.getAuthor());
        entity.setLaunch_date(book.getLaunch_date());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());
        var vo = DozerConverter.parseObject(bookRepository.save(entity), BookVO.class);
        return vo;
    }

    public void delete(Long id) {
        Book entity = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        bookRepository.delete(entity);
    }
}
