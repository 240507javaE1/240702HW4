package model;

public class Order {
	private Integer id;
	private String stockNo;
	private String 委託狀態;
	private String 盤別;
	private String 交易別;
	private String 條件;
	private Double 委託價;
	private Integer 委託股數;
	private Integer 成交股數;
	private String 委託時間;
	private String 委託書號;
	private String 交易日期;
	private String 幣別;
	private Integer member_id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String get委託狀態() {
		return 委託狀態;
	}
	public void set委託狀態(String 委託狀態) {
		this.委託狀態 = 委託狀態;
	}
	public String get盤別() {
		return 盤別;
	}
	public void set盤別(String 盤別) {
		this.盤別 = 盤別;
	}
	public String get交易別() {
		return 交易別;
	}
	public void set交易別(String 交易別) {
		this.交易別 = 交易別;
	}
	public String get條件() {
		return 條件;
	}
	public void set條件(String 條件) {
		this.條件 = 條件;
	}
	public Double get委託價() {
		return 委託價;
	}
	public void set委託價(Double 委託價) {
		this.委託價 = 委託價;
	}
	public Integer get委託股數() {
		return 委託股數;
	}
	public void set委託股數(Integer 委託股數) {
		this.委託股數 = 委託股數;
	}
	public Integer get成交股數() {
		return 成交股數;
	}
	public void set成交股數(Integer 成交股數) {
		this.成交股數 = 成交股數;
	}
	public String get委託時間() {
		return 委託時間;
	}
	public void set委託時間(String 委託時間) {
		this.委託時間 = 委託時間;
	}
	public String get委託書號() {
		return 委託書號;
	}
	public void set委託書號(String 委託書號) {
		this.委託書號 = 委託書號;
	}
	public String get交易日期() {
		return 交易日期;
	}
	public void set交易日期(String 交易日期) {
		this.交易日期 = 交易日期;
	}
	public String get幣別() {
		return 幣別;
	}
	public void set幣別(String 幣別) {
		this.幣別 = 幣別;
	}
	public Integer getMember_id() {
		return member_id;
	}
	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}
}
