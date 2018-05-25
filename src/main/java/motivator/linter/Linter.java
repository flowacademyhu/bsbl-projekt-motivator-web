package motivator.linter;

/*
CleanCode Linter:
	Min:
	- LoC fájl/function szinten
	- Egybe ágyazott if/while/for
	- Változó scope vizsgálat (használva van e? ha igen többször mint 1?)
	- Ne legyenek halmozott feltételek (beszédes)
	- Ékezet mentesség
	- Sortörés számláló
	- Function bemeneti paraméter számláló
	Max:
	- Ne legyen rövidítés
	- Nyelv felismerés
*/
public class Linter {
    public void stringParser(String input) {
        String[] rows = input.split("\n");
    }
}
