package carmelo.json;

//枚举工具类
public enum ResponseType {
	
	SUCCESS(1),

	FAIL(2),

	PUSH(3);
	
	private int type;
	
	ResponseType(int type){
		this.type = type;
	}
	
	public int getType(){
		return this.type;
	}

}
