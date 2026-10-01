package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Product")
public class Product_24110203 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(length = 200, nullable = false)
	private String productName;

	@Column(name = "productCode")
	private int productCode;

	@Column(length = 500)
	private String description;

	private Double price;
	private Integer amount;
	private Integer stock;

	@Column(length = 500)
	private String images;

	private Integer wishlist;
	private Integer status;

	@Temporal(TemporalType.DATE)
	private Date createDate;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category_24110203 category;

	@Column(name = "sellerId")
	private Integer sellerId;

	public Product_24110203() {
		this.createDate = new Date();
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductCode() {
	    return productCode;
	}
	
	public void setProductCode(int productCode) {
	    this.productCode = productCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getWishlist() {
		return wishlist;
	}

	public void setWishlist(Integer wishlist) {
		this.wishlist = wishlist;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category_24110203 getCategory() {
		return category;
	}

	public void setCategory(Category_24110203 category) {
		this.category = category;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
}