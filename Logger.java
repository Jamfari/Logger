package fr.utt.lo02.td2.singleton;

import java.util.Date; // L'instruction import importe la classe Date 

//La classe Logger est visible par les autres car elle est en public
public class Logger { 
	
	// On définit les constantes DEBUG, INFO, WARN, ERROR, FATAL  
	// Visibilité (public) Type (byte) et nom (DEBUG)
	public final static byte DEBUG = 0; // Le mot clé static signifie que l'attribut/membre DEBUG appartient à la classe elle même et non pas aux instances et qu'elle peut être invoquée sans créer une instance.
	public final static byte INFO = 1; // En fait static signifie que l'attribut/membre INFO existe indépendemment des objets de la classe Logger
	public final static byte WARN = 2; // Le membre/attribut statique n'est pas lié à un objet mais à l'ensemble des objets de la classe Logger
	public final static byte ERROR = 3; // Le mot-clé final indique qu'un élément ne peut être changé dans la suite du programme.
	public final static byte FATAL = 4; // Les attributs statiques sont souvent couplés au modifier final pour en faire des constantes de classe 
	
	// On définit les constantes OUTPUT_STREAM, ERROR_STREAM
	public final static byte OUTPUT_STREAM = 0;
	public final static byte ERROR_STREAM = 1;
	
	// On définit le tableau de chaines de caractères LEVEL_NAMES
	public final static String[] LEVEL_NAMES = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL"};
	
	// On définit les attributs ou variable de la classe Logger : logger, level, date, separator, output
	// Visibilité (private), Type (byte), nom (level)
	private static Logger logger = null; // Déclaration d'une référence de l'attribut logger initialisée à null
	// Le constructeur porte le même nom que la classe
	private byte level; // Le mot clé byte désinge le type de l'attribut et signifie que la variable 'level' est un entier de 8 bitscodé en complément à deux 
	private Date date; // L'attribut Date est en private, donc visible seulement à l'intérieur de la classe Logger 
	private String separator; // Le mot clé String désigne que la variable est une chaine de caractère
	private byte output;
	
	//Généralement, les attributs sont en privé, l'accès aux attributs se fera par des méthodes d'accès dédiées
	
	/* Ici la methode porte le même nom que la classe : c'est un constructeur, on a sa visibilité (public), 
	 son type de retour (static), son nom (getInstance) et son paramètre (ici aucun)*/
	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		
		return logger; // Le mot clé return termine l'action d'une méthode et retourne la valeur donnée en paramètre
	}
	
	// Un autre constructeur en privé : il va s'exécuter automatiquement à l'instanciation (visible seulement depuis la classe Logger
	private Logger() // Le mot-clé private signifie que cet objet n'est visible qu'à l'intérieur de la classe Logger
	{
		level = Logger.INFO;
		date = new Date(); // Mot clé new désigne l'instanciation de l'objet Date
		separator = "\t"; // \t -> caractère de tabulation
		output = Logger.OUTPUT_STREAM;	
	} 
	
	// méthode : visibilité (public), type de retour (void donc pas de retour), nom (setLevel), et paramètres (newLevel)
	public void setLevel (byte newLevel) { // La methode setter (setqqchose) permet d'ecrire/d'affecter l'attribut voulu à une valeur
		if ((newLevel < Logger.DEBUG) | (newLevel > Logger.FATAL)) {
			this.error("Niveau de log invalide : " + level); // this est la réf sur la classe càd que 'this' remplace 'Logger'
		}
		else {
			level = newLevel;
		}
	}

	// Méthode visibilité (public), type de retour (byte), nom (getLevel), pas de paramètres
	public byte getLevel() { // Methode getter (getqqchose) permet de lire/d'avoir la valeur de Level
		return level;
	}
	
	public String getStringLevel() {
		return Logger.LEVEL_NAMES[level];
	}
	// La syntaxe pour un Appel de méthode : objet.method();  | Classe.attribut | this.attribut | this.methode(); pour une réf dans la classe
	public void setOutputStream(byte outputStream) {
		if (outputStream == Logger.OUTPUT_STREAM) {
			output = outputStream;
		}
		else if (outputStream == Logger.ERROR_STREAM) {
			output = outputStream;
		}
		else {
			this.error("Sortie de log invalde : " + outputStream); 
		}	// Le mot-clé this désigne une référence sur la classe elle-même; on aurait pu remplacer this par Logger
	}
	
	public void debug(String message) {
		if (level <= Logger.DEBUG) {
			this.log(Logger.DEBUG, message); 
		}
	}
	
	public void info(String message) {
		if (level <= Logger.INFO) {
			this.log(Logger.INFO, message);
		}
	}
	
	public void warn(String message) {
		if (level <= Logger.WARN) {
			this.log(Logger.WARN, message);
		}
	}
	
	public void error(String message) {
		if (level <= Logger.ERROR) {
			this.log(Logger.ERROR, message);
		}
	}
	
	public void fatal(String message) {
		if (level <= Logger.FATAL) {
			this.log(Logger.FATAL, message);
		}
	}
	
	/* Méthode s'appelle log, est visible à l'intérieur de la classe Logger seulement (private), 
	 ne retourne rien (void), et a 2 paramètres (une variable et une chaine de caractères)*/
	private void log(byte messageLevel, String message) {
		String formattedMessage = new String(
				date.toString() + separator + Logger.LEVEL_NAMES[messageLevel] + separator + message);
		
		switch (output) // L'instruciton switch permet de tester une variable
		{
		case Logger.OUTPUT_STREAM: 
			System.out.println(formattedMessage); // Code à exécuter si la valeur de <output> est <Logger.OUTPUT_STREAM>
			break; // break est optionnel 
			// il faut autant de 'case' que de valeurs
		case Logger.ERROR_STREAM:
			System.out.println(formattedMessage); // Code à exécuter si la valeur de <output> est <Logger.ERROR_STREAM>
			break;
		default:
			System.out.println("Logger: invalid output: " + output); // Code à exécuter si aucun cas n'est vérifié
		}
	
	}

	public static void main (String[] args) {
		Logger logger = Logger.getInstance();
		logger.setLevel(Logger.WARN);
		logger.setOutputStream(Logger.OUTPUT_STREAM);
		logger.debug("test debug");
		logger.info("test info");
		logger.warn("test warn");
		logger.error("test error");
		logger.fatal("test fatal");
	}
}
