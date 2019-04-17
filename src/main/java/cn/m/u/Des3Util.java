package cn.m.u;

import java.security.Key;
import java.util.Base64;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
//import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

//import org.apache.commons.codec.binary.Base64;



public class Des3Util {

	// 密钥 长度不得小于24
	private final static String secretKey = "123456789012345678901234";
	// 向量 可有可无 终端后台也要约定
	private final static String iv = "01234567";
	// 加解密统一使用的编码方式
	private final static String encoding = "utf-8";

	/**
	 * 3DES加密
	 * 
	 * @param plainText
	 *            普通文本
	 * @return
	 * @throws Exception
	 */
	public static String encode(String plainText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(plainText.getBytes(encoding));
		return Base64.getEncoder().encodeToString(encryptData);
//		return Base64.encodeBase64String(encryptData);
		
	}

	/**
	 * 3DES解密
	 * 
	 * @param encryptText
	 *            加密文本
	 * @return
	 * @throws Exception
	 */
	public static String decode(String encryptText) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] b_ = Base64.getDecoder().decode(encryptText);
//		byte[] b_ = Base64.decodeBase64(encryptText);
		if(null != b_){
			byte[] decryptData = cipher.doFinal(b_);
			return new String(decryptData, encoding);
		}
		return null;
	}

	public static void main(String args[]) throws Exception {
	//	String str = "{\"deviceId\":\"79802537\",\"deviceType\":\"ios\",\"cityLcode\":\"320400\",\"version\":\"1.0.0\",\"clientTime\":\"147987314\",\"phone\":\"15312584242\",\"password\":\"123456\"}";
		String str = "{\"deviceId\":\"79802537\",\"deviceType\":\"ios\",\"cityLcode\":\"320400\",\"version\":\"1.0.0\",\"clientTime\":\"147987314\",\"id\":\"1\"}";
	//	String str = "{\"deviceId\":\"79802537\",\"deviceType\":\"ios\",\"cityLcode\":\"320400\",\"version\":\"1.0.0\",\"clientTime\":\"147987314\",\"currentPage\":1,\"pageSize\":1}";
	//	String str = "{\"deviceId\":\"79802537\",\"deviceType\":\"ios\",\"cityLcode\":\"320400\",\"version\":\"1.0.0\",\"clientTime\":\"147987314\",\"method\":\"1\"}";
		System.out.println("----加密前-----：" + str);
		
		String encodeStr = Des3Util.encode(str);
		System.out.println("----加密后-----：" + encodeStr);
		System.out.println("----解密后-----：" + Des3Util.decode(encodeStr));
		
		
		
	}
}
