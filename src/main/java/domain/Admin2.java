package domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Admin2 implements Serializable {

	private static final long serialVersionUID = 1L;
	@XmlID
	@Id
	private String username;
	private String passwd;
	private String mota;

	public Admin2(String username, String passwd) {
		this.username = username;
		this.passwd = passwd;
		this.setMota("Admin");
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mota, passwd, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Admin2 other = (Admin2) obj;
		return Objects.equals(mota, other.mota) && Objects.equals(passwd, other.passwd)
				&& Objects.equals(username, other.username);
	}
	
	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

}

