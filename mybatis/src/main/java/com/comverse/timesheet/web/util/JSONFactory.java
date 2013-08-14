package com.comverse.timesheet.web.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class JSONFactory {
	/**
	 * 把数据库数据类转化为EXTJS4所需的JSONTREE数据
	 * 
	 * @param records
	 *            数据类
	 * @param idfield
	 *            ID字段名
	 * @param parentfield
	 *            父ID字段名
	 * @param textfield
	 *            显示文字字段名
	 * @param hascheckbox
	 *            是否需要checkbox
	 * @return
	 */
	public static String recordsToJSONTreeForEXTJS4(List records,
			String idfield, String parentfield, String textfield,
			boolean hascheckbox) {
		List<Map> jsonlist = new ArrayList();
		if (records == null)
			return null;
		for (int i = 0; i < records.size(); i++) {
			Map record = (Map) records.get(i);
			String parentid = (String) record.get(parentfield);
			if (parentid == null) {
				Map newrecord = buildNode(record, hascheckbox, textfield,
						idfield);
				jsonlist.add(newrecord);
			} else {
				Map parent = null;
				/*
				 * for(int j=0;j<jsonlist.size();j++){ Map<String,String>
				 * tmp=jsonlist.get(j);
				 * if(tmp.get("\""+idfield+"\"").equals("\""+parentid+"\"")){
				 * parent=tmp; break; } }
				 */
				parent = getParentNode(jsonlist, parentid, idfield);
				if (parent.containsKey("\"leaf\"")) {
					parent.remove("\"leaf\"");
					parent.put("\"cls\"", "\"folder\"");
				}
				if (!parent.containsKey("\"children\"")) {
					List children = new ArrayList();
					Map newrecord = buildNode(record, hascheckbox, textfield,
							idfield);
					children.add(newrecord);
					parent.put("\"children\"", children);
				} else {
					List children = (List) parent.get("\"children\"");
					Map newrecord = buildNode(record, hascheckbox, textfield,
							idfield);
					children.add(newrecord);
				}
			}
		}
		return jsonlist.toString().replaceAll("=", ":");
	}

	/**
	 * 获取父节点
	 * 
	 * @param jsonlist
	 * @param parentid
	 * @param idfield
	 * @return
	 */
	private static Map getParentNode(List jsonlist, String parentid,
			String idfield) {
		Map parent = null;
		for (int j = 0; j < jsonlist.size(); j++) {
			Map tmp = (Map) jsonlist.get(j);
			if (tmp.get("\"" + idfield + "\"").equals("\"" + parentid + "\"")) {
				parent = tmp;
				break;
			}
			if (tmp.get("\"children\"") != null) {
				parent = getParentNode((List) tmp.get("\"children\""),
						parentid, idfield);
				if (parent != null) {
					break;
				}
			}
		}
		return parent;
	}

	/**
	 * 创建节点
	 * 
	 * @param record
	 * @param hascheckbox
	 * @param textfield
	 * @return
	 */
	private static Map buildNode(Map record, boolean hascheckbox,
			String textfield, String idfield) {
		Map newrecord = new HashMap();
		Iterator<String> it = record.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (record.get(key) == null) {
				newrecord.put("\"" + key + "\"", "\"\"");
			} else {
				if (key.equals(idfield)) {
					newrecord.put("id", "\"" + record.get(key) + "\"");
				}
				newrecord.put("\"" + key + "\"", "\"" + record.get(key) + "\"");
			}
		}
		newrecord.put("\"text\"", "\"" + record.get(textfield) + "\"");
		newrecord.put("\"leaf\"", "true");
		if (hascheckbox) {
			newrecord.put("\"checked\"", "false");
		}
		return newrecord;
	}

	public static void main(String[] arg) {
		List records = new ArrayList();
		Map map1 = new HashMap();
		map1.put("ID", "1");
		map1.put("NAME", "NAME1");
		map1.put("PARENTID", null);
		map1.put("URL", "URL1");
		records.add(map1);
		Map map2 = new HashMap();
		map2.put("ID", "2");
		map2.put("NAME", "NAME2");
		map2.put("PARENTID", null);
		map2.put("URL", "URL2");
		records.add(map2);
		Map map11 = new HashMap();
		map11.put("ID", "11");
		map11.put("NAME", "NAME11");
		map11.put("PARENTID", "1");
		map11.put("URL", "URL11");
		records.add(map11);
		Map map21 = new HashMap();
		map21.put("ID", "21");
		map21.put("NAME", "NAME21");
		map21.put("PARENTID", "2");
		map21.put("URL", "URL21");
		records.add(map21);
		Map map22 = new HashMap();
		map22.put("ID", "22");
		map22.put("NAME", "NAME22");
		map22.put("PARENTID", "2");
		map22.put("URL", "URL22");
		records.add(map22);
		Map map221 = new HashMap();
		map221.put("ID", "221");
		map221.put("NAME", "NAME221");
		map221.put("PARENTID", "22");
		map221.put("URL", "URL221");
		records.add(map221);
		String json = JSONFactory.recordsToJSONTreeForEXTJS4(records, "ID",
				"PARENTID", "NAME", false);
		System.out.println(json);
	}
}
