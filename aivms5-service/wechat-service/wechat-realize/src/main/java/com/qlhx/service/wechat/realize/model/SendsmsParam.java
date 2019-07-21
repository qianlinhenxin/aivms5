package com.qlhx.service.wechat.realize.model;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import tools.Security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Title:发送短息入参数据接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author yxn
 * @date 2017年8月15日 下午4:41:49
 */
@SuppressWarnings("serial")
public class SendsmsParam implements Serializable, Cloneable {

    // 应用标识
    private String appkey;

    // 密文的16位md5串
    private String md5;

    // 短信接收者手机号码
    private String phone;

    // 短信规则编号
    private String num;

    // 短信模板参数列表，具体参数咨询短信平台管理员
    private List<Map<String, String>> list;

    // 密文
    private String ciphertext;

    // 安全码
    private String safe;

    /**
     * 
     * <p>
     * Title:校验请求参数是否被篡改
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param privateKey
     * @return true：没有被篡改，合法，false：不合法
     */
    public boolean validateCiphertext(String privateKey) {
	// 校验密文是否被篡改
	if (this.getMd5().equals(Security.md5_16(this.getCiphertext()))) {// 没有被篡改
	    try {
		// 校验明文是否被篡改
		SendsmsParam sp = (SendsmsParam) this.clone();
		sp.setMd5("");
		sp.setCiphertext("");
		sp.setSafe("");
		String before = JSONObject
			.toJSONString(sp, SerializerFeature.WriteMapNullValue)
			.replaceAll(" ", "").replaceAll("\n", "");
		System.out.println("需加密的json串：" + before);

		// 解密
		// 解密des加密密钥
		String str = Security.decrypt(this.safe, privateKey);
		// 数据解密
		String after = Security.desDecode(
			Security.desDecode(this.ciphertext, str.substring(8)),
			str.substring(0, 8));
		System.out.println("解密后的串：" + after);
		// 解密后的串和明文对比
		if (before.equals(after)) {// 没有被篡改
		    return true;
		}
	    } catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return false;
    }

    /**
     * 
     * <p>
     * Title:校验参数是否合法
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return true:合法 false:不合法
     */
    public boolean validateParam() {
	if (isNAN(this.getAppkey()) && isNAN(this.getMd5())
		&& isNAN(this.getPhone()) && this.getList() != null
		&& this.getList().size() > 0 && isNAN(this.getCiphertext())
		&& isNAN(this.getNum()) && isNAN(this.getSafe())) {
	    return true;
	}
	return false;
    }

    private boolean isNAN(String str) {
	if (str != null && !"".equals(str.trim())) {
	    return true;
	}
	return false;
    }

    /**
     * <p>
     * Description:应用标识
     * </p>
     * 
     * @return the appkey
     */
    public String getAppkey() {
	return appkey;
    }

    /**
     * <p>
     * Description:应用标识
     * </p>
     * 
     * @param appkey
     *            the appkey to set
     */
    public void setAppkey(String appkey) {
	this.appkey = appkey;
    }

    /**
     * <p>
     * Description:密文的16位md5串
     * </p>
     * 
     * @return the md5
     */
    public String getMd5() {
	return md5;
    }

    /**
     * <p>
     * Description:密文的16位md5串
     * </p>
     * 
     * @param md5
     *            the md5 to set
     */
    public void setMd5(String md5) {
	this.md5 = md5;
    }

    /**
     * <p>
     * Description:短信接收者手机号码
     * </p>
     * 
     * @return the phone
     */
    public String getPhone() {
	return phone;
    }

    /**
     * <p>
     * Description:短信接收者手机号码
     * </p>
     * 
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
	this.phone = phone;
    }

    /**
     * <p>
     * Description:短信模板参数列表，具体参数咨询短信平台管理员
     * </p>
     * 
     * @return the list
     */
    public List<Map<String, String>> getList() {
	return list;
    }

    /**
     * <p>
     * Description:短信模板参数列表，具体参数咨询短信平台管理员
     * </p>
     * 
     * @param list
     *            the list to set
     */
    public void setList(List<Map<String, String>> list) {
	this.list = list;
    }

    /**
     * <p>
     * Description:密文
     * </p>
     * 
     * @return the ciphertext
     */
    public String getCiphertext() {
	return ciphertext;
    }

    /**
     * <p>
     * Description:密文
     * </p>
     * 
     * @param ciphertext
     *            the ciphertext to set
     */
    public void setCiphertext(String ciphertext) {
	this.ciphertext = ciphertext;
    }

    /**
     * <p>
     * Description:短信发送规则编号
     * </p>
     * 
     * @return the num
     */
    public String getNum() {
	return num;
    }

    /**
     * <p>
     * Description:短信发送规则编号
     * </p>
     * 
     * @param num
     *            the num to set
     */
    public void setNum(String num) {
	this.num = num;
    }

    /**
     * <p>
     * Description:安全码
     * </p>
     * 
     * @return the safe
     */
    public String getSafe() {
	return safe;
    }

    /**
     * <p>
     * Description:安全码
     * </p>
     * 
     * @param safe
     *            the safe to set
     */
    public void setSafe(String safe) {
	this.safe = safe;
    }

    public static void main(String[] args) {
	SendsmsParam ssp = new SendsmsParam();
	ssp.setAppkey("asfrre");
	List list = new ArrayList<Map<String, String>>();
	Map m1 = new HashMap<String, String>();
	m1.put("aa", "3qwef");
	Map m2 = new HashMap<String, String>();
	m2.put("cc", "dfaf4qsd");
	Map m3 = new HashMap<String, String>();
	m3.put("bb", "vbgwdf");
	list.add(m1);
	list.add(m2);
	list.add(m3);
	ssp.setList(list);
	ssp.setCiphertext("aaaaaaaaaaaaaadfdfefgsdfbddtrhr32komkefdfuyv7by8u9hj0oyp6l43n2wsgyd8fvigbklhymn65b4hgredysciofklghmtnbr4egdsyhujdfnrfj");
	ssp.setSafe("df34bjnk345ytihubuhnrrgtghbvncshseuiyyhhgubytrjmntyudekn6tygubyvvkngweaszdaegegzeuimghchbivjrnfbtrdrsggtfsdgv");
	ssp.setMd5("dfderfgthb67tgfr");
	ssp.setNum("lk3452wert4ffasdfer3fdf4dgerqwer");
	ssp.setPhone("13691291634");

	System.out.println(JSONObject.toJSONString(ssp));
    }

}
