

object Main {

  def main(args: Array[String]): Unit = {
    println("Zadanie 1")
    println("PONIEDZIAŁEK: " + getDayInfo("PONIEDZIAŁEK"))
    println("PIĄTEK: " + getDayInfo("Error"))
    println("SOBOTA: " + getDayInfo("SOBOTA"))
    println("NIEDZIELA: " + getDayInfo("NIEDZIELA"))

    println("Zadanie 2")
    println("Konto Bankowe stan konta  0")
    var kontoBankowe1 = new KontoBankowe()
    println("Stan konta : " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wplata(123)
    println("Stan konta : " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wyplata(12)
    println("Stan konta : " + kontoBankowe1.getStanKonta)
    kontoBankowe1.wyplata(123)
    println("Stan konta : " + kontoBankowe1.getStanKonta)
    println("Konto Bankowe 2")
    var kontoBankowe2 = new KontoBankowe(123456)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)
    kontoBankowe2.wyplata(12345)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)
    kontoBankowe2.wplata(123456)
    println("Stan konta 2: " + kontoBankowe2.getStanKonta)

    println("Zadanie 3")
    var osoba1 = new Osoba("O1", "N1")
    var osoba2 = new Osoba("O2", "N2")

    var listOsob = List[Osoba](osoba1, osoba2);
    for(osoba <- listOsob) {
      println(osoba.imie + " " + osoba.nazwisko + ": " + patternMatching(osoba))
    }

    println("Zadanie 4")
    println(useFunctionThreeTimes(3, multiplyByNine));

    println("Zadanie 5")
    val nowaOsoba = new Osoba2("Imie", "Nazwisko")
    println("Podatek Osoby: " + (nowaOsoba.podatek * 100)")
    val student = new Osoba2("Student", "Nazwisko") with Student
    println("Podatek Studenta: " + (student.podatek * 100))
    val pracownik = new Osoba2("pracownik", "Nazwisko") with Pracownik
    println("Podatek Pracownika: " + (pracownik.podatek * 100))
    val nauczyciel = new Osoba2("nauczyciel", "Nazwisko") with Nauczyciel
    println("Podatek Nauczyciela: " + (nauczyciel.podatek * 100))
    val nauczycielPracownik = new Osoba2("nauczycielPracownik", "Nazwisko") with Nauczyciel with Pracownik
    println("Podatek Nauczyciela-Pracownika: " + (nauczycielPracownik.podatek * 100))
    val pracownikNauczyciel = new Osoba2("pracownikNauczyciel", "Nazwisko") with Pracownik with Nauczyciel
    println("Podatek Pracownika-Nauczyciela: " + (pracownikNauczyciel.podatek * 100))
  }


//  Implementacja
//Zadanie 1
  def getDayInfo(day: String): String = day.toUpperCase match {
    case "PONIEDZIAŁEK"  =>   "Praca"
    case "WTOREK"        =>   "Praca"
    case "ŚRODA"         =>   "Praca"
    case "CZWARTEK"      =>   "Praca"
    case "PIĄTEK"        =>   "Praca"
    case "SOBOTA"        =>   "Weekend"
    case "NIEDZIELA"     =>   "Weekend"
    case  _              =>   "Nie ma takiego dnia"
  }
  //Zadanie 2
  class KontoBankowe() {
    private var stanKonta:Double = 0
    def getStanKonta = { stanKonta }

    def this(stanKonta:Double){
      this()
      this.stanKonta = stanKonta
    }

    def wplata(value: Double) {
      stanKonta += value
      println(value + " zostało wpłacone na konto.")
    }

    def wyplata(value: Double) {
      if (value <= stanKonta) {
        stanKonta -= value
        println(value + " zostało wypłacone z konta.")
      } else {
        println("Brak wystarczających środków na koncie (" + value + ").")
      }
    }
  }
//Zadanie 3
  case class Osoba(imie: String, nazwisko: String)
  def patternMatching(osoba: Osoba) = osoba match {
    case Osoba("O1", "N1") => "Hi O2"
    case Osoba("O2", _) => "Hi O1"
    case Osoba(_, "N2") => "Hi again O1"
    case default => "Hello."
  }
//Zadanie 4
  def multiplyByNine(value:Int):Int = { value * 9 }
  def useFunctionThreeTimes(value: Int, func: Int => Int): Int = {
    func(func(func(value)))
  }
}

//Zadanie 5
  class Osoba2() {
    private var _imie: String = ""
    private var _nazwisko: String = ""
    private var _podatek: Double = 0

    def this(imie: String, nazwisko: String){
      this()
      this._imie = imie
      this._nazwisko = nazwisko
      this._podatek = 0
    }

    def imie : String = { _imie }
    def nazwisko : String = { _nazwisko }
    def podatek : Double = { _podatek }
  }

  trait Student extends Osoba2 {
    override def podatek:Double = 0.0
  }

  trait Pracownik extends Osoba2 {
    private var _pensja:Double = 0

    private def setPensja(value: Double) {
      _pensja = value
    }

    private def getPensja: Double = if(_pensja > 0) { _pensja * podatek } else { 0 }

    override def podatek: Double = 0.20
  }

  trait Nauczyciel extends Pracownik {
    override def podatek: Double = 0.10
  }