package org.judo.brownbag

import org.scalatest.FunSpec
import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor2}

/**
  * Created by mark on 14/08/16.
  */
class AccountControllerSpec extends FunSpec with TableDrivenPropertyChecks{

  describe("AccountController"){
    describe("#getUserTransactions"){
      it("should return appropriate maybe value"){
        
        forAll(examples) { (service: AccountService, result: Seq[AccountTransaction]) =>

          val sut = new AccountsController(service)

          val actual = sut.getUserTransactions("judo")
          assert(result === actual)

        }
      }
    }
  }


  val examples: TableFor2[AccountService, Seq[AccountTransaction]] = Table(
    ("service", "result"),
    (MockAccountService(), Seq()),
    (MockAccountService("judo"), Seq()),
    (MockAccountService("judo", "j123"), Seq()),
    (MockAccountService("judo", "j123", Seq(5d,4d,3d)), Seq(5d,4d,3d) map AccountTransaction)
  )

  object MockAccountService {
    def apply(): MockAccountService = new MockAccountService(No, No,No)
    def apply(userName: String) = new MockAccountService(Yes(User(userName)), No, No)
    def apply(username: String, accNo: String) = new MockAccountService(Yes(User(username)), Yes(UserAccount(accNo)), No)
    def apply(username: String, accNo: String, amounts: Seq[Double]) =
      new MockAccountService(Yes(User(username)), Yes(UserAccount(accNo)), Yes(amounts map AccountTransaction))
  }

  class MockAccountService(user: Maybe[User], account: Maybe[UserAccount], transactions: Maybe[Seq[AccountTransaction]]) extends AccountService {
    override def getUser(userId: String): Maybe[User] = user

    override def getUserAccount(u: User): Maybe[UserAccount] = account

    override def getAccountTransactions(ua: UserAccount): Maybe[Seq[AccountTransaction]] = transactions
  }
}
