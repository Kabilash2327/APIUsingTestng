package org.pojo;



public class PersonalDetailsPojoWrite {

	private int id;
	private String name;
	private String bloodGroup;
	private int age;
	private String location;
	
	
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
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public PersonalDetailsPojoWrite(int id, String name, String bloodGroup, int age, String location) {
		super();
		this.id = id;
		this.name = name;
		this.bloodGroup = bloodGroup;
		this.age = age;
		this.location = location;
	}
	
	
	
	
	

}
