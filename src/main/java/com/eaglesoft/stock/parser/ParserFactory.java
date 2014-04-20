package com.eaglesoft.stock.parser;


public class ParserFactory {
  public static DataParser createParser(DataType type){
	  
	  if(DataType.ZJLX == type) {
		  return new ZjlxDataParser();  
	  } else {
		  return null;
	  }
  }
}
