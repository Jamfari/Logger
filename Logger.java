package fr.utt.lo02.td2.singleton;

import java.util.Date; 


public class Logger { 
	
	
	public final static byte DEBUG = 0; 
	public final static byte INFO = 1;  
	public final static byte WARN = 2;
	public final static byte ERROR = 3; 
	public final static byte FATAL = 4; 
	
	public final static byte OUTPUT_STREAM = 0;
	public final static byte ERROR_STREAM = 1;
	
	
	public final static String[] LEVEL_NAMES = {"DEBUG", "INFO", "WARN", "ERROR", "FATAL"};
	
	
	private static Logger logger = null; 
	
	private byte level; scodé en complément à deux 
	private Date date; 
	private String separator; 
	private byte output;
	
	


	public static Logger getInstance() {
		if (logger == null) {
			logger = new Logger();
		}
		
		return logger; 
	}
	
	
	private Logger() 
	{
		level = Logger.INFO;
		date = new Date(); 
		separator = "\t"; 
		output = Logger.OUTPUT_STREAM;	
	} 
	
	
	public void setLevel (byte newLevel) { 
		if ((newLevel < Logger.DEBUG) | (newLevel > Logger.FATAL)) {
			this.error("Niveau de log invalide : " + level); 
		}
		else {
			level = newLevel;
		}
	}

	
	public byte getLevel() { 
		return level;
	}
	
	public String getStringLevel() {
		return Logger.LEVEL_NAMES[level];
	}
	
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
	
	
	private void log(byte messageLevel, String message) {
		String formattedMessage = new String(
				date.toString() + separator + Logger.LEVEL_NAMES[messageLevel] + separator + message);
		
		switch (output) 
		{
		case Logger.OUTPUT_STREAM: 
			System.out.println(formattedMessage); 
			break; 
			
		case Logger.ERROR_STREAM:
			System.out.println(formattedMessage); 
			break;
		default:
			System.out.println("Logger: invalid output: " + output); 
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
