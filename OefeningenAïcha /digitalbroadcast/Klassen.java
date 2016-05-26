public class Klassen
{
    public static void main(String args[])
    {
        werknemer jan;
        jan = new werknemer("Jan", "Janssens", 1 , 20.0f);
        
        jan.betaal();
            
        werknemer herman = new werknemer("Herman", "Hermans", 2, 30.0f);
        werknemer piet = new werknemer("Piet", "Geerts",3,25.0f * 0.10);
        werknemer sandra = new werknemer ("Sandra", "Daens",2,15.0f);
        
        herman.salarisVerhogen(10);
        piet.salarisVerhogen(10);
        
        sandra.setRSZ(40);
        
        ParttimeWerknemer kevin = new ParttimeWerknemer("kevin","Levy",1,10.0f);
        
        kevin.salarisVerhogen(10);
        ParttimeWerknemer tom = new ParttimeWerknemer("tom","toms",5,5.0f);
        
        
        Faktuur object = new Faktuur();
        object.betaal();
            
        System.out.println(jan.voornaam + "verdient " + jan.getSalaris());
        System.out.println(herman.voornaam + "verdient " + herman.getSalaris());
        System.out.println(piet.voornaam + "verdient" + piet.getSalaris());
        System.out.println(sandra.voornaam + "verdient" + sandra.getSalaris());
    }
}