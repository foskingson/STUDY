package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookRepository
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.book.request.BookLoanRequest
import com.group.libraryapp.dto.book.request.BookRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BookServiceTest @Autowired constructor(private val bookRepository: BookRepository,
                                             private val bookService: BookService,
                                             private val userRepository: UserRepository,
                                             private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {

    @AfterEach
    fun afterEach(){
        userRepository.deleteAll()
        bookRepository.deleteAll()
        userLoanHistoryRepository.deleteAll()
    }

    @Test
    fun 책저장(){
        // given
        val request = BookRequest("책",BookType.COMPUTER)

        // when
        bookService.saveBook(request)

        // then
        assertThat(bookRepository.findAll()[0].name).isEqualTo("책")
        assertThat(bookRepository.findAll()[0].type).isEqualTo(BookType.COMPUTER)
    }

    @Test
    fun 책대출(){
        // given
        bookRepository.save(Book.fixture("책"))
        userRepository.save(User("ㅇㅇㅇ", 10))
        val request = BookLoanRequest("ㅇㅇㅇ","책")

        // when
        bookService.loanBook(request)

        // then
        val res = userLoanHistoryRepository.findAll()
        assertThat(res).hasSize(1)
        assertThat(res[0].bookName).isEqualTo("책")
    }

    @Test
    fun 책대여권수정상확인(){
        // given
        val user = userRepository.save(User("ㅇㅇㅇ",null))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(user,"A"),
            UserLoanHistory.fixture(user,"B",UserLoanStatus.RETURNED),
            UserLoanHistory.fixture(user,"C",UserLoanStatus.RETURNED),
        ))

        // when
        val res = bookService.countLoanedBook()

        // then
        assertThat(res).isEqualTo(1)
    }

    @Test
    fun 분야별책통계확인(){
        // given
        bookRepository.saveAll(listOf(
            Book.fixture("A",BookType.COMPUTER),
            Book.fixture("B",BookType.COMPUTER),
            Book.fixture("C",BookType.SCIENCE),
        ))

        // when
        val res = bookService.getBookStatistics()

        // then
        assertThat(res).hasSize(2)
        val computerDto = res.first{result -> result.type == BookType.COMPUTER}
        assertThat(computerDto.count).isEqualTo(2)

        val scienceDto = res.first{result -> result.type == BookType.SCIENCE}
        assertThat(scienceDto.count).isEqualTo(1)
    }


}