package de.mannheim.wifo2.fesas.mwImplBase.BASEUtilities;

import info.pppc.base.system.ObjectID;
import info.pppc.base.system.ReferenceID;
import info.pppc.base.system.SystemID;

import java.math.BigInteger;

import de.mannheim.wifo2.fesas.settings.Constants;

public class IDConversion {

	public static SystemID transformStringToSysID(String idHexString) {
		if(Constants.DEBUG_IDCONVERSION) System.out.println("transformStringToSysID : " + idHexString);
		return new SystemID(stringToByteArray(idHexString));
	}
	
	public static ObjectID transformStringToObjectID(String idString) {
		if(Constants.DEBUG_IDCONVERSION) System.out.println("transformStringToObjectID : " + idString);
		int index1 = idString.indexOf(' ');
//		return new ObjectID(Long.parseLong(idString.substring(index1 + 1),16),
//				IDConversion.transformStringToSysID(idString.substring(0,index1)));
		
		return new ObjectID(new BigInteger(idString.substring(index1 + 1),16), 				
				IDConversion.transformStringToSysID(idString.substring(0,index1)));

	}
	
	public static ReferenceID transformStringToRefID(String refIDString) {
		if(Constants.DEBUG_IDCONVERSION) System.out.println("transformStringToRefID : " + refIDString);
		return transformTwoStringsToRefID(
				refIDString.substring(0, refIDString.indexOf(' ')),
				refIDString.substring(refIDString.indexOf(' ') + 1, refIDString.length()));
	}
	
	public static ReferenceID transformTwoStringsToRefID(String sysIDString, String objIDString) {
//		int index1 = idString.indexOf('(');
//		int index2 = idString.indexOf(')');
		SystemID sysID = transformStringToSysID(sysIDString);
		
//		index1 = idString.indexOf('(', index2);
//		index2 = idString.indexOf(')', index1);
		ObjectID objID = transformStringToObjectID(sysIDString + ' ' + objIDString);
		
		return new ReferenceID(sysID, objID);

	}
	
	
	
	public static String transformSysIDToHexString(SystemID sysID) {
		if(Constants.DEBUG_IDCONVERSION) System.out.println("transformSysIDToHexString : " + sysID);
		int[] valuesAsInt = convertByteArrayToIntArray(sysID.getBytes());		
		StringBuffer result = new StringBuffer();
		int start = valuesAsInt.length - 1;
		
		while (valuesAsInt[start] == 0) {
			start--;
		}
		
		for (int i = start; i >= 0; i--) {
			
			String hex = Integer.toHexString(valuesAsInt[i]);
//			System.out.println(hex);
			if (hex.length() == 1 && i != start) {
				hex = "0" + hex;
			}
			result.append(hex);
		}
		
		return result.toString();
	}
	
	public static String transformObjIDToString(ObjectID objID) {

		return objID.toString();
	}
	
	public static String transformRefIDToString(ReferenceID refID) {
		
		return transformSysIDToHexString(refID.getSystem()) + "_" + 
				refID.getObject().toString();
	}
	
	
	
	
	public static byte[] stringToByteArray(String string) {
		if(Constants.DEBUG_IDCONVERSION) System.out.println("stringToByteArray : " + string);
		long value = Long.parseLong(string, 16);
		return SystemID.toBytes(value);
		
	}
	
	 
	private static int[] convertByteArrayToIntArray(byte[] bytes) {
		
		int[] valuesAsInt = new int[bytes.length];
		
		for (int i = 0; i < bytes.length; i++) {
			int x = bytes[i];
			x = (x + 256) % 256;
			valuesAsInt[i] = x;
		}
		
		return valuesAsInt;
	}
}
