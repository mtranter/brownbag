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
    service.getUser(userId) match {
      case No => Seq()
      case Yes(user) => service.getUserAccount(user) match {
        case No => Seq()
        case Yes(userAccount) => service.getAccountTransactions(userAccount) match {
          case No => Seq()
          case Yes(transactions) => transactions
        }
      }
    }
  }

}
