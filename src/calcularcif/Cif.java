package calcularcif;
/**
 * Clase que utilizamos para validar el CIF de una empresa.
 * @author Fran
 * @version 1.0, 29/11/2020
 */
public class Cif {
    
    private final String letrasCif; // Caracteres validos pra el CIF.
    private String cadenaCif; // Contiene toda la cadena del CIF.
    private String letraCif; // Almacena la letra del CIF.
    //El siguiente atributo (ultimoDigitoCIF) es opcional:
    private String ultimoDigitoCif; // Contiene el último dígito del CIF.
    // Contiene los dígitos para calcular el dígito control.
    private String calculoDigitoControl;
    private int sumaPares; // Suma de números de las posiciones pares.
    private int sumaImpares; // Suma de números de las posiciones impares.
    private int sumaTotal; // Guarda la suma total de pares e impares.
    private int unidades; // Almacena el dígito de las uds. de la suma total.
    private int resultadoResta; // Guarda el resultado de restar las uds. a 10.
    /* Guarda los caracteres control por lo que puede comenzar un CIF.*/
    private final char[] letraControl;
    /*En caso de que el CIF, comience por una letra el caracter control
    será una letra almacenada en este atributo.*/
    private final String primerCaracterLetra;
    private String caracterControl; // Guarda un caracter control tipo letra
    private String digitoControl; // Guarda un digito control de tipo numérico.
    private String cifValido; // Almacena un CIF cuyo dígito control es válido.
    
    /**
     * Crea un objeto de tipo Cif
     * @param cif: cadena de 9 caracteres introducida por el usuario.
     */
    public Cif(String cif){
        // Iniciamos las cadenas necesarias.
        this.letrasCif = "ABCDEFGHJKLMNPQRSUVW";
        this.letraControl = new char[]
        {'J','A','B','C','D','E','F','G','H','I'};
        this.primerCaracterLetra="NPQRSW";
        
        /* Si la cadena introducida por el usuario, contiene una letra
        de la lista de caracteres letrasCIF...*/
        if(letrasCif.indexOf(cif.charAt(0)) != -1){
            this.cadenaCif=cif; // asignamos la cadena al atributo cadenaCIF
            
            // Extraemos la letra del CIF a un atributo llamado letraCif
            this.letraCif = cif.substring(0, 1);
            
            /* Extraemos 7 siguientes caracteres a un atributo llamado
            calculoDigitoControl*/
            this.calculoDigitoControl = cadenaCif.substring(1, cif.length()-1);
            
            // Extraemos el último dígito del CIF (opcional)
            this.ultimoDigitoCif= cadenaCif.substring(cadenaCif.length()-1);
        }
    }
    
    /**
     * Devuelve la cadena de caracteres introducida por el usuario.
     * @return Devuelve el CIF en formato texto con la letra en mayúscula.
     */
    public String getCadenaCif(){
        return this.cadenaCif.toUpperCase();
    }
    
    /**
     * Devuelve la letra del cif introducido por teclado.
     * @return Devuelve la letra del CIF convertida en mayúsculas
     */
    public String getLetraCif(){
        return this.letraCif.toUpperCase();
    }
    
    /**
     * Devuelve el número sobre el que se va a calcular el dígito control
     * @return Devuelve el un número de 7 cifras en formato texto.
     */
    public String getCalculoDigitoControl(){
        return this.calculoDigitoControl;
    }
    
    /**
     * Devuelve el último dígito del CIF introducido por teclado. (Opcional) 
     * @return Devuelve el último digito en formato Texto.
     */
    public String getUltimoDigitoCif(){
        return this.ultimoDigitoCif;
    }
    
    /**
     * Suma los números de las posiciones pares del número de 7 dígitos.
     * @return Devuelve el resultado en formato de número entero.
     */
    public int getSumaPares(){
        // Creamos un bucle para recorrer las posiciones pares.
        for(int pos =1;pos< this.getCalculoDigitoControl().length();pos+=2){
            /* A la variable sumaPares le sumamos lo que tenga la variable
            en ese momento, más el número convertido en dato numérico*/
            this.sumaPares = this.sumaPares + Integer.parseInt(
                    this.calculoDigitoControl.substring(pos, pos + 1));
        }
        return sumaPares;
    }
    
