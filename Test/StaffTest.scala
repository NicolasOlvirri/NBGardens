import com.WOTS.system._
import org.scalatest.{FlatSpec, Matchers, Tag}
/**
  * Created by Luke on 10/07/2016.
  */
class StaffTest extends FlatSpec with Matchers {
  //return true if the given ID is a staff member from accounts
  it should "return true if staff member from accounts" taggedAs ReturnRoleAccountSuccess in {
    Staff.returnRole("0003") shouldEqual true
  }

  //return false if the given ID is a staff member not from account
  it should "return false if staff member not from accounts" taggedAs ReturnRoleNotAccountSuccess in {
    Staff.returnRole("0001") shouldEqual false
  }

  //return false if the given ID is not found
  it should "return false if staff member Id invalid" taggedAs ReturnRoleFail in {
    Staff.returnRole("egergerg") shouldEqual false
  }
}

object ReturnRoleAccountSuccess extends Tag("ReturnRoleSuccess")
object ReturnRoleNotAccountSuccess extends Tag("ReturnRoleNotAccountSuccess")
object ReturnRoleFail extends Tag("ReturnRoleFail")