package affinypay

import scala.util.{Failure, Success, Try}

case class HourMinuteWithAMPM(hm: HourMin, ampm: AMPM){

  def toHourClock12() = {
      val hAs12 = if(hm.h >= 12){
        hm.h % 12
      }else {
        hm.h
      }
      val newHM = HourMin(hAs12, hm.m, Clock12)
    newHM.map(HourMinuteWithAMPM(_, ampm))
    }


  def toHourClock24() =  ampm match {
    case AM => Success(HourMinuteWithAMPM(hm, ampm))
    case PM => {
      val newHM = HourMin(hm.h+12, hm.m, Clock24)
      newHM.map(HourMinuteWithAMPM(_, ampm))
    }
  }


  def addMinutes(minsToAdd: Int) = {


    val newHmWithAMPClock24 = for {
      hmm <- toHourClock24()
      hourOfDayInMins = (hmm.hm.h * 60 + hmm.hm.m)
      withAddedMins = (hourOfDayInMins + minsToAdd)
      newAMPM <- AMPM.parse(withAddedMins)
      newHourMins <- HourMin.parse(withAddedMins)
    } yield {
      HourMinuteWithAMPM(newHourMins, newAMPM)
    }

    newHmWithAMPClock24.fold(failed => Failure(failed), newHm24 => newHm24.toHourClock12())


  }


  override def toString: String = {
    s"${this.hm.toString} ${this.ampm.value}"
  }

}
