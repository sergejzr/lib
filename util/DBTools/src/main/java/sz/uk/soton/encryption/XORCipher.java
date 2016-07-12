package sz.uk.soton.encryption;

import javax.xml.bind.DatatypeConverter;

public class XORCipher {
	
	public static String encrypt(String plainText, String keyPhrase)
	{
		
		byte[] out=new byte[plainText.length()];
		for(int i=0;i<plainText.length();i++)
		{
			int y=plainText.length()%keyPhrase.length();
			out[i]=(byte)(plainText.charAt(i)^keyPhrase.charAt(y));
		}
		
		
		return DatatypeConverter.printBase64Binary(out);
	}
	
	public static String decrypt(String cipherText, String keyPhrase)
	{
		
		
	byte[] out = DatatypeConverter.parseBase64Binary(cipherText);
	byte original[]=new byte[out.length];
	for(int i=0;i<out.length;i++)
	{
		int y=out.length%keyPhrase.length();
		original[i]=(byte)(out[i]^keyPhrase.charAt(y));
	}
	return new String(original);
}

}
