package affinypay

import scala.util.{Failure, Success, Try}


sealed abstract class Clock(val hourRange:Range,  val minsRange:Range)

/**period consists of 12 hours:
  * 12- (as 0 for midnight and noon), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 and 11. */
case object Clock12 extends Clock(Range(0,12), Range(0,60))

/**period consists of 24 hours:
  * 12- (as 0 for midnight ), 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 ,
  * 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23 */
case object Clock24 extends Clock(Range(0,24), Range(0,60))

class HourMin (val h: Int, val m:Int, clock: Clock){
  override def toString: String = {
    if(h == 0){
      s"12:${m}"
    }else{
      s"${h}:${m}"
    }


  }
}


object HourMin{

   def apply(h:Int, m:Int, clock: Clock=Clock12) ={
    createSafe(h, m, clock)
  }

  def validHour(inputH: Int, clock: Clock) ={
    clock.hourRange.contains(inputH)
  }


  def validMin(inputM: Int, clock: Clock) ={
    clock.minsRange.contains(inputM)
  }

  def createSafe(h:Int, m:Int, clock: Clock): Try[HourMin] ={
    if(validHour(h,clock) && validMin(m, clock)){
      Success(new HourMin(h, m, clock))
    }
    else{
      Failure(new RuntimeException(
        s"""
           |Invalid input
           |Valid Hour ${clock.hourRange} : received ${h}
           |Valid Mins ${clock.minsRange}: received ${m}
               """.stripMargin))
    }


  }


  def parse(hmStr: String): Try[HourMin] = {

    val splitOnColon = hmStr.split(":").toList

    splitOnColon match {

      case hStr :: mStr :: Nil => {
        try {
          val hInt = hStr.toInt
          val mInt = mStr.toInt
          createSafe(hInt, mInt, Clock12)

        } catch {
          case e1: NumberFormatException => Failure(e1)
        }
      }

      case _ => Failure(new RuntimeException(s"Invalid Hour Min format. Received ${hmStr}, Expected Format : '[H]H:MM {AM|PM}"))

    }
  }



  def parse(hm: Int): Try[HourMin] = {
     val hours = hm / 60 // will just be int should be all we need

     val mins = hm % 60

     val hoursIn24 = if(hours >= 24){
       hours % 24
     }else{
       hours
     }

     HourMin.createSafe(hoursIn24, mins, Clock24)
   }


}