package org.judo.brownbag

/**
  * Created by mark on 14/08/16.
  */

case class User(name: String)
case class UserAccount(accNo: String)
case class AccountTransaction(amount: Double)

trait AccountService{
  def getUser(userId: String): Maybe[User]
  def getUserAccount(u: User): Maybe[UserAccount]
  def getAccountTransactions(ua: UserAccount): Maybe[Seq[AccountTransaction]]
}

class AccountsController(service: AccountService) {

  def getUserTransactions(userId: String): Seq[AccountTransaction] = {

    val retval = for {
      user <- service.getUser(userId)
      userAccount <- service.getUserAccount(user)
      transactions <- service.getAccountTransactions(userAccount)
    } yield transactions

    if(retval.hasValue) retval.value else Seq()
  }

}
