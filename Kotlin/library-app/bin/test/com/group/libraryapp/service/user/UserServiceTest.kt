package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository
import com.group.libraryapp.domain.user.loanhistory.UserLoanStatus
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest;
import java.awt.print.Book


@SpringBootTest
class UserServiceTest @Autowired constructor(
     private val userRepository: UserRepository,
     private val userService: UserService,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
) {


    @AfterEach
    fun afterEach(){
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("유저저장")
    fun 유저저장(){
        // given
        val request = UserCreateRequest("이름",10)

        // when
        userService.saveUser(request)

        // then
        val res = userRepository.findAll()
        assertThat(res[0].name).isEqualTo("이름")
        assertThat(res[0].age).isEqualTo(10)
    }

    @Test
    fun 유저조회(){
        // given
        val request = UserCreateRequest("이름",10)
        userRepository.saveAll(listOf(
            User("A", 20),
            User("B", 30),
            User("C", 40)
        ))

        // when
        val res = userService.getUsers()

        // then
        assertThat(res).hasSize(3)
        assertThat(res).extracting("name").containsExactlyInAnyOrder("A","B","C")  // ["A", "B","C"]
    }

    @Test
    fun 유저수정(){
        // given
        val savedUser = userRepository.save(User("A", null))
        val request = UserUpdateRequest(savedUser.id!!,"B")

        // when
        userService.updateUserName(request)

        // then
        val res = userRepository.findAll()[0]
        assertThat(res.name).isEqualTo("B")
    }

    @Test
    fun 유저삭제(){
        // given
        userRepository.save(User("A", null))

        // when
        userService.deleteUser("A")
        val res = userRepository.findAll()

        // then
        assertThat(res).isEmpty()
    }

    @Test
    fun 대출기록없는사람응답포함(){
        // given
        userRepository.save(User("A",null))

        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).isEmpty()
    }

    @Test
    fun 대출기록많은사람응답(){
        // given
        val savedUser = userRepository.save(User("A",null))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(savedUser,"책1",UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser,"책2",UserLoanStatus.LOANED),
            UserLoanHistory.fixture(savedUser,"책3",UserLoanStatus.RETURNED)
        ))


        // when
        val results = userService.getUserLoanHistories()

        // then
        assertThat(results).hasSize(1)
        assertThat(results[0].name).isEqualTo("A")
        assertThat(results[0].books).hasSize(3)
        assertThat(results[0].books).extracting("name").containsExactlyInAnyOrder("책1","책2","책3")
        assertThat(results[0].books).extracting("isReturn").containsExactlyInAnyOrder(false,false,true)
    }


}