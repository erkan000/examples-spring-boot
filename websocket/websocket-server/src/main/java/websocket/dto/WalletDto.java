package websocket.dto;

public class WalletDto {
	
	private String address;
	private String name;
	
	public WalletDto(final String address, final String name) {
        this.address = address;
        this.name = name;
    }
	
	public WalletDto() {
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
