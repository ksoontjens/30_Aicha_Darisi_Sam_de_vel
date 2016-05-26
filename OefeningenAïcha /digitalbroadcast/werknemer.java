public class werknemer implements Betaalbaar 
{
    // Dataleden
    public String voornaam;
    public String achternaam;
    public int werknemerNr;
    protected float salaris;
    private float RSZpercenage = 33.0F;
    

    public werknemer(String voornaam, String achternaam, int wNr, float salaris)    // Constructor, zelfde naam als klasse
    {
        // this.voornaam -> datalid     =   voornaam -> parameter
        this.voornaam   =   voornaam;
        this.achternaam =   achternaam;
        werknemerNr     =   wNr;
        this.salaris    =   salaris;
             
    }
        
    public void salarisVerhogen(int percentage)
    {
        salaris = salaris * (1 + ( percentage / 100.0f )); 
    }
    public float getSalaris()   //  getter
    {
        return salaris;   
    }
    
    public float getSalaris()
    {
        return salaris;
    }
    float getRSZ(){
        return RSZpercentage;
    }
    
    void setRSZ (float RSZ){
        RSZpercentage = RSZ;
    }
    
    public void betaal (){
        System.out.println("Betaal het salaris van" + voornaam + "aan de werknemer:" + salaris);
    }
    
    
}