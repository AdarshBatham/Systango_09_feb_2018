package beans;

/*--------------------------Bean Class Used for mapping-----------------------------*/
public class Customer {
	int id;
	String name;
	String address;
	String mobilenumber;
	String email;
	String ipaddress;
	String dateofpurchase;
	int quantity;
	String password;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getDateofpurchase() {
		return dateofpurchase;
	}
	public void setDateofpurchase(String dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Customer()
	{}
	public Customer(String name,String address,String mobile,String email,String accountnumber,String password,int balance)
	{
		super();
				this.name=name;
				this.address=address;
				this.mobilenumber=mobile;
				this.email=email;				
				this.password=password;
	}
}
