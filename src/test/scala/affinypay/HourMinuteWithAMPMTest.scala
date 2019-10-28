package affinypay

import org.scalatest._

class HourMinuteWithAMPMTest extends FlatSpec with Matchers {

  "HourMinuteWithAMPM" should "add 200 mins to 9:13 AM and output 12:33 PM" in {

    val hm = HourMin(9, 13)
    val hmam = HourMinuteWithAMPM(hm.get, AM)

    val newHmAM = hmam.addMinutes(200)

    newHmAM.get.ampm should equal ( PM )
    newHmAM.get.hm.h should equal ( 0 )
    newHmAM.get.hm.m should equal ( 33 )
  }


  "HourMinuteWithAMPM" should "add 200 mins to 9:13 PM and output 12:33 AM" in {

    val hm = HourMin(9, 13)
    val hmam = HourMinuteWithAMPM(hm.get, PM)

    val newHmAM = hmam.addMinutes(200)

    newHmAM.get.ampm should equal ( AM )
    newHmAM.get.hm.h should equal ( 0 )
    newHmAM.get.hm.m should equal ( 33 )

    newHmAM.get.toString should equal ("12:33 AM")
  }


  "HourMinuteWithAMPM" should "add 886 mins to 9:13 AM and output 11:59 PM" in {

    val hm = HourMin(9, 13)
    val hmam = HourMinuteWithAMPM(hm.get, AM)

    val newHmAM = hmam.addMinutes(886)

    newHmAM.get.ampm should equal ( PM )
    newHmAM.get.hm.h should equal ( 11 )
    newHmAM.get.hm.m should equal ( 59 )

    newHmAM.get.toString should equal ("11:59 PM")
  }

  "HourMinuteWithAMPM" should "add 886 mins to 9:13 PM and output 11:59 AM" in {

    val hm = HourMin(9, 13)
    val hmam = HourMinuteWithAMPM(hm.get, PM)

    val newHmAM = hmam.addMinutes(886)

    newHmAM.get.ampm should equal ( AM )
    newHmAM.get.hm.h should equal ( 11 )
    newHmAM.get.hm.m should equal ( 59 )

    newHmAM.get.toString should equal ("11:59 AM")
  }


  "HourMinuteWithAMPM" should "add -20 mins to 1:23 AM and output 1:3 AM" in {

    val hm = HourMin(1,23)
    val hmam = HourMinuteWithAMPM(hm.get, AM)

    val newHmAM = hmam.addMinutes(-20)

    newHmAM.get.ampm should equal ( AM )
    newHmAM.get.hm.h should equal ( 1 )
    newHmAM.get.hm.m should equal ( 3 )

    newHmAM.get.toString should equal ("1:3 AM")
  }
}
