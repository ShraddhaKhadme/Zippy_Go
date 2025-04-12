package in.shraddha.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String pname;
	private String pimg;
	private String pimg2;
	private String pimg3;
	private Double pprice;
	private String pdescription;
	private Double pdiscount;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
	
//	@ManyToOne
//	@JoinColumn(name = "subcategory_id")
//	private SubCategory psubCategory;
}
