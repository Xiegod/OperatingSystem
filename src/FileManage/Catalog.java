package FileManage;

public class Catalog {
	private String name;
	private String type;
	private byte property;
	private byte startBlock;
	private byte length;
	
	public Catalog(String name,String type,byte property,byte startBlock,byte length){
		this.name = name;
		this.type = type;
		this.property = property;
		this.startBlock = startBlock;
		this.length = length;
	}
	
 	public Catalog(byte[] data,int i){
		byte[] temp = new byte[3];
		temp[0] = data[i];
		temp[1] = data[i + 1];
		temp[2] = data[i + 2];		
		name = new String(temp);
		
		temp = new byte[2];
		temp[0] = data[i + 3];
		temp[1] = data[i + 4];
		type = new String(temp);
		
		property = data[i + 5];
		startBlock = data[i + 6];
		length = data[i + 7];
	}

	public byte[] catalogToByte(){
		byte[] data = new byte[8];
		byte[] temp = new byte[3];
		temp = name.getBytes();
		data[0] = temp[0];
		data[1] = temp[1];
		data[2] = temp[2];	
		
		temp = new byte[2];
		temp = type.getBytes();
		data[3] = temp[0];
		data[4] = temp[1];
		
		data[5] = property;
		data[6] = startBlock;
		data[7] = length;
		return data;
	}
	
	//----以下为所有变量的getter and setter-----
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte getProperty() {
		return property;
	}

	public void setProperty(byte property) {
		this.property = property;
	}

	public byte getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(byte startBlock) {
		this.startBlock = startBlock;
	}

	public byte getLength() {
		return length;
	}

	public void setLength(byte length) {
		this.length = length;
	}
}
