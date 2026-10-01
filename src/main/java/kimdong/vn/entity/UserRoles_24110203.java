package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UserRoles")
public class UserRoles_24110203 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;

	@Column(nullable = false, length = 50)
	private String roleName;

	public UserRoles_24110203() {
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}