public class ParttimeWerknemer extends wernemer {
    
    int urenGewerkt;
    
    
    
    public ParttimeWerknemer (String voornaam, String achternaam, int werknummer, float salaris,int urenGw){
        super(voornaam, achternaam, werknumbber, salaris);
        urenGewerkt = urenGw;
    }
    
    public void salarisVerhogen (int percentage) {
        if (percentage >5) {
            percentage=0;
            System.out.println("Percentage is hoger als 5")
        }
        else
        {
            super.salarisVerhogen(percentage);
        }
    }
    
   
    

}