public class Faktuur implements Betaalbaar {
    public int faktuurNr;
    public float faktuurBedrag;
    
    public Faktuur (int FactuurNr, float factuurBedrag)
    {
        this.factuurBedrag = factuurBedrag;
        this.factuurNr= factuurNr;
    }
    
    public void betaal() {
        System.out.println("Betaal het faktuur" + faktuurNr + "van" + faktuurBedrag);
    }
}