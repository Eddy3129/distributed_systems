import scala.collection.mutable.ArrayBuffer
import scala.io.StdIn

class SaleItem(var desc: String, var price: Double)

class SaleTransaction(val items: ArrayBuffer[SaleItem], var payment: Double) {
  def totalAmount: Double = items.map(_.price).sum
  def change: Double = payment - totalAmount
}

object Shop {
  val transactions = new ArrayBuffer[SaleTransaction]()
  def totalSales: Double = transactions.map(_.totalAmount).sum
}

object POS {
  def main(args: Array[String]): Unit = {
    do {
      val saleItems = new ArrayBuffer[SaleItem]()
      do {
        val desc = StdIn.readLine("Enter item description = ")
        val price = StdIn.readLine("Please enter item price = ").toDouble
        val item = new SaleItem(desc, price)
        saleItems += item
      } while (StdIn.readLine("Do you have next item? Y/N = ").toUpperCase == "Y")

      val transaction = new SaleTransaction(saleItems, payment = 0.0)
      println(f"The total amount for all items is ${transaction.totalAmount}%.2f")

      val payment = StdIn.readLine("Please enter payment amount: ").toDouble
      transaction.payment = payment
      println(f"The change for you is ${transaction.change}%.2f")

      Shop.transactions += transaction
    } while (StdIn.readLine("Next customer please? Y/N = ").toUpperCase == "Y")

    println(f"The total sales is ${Shop.totalSales}%.2f")
  }
}