PROGETTO BASI DI DATi

A.	Istruzioni sull’avvio dell’applicativo

      Requisiti:
          •	Java 21+
          •	MySQL 8.0+
          •	Scaricare i file dbAgriTerra.sql e dbAgriTerra.jar

B.	Creazione del database MySQL in locale:

      •	Avviare lo script dbAgriTerra.sql su una connessione localhost di MySQL
      •	L'applicazione accede come utente root e password “password123”

C.	Avvio dell'applicazione:

      Se si ha Java per desktop installato:
        •	Fare doppio clic sul file dbAgriTerra.jar
      
      Altrimenti, eseguire da terminale:
        •	Windows: java -jar .\dbAgriTerra.jar
        •	Linux/Mac: java -jar ./dbAgriTerra.jar

      In caso di problemi, clonare il repo e ricompilare il progetto eseguendo da terminale:
        •	Windows: .\gradlew.bat clean run
        •	Linux/Mac: ./gradlew clean run

D.	Utilizzo applicazione

    Una volta avviata l'applicazione è necessario effettuare il login. 
    Servono credenziali apposite a seconda di che ruolo si ha: 

    AMMINISTRATORE
      USERNAME --> admin 
      PASSWORD --> admin123
    
    RESPONSABILE TERRENO
      USERNAME --> campo 	 
      PASSWORD --> campo123
      
    ADDETTO ALLE VENDITE
      USERNAME --> vendite	  
      PASSWORD --> vendite123
      
