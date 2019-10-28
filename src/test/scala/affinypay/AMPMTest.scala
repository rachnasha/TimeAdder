package affinypay


import org.scalatest._
import scala.util.Try


class AMPMTest extends FlatSpec with Matchers {

  "AMPM" should "be able to parse 720 as noon / 12:00 PM" in {

    val ampm: Try[AMPM] = AMPM.parse(720)

    ampm.isSuccess should be (true)
    ampm.get should be (PM)
  }

  "AMPM" should "be able to parse 1439 as (11:59) PM" in {

    val ampm: Try[AMPM] = AMPM.parse(1439)

    ampm.isSuccess should be (true)
    ampm.get should be (PM)
  }


  "AMPM" should "be able to parse 1440 as (12:00) AM" in {

    val ampm: Try[AMPM] = AMPM.parse(1440)

    ampm.isSuccess should be (true)
    ampm.get should be (AM)
  }

  "AMPM" should "be able to parse 2300 as PM" in {

    val ampm: Try[AMPM] = AMPM.parse(2300)

    ampm.isSuccess should be (true)
    ampm.get should be (PM)
  }


  "AMPM" should "be able to parse 5600 as  AM" in {

    val ampm: Try[AMPM] = AMPM.parse(5600)

    ampm.isSuccess should be (true)
    ampm.get should be (PM)
  }

}