    /**
     * Multiplica por dos los números de las posiciones impares
     * y devuelve la suma, si el resultado es de dos cifras, suma ambas cifras.
     * @return Devuelve el resultado de la suma en formato número entero.
     */
    public int getSumaImpares(){
        Integer longitudCifraImpar;
        for(int pos =0;pos<this.getCalculoDigitoControl().length();pos+=2){
            /* A la variable sumaImpares le sumamos lo que tenga la variable
            en ese momento, más el número convertido en dato numérico
            multiplicado por dos*/
            longitudCifraImpar = 2*Integer.parseInt(
                    this.calculoDigitoControl.substring(pos, pos + 1));
            
            /* Si al multiplicar por dos un numero de unaposicion impar
            el restultado tiene una longitud de más de 1 cifra..*/
            if(longitudCifraImpar.toString().length()>1){
                /*Sumamos las dos cifras*/
                longitudCifraImpar = Integer.parseInt
        (longitudCifraImpar.toString().substring(0, 1)) + Integer.parseInt(
                                longitudCifraImpar.toString().substring(1, 2));
            }
            // Sino, sumamos el resultado a la suma impar
            this.sumaImpares=this.sumaImpares+longitudCifraImpar; 
        }
        return sumaImpares;
    }
    
   /**
    * Obtiene el dígito control valido para el CIF que el usuario introduce
    * por teclado.
    * @return Devuelve un valor de tipo entero con el dígito control siempre
    * que el valor de las unidades sea mayor que cero.
    */
    public int getObtenerDigitoControl(){
        // Sumamos el resultado de los pares y los impares        
        this.sumaTotal= this.getSumaPares()+this.getSumaImpares();
        
        // Nos quedamos con las unidades de dicha suma
        this.unidades = this.sumaTotal % 10;
        
        // Si el valor de las unidades es 0
        if(this.unidades > 0){
            // Restamos de 10 las unidades obtenidas.
            this.resultadoResta = 10 - this.unidades;
        }
        else{ // En caso contrario
            this.resultadoResta=0;
        }
        
        // Devolvemos el resultado.
        return this.resultadoResta;
    }
    
    /**
     * Comprueba si el CIF introducido por el usuario es correcto.
     * @return Devuelve verdaddero el si CIF introducido es correcto.
     */
    public boolean getEstablecerDigitoControl(){
        
        // Guardamos el dígito control en una variable tipo texto.
        this.digitoControl = String.valueOf(this.getObtenerDigitoControl());
        
        /*Si los dos primeros dígitos de la cadena que se utiliza para calcular
        el digito control son dos ceros o bien, si la letra del CIF
        esta contenida en la cadena primerCaracterLetra, el dígito control
        del CIF será una letra*/
        if(this.getCalculoDigitoControl().substring(1, 2).equals("00") || 
                primerCaracterLetra.indexOf(
                        this.getLetraCif().charAt(0)) != -1){
            
            // Almacenamos un caracter de control tipo letra
            this.caracterControl = Character.toString(
                    this.letraControl[this.resultadoResta]);
            
            // Armamos el CIF con la letra.
            this.cifValido = this.getLetraCif() + 
                    this.getCalculoDigitoControl() + this.caracterControl;   
        
        }
        else{ // En caso de no comenzar por una letra, almacenamos un número.
            
            // Almacenamos un caracter control de tipo numérico
            this.cifValido = this.getLetraCif() + 
                    this.getCalculoDigitoControl() + this.digitoControl;
        }
        
        // Si el CIF válido coincide con la cadena introducida por el usuario
        // el método equals de la clase String, devuelve verdadero.
        // Por lo tanto podemos crear el return de la siguiente forma:
        return this.getCadenaCif().equals(this.cifValido);
    }
}
