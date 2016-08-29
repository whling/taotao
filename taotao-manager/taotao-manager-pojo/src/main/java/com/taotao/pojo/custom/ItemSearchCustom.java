package com.taotao.pojo.custom;
/**
 * 自定义的po类:数据库中向索引库中复制的值
 * @author Administrator
 *
 */
public class ItemSearchCustom {
	//solr的schema.xml中定义的id为string类型
	private String id;

    private String title;

    private String sellPoint;

    private Long price;
    
    private String image;
    //商品分类名称
    private String name;
    
    //一个商品对应一张或者多张图片，这里主要是为了方便开发
    private String[] images;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getImages(){
		if(image!=null){
			String[] strings = image.split(",");
			return strings;
		}else{
			return null;
		}
	}
}
