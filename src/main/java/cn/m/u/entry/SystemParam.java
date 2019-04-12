package cn.m.u.entry;

public class SystemParam {

	private String deviceId;
	private String deviceType;
	private String cityLcode;
	private String version;
	private String clientTime;
	private String phone;
	private String pwd;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getCityLcode() {
		return cityLcode;
	}
	public void setCityLcode(String cityLcode) {
		this.cityLcode = cityLcode;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getClientTime() {
		return clientTime;
	}
	public void setClientTime(String clientTime) {
		this.clientTime = clientTime;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "SystemParam [deviceId=" + deviceId + ", deviceType=" + deviceType + ", cityLcode=" + cityLcode
				+ ", version=" + version + ", clientTime=" + clientTime + ", phone=" + phone + ", pwd=" + pwd + "]";
	}
}
