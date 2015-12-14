package clt.com.cn.dao.impl;

import java.util.List;

import clt.com.cn.base.BaseDao;
import clt.com.cn.dao.IDeptDao;
import clt.com.cn.dao.IManagerApprovalDao;
import clt.com.cn.model.entity.*;


public class ManagerApprovalDao extends BaseDao implements IManagerApprovalDao {

	public List<ManagerApproval> getAllDept() {
		// TODO Auto-generated method stub
		return super.getAllObject(ManagerApproval.class);
	}

	public void addManagerApproval(ManagerApproval dept) {
		// TODO Auto-generated method stub
		super.addObject(dept);
	}

	public void delManagerApprovalById(int id) {
		// TODO Auto-generated method stub
		super.deleteObjectById(ManagerApproval.class, id);
	}

	public ManagerApproval getManagerApprovalById(int id) {
		// TODO Auto-generated method stub
		return (ManagerApproval) super.getObjectById(ManagerApproval.class, id);
	}

	public void updateManagerApproval(ManagerApproval d) {
		// TODO Auto-generated method stub
		super.updateObject(d);
	}

	public List<ManagerApproval> getUserInfo(String hql, Object... p) {
		// TODO Auto-generated method stub
		return super.getUsersByCondition(hql, p);
	}
}
