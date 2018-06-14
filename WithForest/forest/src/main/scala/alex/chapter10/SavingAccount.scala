package alex.chapter10

class SavingAccount extends GoodLogger {

    def withdraw(amount:Double): Unit ={
        if(amount >100000){
            log(amount)
        } else if(amount>10000 &amount <=99000){
            info(amount)
        }else if(amount>1000 &amount <=9900){
            warn(amount)
        }else {
            fatal(amount)
        }
    }

    override def savelog(path: String): Unit = {
        println("the log file will stored at "+path)
    }
}

object SavingAccount{
    def main(args: Array[String]): Unit = {
        val sat = new SavingAccount()
        sat.withdraw(80000)
    }
}

