package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.book.Book
import com.group.libraryapp.domain.book.BookType
import com.group.libraryapp.domain.user.User
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class UserLoanHistory(
    @ManyToOne
    val user: User,

    val bookName: String,

    var status: UserLoanStatus = UserLoanStatus.LOANED,


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    fun doReturn(){
        this.status = UserLoanStatus.RETURNED
    }

    val isReturn: Boolean
        get() = this.status == UserLoanStatus.RETURNED

    companion object{
        fun fixture(
            user: User,
            name: String = "책 이름",
            status: UserLoanStatus = UserLoanStatus.LOANED,
        ): UserLoanHistory{
            return UserLoanHistory(
                user = user,
                bookName = name,
                status = status,
            )
        }
    }
}