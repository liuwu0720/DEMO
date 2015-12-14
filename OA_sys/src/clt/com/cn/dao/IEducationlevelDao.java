package clt.com.cn.dao;

import java.util.List;

import clt.com.cn.model.entity.*;

public interface IEducationlevelDao {
	public abstract List<Educationlevel> getAllEducationlevel();
	
	public abstract void addEducationlevel(Educationlevel educationlevel);
	
	public abstract void delEducationlevelById(int id);
	
	public abstract Educationlevel getEducationlevelById(int id);
	
	public abstract void updateEducationlevel(Educationlevel d);
	
}
