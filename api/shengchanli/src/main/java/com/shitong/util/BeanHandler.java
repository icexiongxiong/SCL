package com.shitong.util;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 半天
 * @version 创建时间：2018年03月28日 14时15分18秒  星期三 
 */
public class BeanHandler {
	public static String insertReturnSql(String tableName, Object entity) {
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null) {
					if (value instanceof List) {
						continue;
					}
					name = charReplace(name);
					property = property + name + ",";
					if (value instanceof String || value instanceof Date) {
						condition = condition + "'" + value + "',";
					} else {
						condition = condition + value + ",";
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		property = property.substring(0, property.length() - 1);
		condition = condition.substring(0, condition.length() - 1);
		String sql = "insert into " + tableName + "(" + property + ") values(" + condition + ")";
		return sql;
	}
	public static ConditionHandler insert(String tableName,Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null ) {
					if(value instanceof List){
						continue;
					}
					name = charReplace(name);
					property = property + name + ",";
					condition = condition + "? ,";
					params.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ch.setProperty(property.substring(0, property.length() - 1));
		ch.setCondition(condition.substring(0, condition.length() - 1));
		ch.setParams(params.toArray());
		String sql = "insert into "+tableName+"("+ch.getProperty()+") values("+ch.getCondition()+")" ;
		ch.setSql(sql);
		return ch;
	}

	public static ConditionHandler updateById(String tableName,String primaryKey,Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		try {
			Object pkey = null;
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if(name.equals(primaryKey)){
					pkey = value;
				}
				if(value instanceof List){
					continue;
				}
				if (value != null && !name.equals(primaryKey)) {
					name = charReplace(name);
					property = property + name + "= ? ,";
					params.add(value);
				}
			}
			params.add(pkey);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ch.setProperty(property.substring(0, property.length() - 1));
		ch.setParams(params.toArray());
		String sql = "UPDATE "+tableName+" SET "+ch.getProperty()+" WHERE "+primaryKey+"=?";
		ch.setSql(sql);
		return ch;
	}

	public static String delete(Object object) {
		return null;
	}

	public static ConditionHandler selectAndByEntity(Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		boolean flag = false;
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null) {
					flag = true;
					name = charReplace(name);
					if( name.equals("startyear")){
						property = property + name + " > ? and ";
						params.add(value);
					}else if( name.equals("endyear")){
						property = property + name + " < ? and ";
						params.add(value);
					}else if(value instanceof String){
						property = property + name + " like ? and ";
						params.add("%"+value+"%");
					}else{
						property = property + name + " = ? and ";
						params.add(value);
					}
		
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			ch.setProperty(property.substring(0, property.length() - 4));
			ch.setParams(params.toArray());
		}
		return ch;
	}
	
	

	public static ConditionHandler selectAndLikeByEntity(Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		boolean flag = false;
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null) {
					flag = true;
					name = charReplace(name);
					property = property + name + " like ? and ";
					params.add(value);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			ch.setProperty(property.substring(0, property.length() - 4));
			ch.setParams(params.toArray());
		}
		return ch;
	}
	
	
	public static ConditionHandler selectOrByEntity(Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		boolean flag = false;
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null) {
					flag = true;
					name = charReplace(name);
					property = property + name + " = ? or ";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag)
			ch.setProperty(property.substring(0, property.length() - 3));
		return ch;
	}
	
	public static ConditionHandler selectOrLikeByEntity(Object entity) {
		ConditionHandler ch = new ConditionHandler();
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		String property = "";
		String condition = "";
		List params = new ArrayList();
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		boolean flag = false;
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null) {
					flag = true;
					name = charReplace(name);
					property = property + name + " like ? or ";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag)
			ch.setProperty(property.substring(0, property.length() - 3));
		return ch;
	}


	public static String select(Object object) {
		return null;
	}

	public static String charReplace(String str) {
		char[] ch = str.toCharArray();
		List<Integer>  list= new ArrayList();
		boolean flag = false;
		for (int i = 0; i < ch.length; i++) {
			if ((int) ch[i] >= 65 && (int) ch[i] <= 90) {
				ch[i] += 32;
				list.add(i);
				flag = true;
			}
		}
		str = new String(ch);
		StringBuilder sb = new StringBuilder(str);
		int i = 0;
		if(flag)
			for (Integer length : list) {
				sb.insert(length+i, "_");
				i++;
			}
		return sb.toString();
	}
	
	
	public static Boolean beanIsNotNull(Object entity) {
		Field[] fields;
		Field field;
		int count = 0;
		String name;
		Object value;
		if (entity == null) {
			return null;
		}
		fields = entity.getClass().getDeclaredFields();
		if (fields != null && fields.length > 0) {
			count = fields.length;
		}
		Boolean flag = false;
		try {
			for (int i = 0; i < count; i++) {
				field = fields[i];
				field.setAccessible(true);
				name = field.getName();
				value = field.get(entity);
				if (value != null ) {
					flag = true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}


}