import org.scalatest._

/**
  * Created by kishoreyakkala on 11/03/17.
  */
class TCUsingFunSuite extends FunSuite with Matchers {

  test("TestCase2") {
    1 should be(1)
  }

  test("TestCase1") {
    assert(0 == 0)
  }


}
