package affinypay

import org.scalatest._


class HourMinTest extends FlatSpec with Matchers {

  "HourMin" should "be able to parse correct format" in {

    val inputTimeStr = "9:13"

    val mayBeHourMins = HourMin.parse(inputTimeStr)

    mayBeHourMins.isSuccess should be (true)
    mayBeHourMins.get.h should be (9)
    mayBeHourMins.get.m should be (13)
  }


  "HourMin" should "not parse invalid format" in {

    val inputTimeStr = "9:13 AM"

    val mayBeHourMins = HourMin.parse(inputTimeStr)

    mayBeHourMins.isFailure should be (true)
  }


  "HourMin" should "be able parse int to valid HourMin" in {


    val hourMins = HourMin.parse(753)

    hourMins.isSuccess should be(true)
    hourMins.get.h should be (12)
    hourMins.get.m should be (33)
  }

}
