package affinypay

import scala.util.{Failure, Success, Try}

/**
  * Responsible for reading and writing from/to file.
  */

object TimeApp extends App {


  if (args.length < 2) {
    throw new RuntimeException(s"Need 2 arguments, 12 hour time string and minutes ")
  } else {

    val timeString = args(0)
    val minuteString = args(1)
    val timeWithMinsAdded = addMinutes(timeString, minuteString)
    timeWithMinsAdded match{
      case Success(hmWithAmPM) => println(s"${hmWithAmPM.toString()}")
      case Failure(e) => e.printStackTrace()
    }


  }

  def addMinutes(hmStr: String, minToAddStr: String) = {

    val hourMinuteWithAMPM = parse(hmStr)

    hourMinuteWithAMPM.flatMap { hm =>
      val minInt: Try[Int] = Try {
        minToAddStr.toInt
      }

      minInt.flatMap { m =>
        hm.addMinutes(m)
      }
    }

  }



  def parse(input: String): Try[HourMinuteWithAMPM]  = {

    val splitOnAMPM = input.split(" ").toList

    splitOnAMPM match {
      case hmStr :: ampmStr :: Nil => {
        AMPM.parse(ampmStr).flatMap {
          ampm =>
            HourMin.parse(hmStr).map(HourMinuteWithAMPM(_, ampm))
        }
      }

      case _ => Failure(new RuntimeException(s"Invalid String format. Found ${input}, Expected Format : '[H]H:MM {AM|PM}"))

    }

  }

}












