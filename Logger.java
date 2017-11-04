package fr.utt.lo02.td2.singleton;

import java.util.Date; // L'instruction import importe la classe Date 

//La classe Logger est visible par les autres car elle est en public
public class Logger { 
	
	// On d�finit les constantes DEBUG, INFO, WARN, ERROR, FATAL  
	// Visibilit� (public) Type (byte) et nom (DEBUG)
	public final static byte DEBUG = 0; // Le mot cl� static signifie que l'attribut/membre DEBUG appartient � la classe elle m�me et non pas aux instances et qu'elle peut �tre invoqu�e sans cr�er une instance.
	public final static byte INFO = 1; // En fait static signifie que l'attribut/membre INFO existe ind�pendemment des objets de la classe Logger
	public final static byte WARN = 2; // Le membre/attribut statique n'est pas li� � un objet mais � l'ensemble des objets de la classe Logger
	public final static byte ERROR = 3; // Le mot-cl� final indique qu'un �l�ment ne peut �tre chang� dans la suite du programme.
	public final static byte FATAL = 4; // Les attributs statiques sont souvent coupl�s au modifier final pour en faire des constantes de classe 
	
	// On d�finit les constantes OUTPUT_STREAM, ERROR_STREAM
	public final static byte OUTPUT_STREAM = 0;
	public final static byte ERROR_STREAM = 1;
	
	// On d�finit le tableau de chaines de caract�res LEVEL_NAMES
	public final static String[] LEVEL_NAMES = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL"};
	
	// On d�finit les attributs ou variable de la classe Logger : logger, level, date, separator, output
	// Visibilit� (private), Type (byte), nom (level)
	private static Logger logger = null; // D�claration d'une r�f�rence de l'attribut logger initialis�e � null
	// Le constructeur porte le m�me nom que la classe
	private byte level; // Le mot cl� byte d�singe le type de l'attribut et signifie que la variable 'level' est un entier de 8 bitscod� en compl�ment � deux 
	private Date date; // L'attribut Date est en private, donc visible seulement � l'int�rieur de la classe Logger 
	private String separator; // Le mot cl� String d�signe que la variable est une chaine de caract�re
	private byte output;
	
	//G�n�ralement, les attributs sont en priv�, l'acc�s aux attributs se fera par des m�thodes d'acc�s d�di�es
	
	// On d�finit la M�thode, sa visibilit� (public), son type de retour (static), son nom (getInstance) et son param�tre (ici aucun)
	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		
		return logger; // Le mot cl� return termine l'action d'une m�thode et retourne la valeur donn�e en param�tre
	}
	
	// Un autre constructeur en priv� : il va s'ex�cuter automatiquement � l'instanciation (visible seulement depuis la classe Logger
	private Logger() // Le mot-cl� private signifie que cet objet n'est visible qu'� l'int�rieur de la classe Logger
	{
		level = Logger.INFO;
		date = new Date(); // Mot cl� new d�signe l'instanciation de l'objet Date
		separator = "\t"; // \t -> caract�re de tabulation
		output = Logger.OUTPUT_STREAM;	
	} 
	
	// m�thode : visibilit� (public), type de retour (void donc pas de retour), nom (setLevel), et param�tres (newLevel)
	public void setLevel (byte newLevel) { // La methode qui permet d'affecter l'attribut Le
		if ((newLevel < Logger.DEBUG) | (newLevel > Logger.FATAL)) {
			this.error("Niveau de log invalide : " + level); // this est la r�f sur la classe c�d que 'this' remplace 'Logger'
		}
		else {
			level = newLevel;
		}
	}

	// M�thode visibilit� (public), type de retour (byte), nom (getLevel), pas de param�tres
	public byte getLevel() { // Methode qui permet d'avoir la valeur de Level
		return level;
	}
	
	public String getStringLevel() {
		return Logger.LEVEL_NAMES[level];
	}
	// appel de m�thode : objet.method();  | classe.attribut | this.attribut | this.methode(); pour une r�f dans la classe
	public void setOutputStream(byte outputStream) {
		if (outputStream == Logger.OUTPUT_STREAM) {
			output = outputStream;
		}
		else if (outputStream == Logger.ERROR_STREAM) {
			output = outputStream;
		}
		else {
			this.error("Sortie de log invalde : " + outputStream);
		}	
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
	
	// M�thode s'appelle log, est visible � l'int�rieur de la classe Logger seulement (private), ne retourne rien (void), et a 2 param�tres (une variable et une chaine de caract�res)
	private void log(byte messageLevel, String message) {
		String formattedMessage = new String(
				date.toString() + separator + Logger.LEVEL_NAMES[messageLevel] + separator + message);
		
		switch (output) // L'instruciton switch permet de tester une variable
		{
		case Logger.OUTPUT_STREAM: 
			System.out.println(formattedMessage); // Code � ex�cuter si la valeur de <output> est <Logger.OUTPUT_STREAM>
			break; // break est optionnel 
			// il faut autant de 'case' que de valeurs
		case Logger.ERROR_STREAM:
			System.out.println(formattedMessage); // Code � ex�cuter si la valeur de <output> est <Logger.ERROR_STREAM>
			break;
		default:
			System.out.println("Logger: invalid output: " + output); // Code � ex�cuter si aucun cas n'est v�rifi�
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