package affinypay

import scala.util.{Failure, Success, Try}

sealed abstract class AMPM(val value: String, val minsRange:Range)

case object AM extends AMPM(value = "AM", minsRange = Range(0,720))
case object PM extends AMPM(value = "PM", minsRange = Range(720, 1440))


object AMPM{

  def parse(inputStr: String): Try[AMPM] = {

    inputStr match {
      case AM.value => Success(AM)
      case PM.value => Success(PM)
      case _ => Failure(new RuntimeException(s"Invalid AMPM format. Found ${inputStr}, Expected Format : '{AM|PM}"))
    }

  }


  def parse(mins: Int): Try[AMPM] = {

    if(mins < 0 ) Failure(new RuntimeException(s"Negative mins is invalid. Received = ${mins}"))
    else if(AM.minsRange.contains(mins)) Success(AM)
    else if(PM.minsRange.contains(mins)) Success(PM)
    else {
        parse(mins-1440) // greater than equal to 1400
      }
  }


}
