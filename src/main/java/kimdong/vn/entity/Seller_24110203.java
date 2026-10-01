package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Seller")
public class Seller_24110203 implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sellerId;

	@Column(nullable = false, length = 50)
	private String sellername;

	@Column(length = 500)
	private String images;

	private int status;

	public Seller_24110203() {
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}