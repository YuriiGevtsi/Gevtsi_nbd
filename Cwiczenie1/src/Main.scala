import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    println(" Zadanie 1 ")
    val days = List[String]("Poniedzialek", "Wtorek", "Sroda", "Czwartek", "Piatek", "Sobota", "Niedziela")
    println("Petla For")
    println(getListItemsByFor(days))
    //1b
    println("Petla For + P")
    println(getListItemsByForStartsWithP(days))
    //1c
    println("Petla While")
    println(getListItemsByWhile(days))

    println(" Zadanie 2 ")
    //2a
    println("Funkcji rekurencyjnej")
    println(recursive(days))
    //2b
    println("Funkcji rekurencyjnej wypisując elementy listy od końca)
    println(backwardsRecursive(days))

    println(" Zadanie 3 ")
    println(tailRecursive(days))

    println(" Zadanie 4 ")
    //4a
    println("Metody foldl")
    println(getListItemsByFoldl(days))
    //4b
    println("Metody foldr")
    println(getListItemsByFoldr(days))
    //4c
    println("Metody foldl wypisując tylko dni z nazwami zaczynającymi się na P")
    println(getListItemsByFoldlStartsWithP(days))

    println(" Zadanie 5 ")
    var products = Map("Product1" ->1.0, "Product2" -> 2.0)
    println("Do: " + products.map(_.productIterator.mkString(":")).mkString("; "))
    var products2 = getDiscount(products, 10);
    println("Po : " + products2.map(_.productIterator.mkString(":")).mkString("; "))

    println(" Zadanie 6 ")
    println(getTupleValues(!false, 1.0f, 1))

    println(" Zadanie 7 ")
    println(getMapItemValueByKey(products, "Product1"))

    println(" Zadanie 8 ")
    val integerValues = List[Int](1,2, 0, 4, 0, 0, 7, 0, 9)
    println("Do: " + integerValues)
    println("Po: " + getListWithoutZeros(integerValues))

    println(" Zadanie 9 ")
    val integerValues2  = List[Int](1,2, 0, 4, 0, 0, 7, 0, 9)
    println("Do: " + integerValues2)
    println("Po: " + getIncrementedList(integerValues2))

    println(" Zadanie 10 ")
    val realNumbers  = List[Double](-10.10, -5.5, -3.3, 0, 1.1, 10.10, 13.13)
    println("Do: " + realNumbers)
    println("Po: " + getAbsoluteValuesList(realNumbers, -5.0, 12.0))

  }
//Implementacja
//  Zd 1
//  1a
  def getListItemsByFor(list: List[String]): String = {
    var resultStr:String = ""
    for(item <- list) {
      resultStr += (if(item != list.head) ", " else "") + item
    }
    resultStr
  }
//  1b
  def getListItemsByForStartsWithP(list: List[String]): String = {
    var resultStr:String = ""
    for(item <- list if item.startsWith("P")) {
      resultStr += (if(item != list.head) ", " else "") + item
    }
    resultStr
  }
//  1c
  def getListItemsByWhile(list: List[String]): String = {
    var resultStr:String = ""
    var i:Int = 0
    while(i < list.length) {
      resultStr += (if(list(i) != list.head) ", " else "") + list(i)
      i+=1
    }
    resultStr
  }
// Zadanie 2
//  2a
  def recursive(list: List[String]): String = {
    if (list.length == 1) {
      list.head
    } else if (!list.isEmpty) {
      list.head + ", " + recursive(list.tail)
    } else {
      throw new Exception("List is Empty")
    }
  }
//  2b
  def backwardsRecursive(list: List[String]): String = {
    if (list.length == 1) {
      list.head
    } else if (!list.isEmpty) {
      backwardsRecursive(list.tail) + ", " + list.head
    } else {
      throw new Exception("List is Empty")
    }
  }
// Zadanie 3
  @tailrec def tailRecursive(list: List[String], result: String = ""): String = {
    if (list.head != list.last) {
      tailRecursive(list.tail, result.concat(list.head) + ", ")
    } else {
      result.concat(list.head)
    }
  }
// Zadanie 4
//  4a
  def getListItemsByFoldl(list: List[String]): String = {
    list.foldLeft("")((A, B) =>
      A + (if(B != list.head) ", " else "") + B
    )
  }
//  4b
  def getListItemsByFoldr(list: List[String]): String = {
    list.foldRight("")((A, B) =>
      (if(A != list.head) ", " else "") + A + B
    )
  }
//  4c
  def getListItemsByFoldlStartsWithP(list: List[String]): String = {
    list.foldLeft("")((A, B) =>
      A +(if (B.startsWith("P")) {
            ((if(B != list.head) ", " else "") + B)
          } else { "" })
    )
  }
// Zadanie 5
  def getDiscount(map: Map[String, Double], percent: Double): Map[String, Double] = {
    map.view.mapValues(cost => {
      cost  cost * 0.01 * percent
    }).toMap
  }
// Zadanie 6
  def getTupleValues(tuple: (Any, Any, Any)): String = {
    (tuple._1.getClass.getSimpleName + ": " + tuple._1 + ";\n" +
      tuple._2.getClass.getSimpleName + ": " + tuple._2 + ";\n" +
      tuple._3.getClass.getSimpleName + ": " + tuple._3 + ";")
  }
// Zadanie 7
  def getMapItemValueByKey(map: Map[String, Double], key: String): Option[Double] = {
    map.get(key)
  }
// Zadanie 8
  @tailrec
  def getListWithoutZeros(list: List[Int], result: List[Int] = List.empty[Int]): List[Int] = list match {
    case head::tail => {
      if (head != 0) {
        getListWithoutZeros(tail, result.appended(head))
      } else {
        getListWithoutZeros(tail, result)
      }
    }
    case Nil => result
  }
// Zadanie 9
  def getIncrementedList(list: List[Int]): List[Int] = {
    list.map(value => value + 1)
  }
// Zadanie 10
  def getAbsoluteValuesList(list: List[Double], min: Double, max: Double): List[Double] = {
    list.filter(value => value >= min && value <= max)
      .map(value => value.abs)
  }

}