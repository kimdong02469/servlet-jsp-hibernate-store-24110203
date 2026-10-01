package kimdong.vn.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Category")
public class Category_24110203 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categoryId;

	@Column(name = "categoryName", length = 200, nullable = false)
	private String categoryName;

	@Column(length = 500)
	private String images;

	private int status;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Product_24110203> products;

	public Category_24110203() {
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public List<Product_24110203> getProducts() {
		return products;
	}

	public void setProducts(List<Product_24110203> products) {
		this.products = products;
	}
}