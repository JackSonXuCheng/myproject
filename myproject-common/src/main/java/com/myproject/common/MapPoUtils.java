package com.myproject.common;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;


/**
 * 实体类和Map相互转化
 */
public class MapPoUtils {

    public static void main(String[] args) {
    }

    public static Map getValue(Object thisObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        Class c;
        try {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getMethods();
            for (int i = 0; i < m.length; i++) {
                String method = m[i].getName();
                if (method.startsWith("get")) {
                    try {
                        Object value = m[i].invoke(thisObj);
                        String key = method.substring(3);
                        if (value != null) {

                            // key=key.substring(0,1).toUpperCase()+key.substring(1);
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                            String value2String = String.valueOf(value);
                            if (NumberValidationUtils.isRealNumber(value2String)) {
                                map.put(key, DoubleUtils.roundByScale(new Double(value2String), 2));
                            } else {
                                map.put(key, value);
                            }

                        } else {
                            key = key.substring(0, 1).toLowerCase() + key.substring(1);
                            map.put(key, null);
                        }
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println("error:" + method);
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, String> PO2Map(Object o) {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = null;
        String clzName = o.getClass().getSimpleName();
        fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String proName = field.getName();
            String proValue;
            try {
                proValue = String.valueOf(field.get(o));
                map.put(proName, proValue);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return map;
    }

    /**
     * 把map转化为对象
     */
    public static Object map2PO(Map<String, String> map, Object o) {
        if (!map.isEmpty()) {
            for (String key : map.keySet()) {
                Object value = "";
                if (!key.isEmpty()) {
                    value = map.get(key);
                }
				/*if ("java.sql.Timestamp".equals(value.getClass().getName())) {
					value = value.toString();
				}
				if ("java.sql.Date".equals(value.getClass().getName())) {
					value = value.toString();
				}*/

                Field[] fields = null;
                fields = o.getClass().getDeclaredFields();
                String clzName = o.getClass().getSimpleName();

                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if (Modifier.isStatic(mod) || Modifier.isFinal(mod) || Modifier.isTransient(mod)) {
                        continue;
                    }
                    //将属性名称转换为_。
                    String fieldName = MyStringUtil.changeUpper2UnderLine(field.getName());

                    if (fieldName.equals(key)) {
                        field.setAccessible(true);
                        try {
                            if (fieldName.equals("createDate") || fieldName.equals("updateDate")) {
                                field.set(o, (Date) value);
                            } else {
                                field.set(o, value);
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        return o;
    }

    /**
     * 把request请求中的参数转为map然后转化为对象
     */
    public static Object requestMap2PO(Map<String, String[]> map, Object o) {
        if (!map.isEmpty()) {
            for (String key : map.keySet()) {
                String[] value = null;
                if (!key.isEmpty()) {
                    value = map.get(key);
                }
                Field[] fields = null;
                fields = o.getClass().getDeclaredFields();
                String clzName = o.getClass().getSimpleName();
                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                        continue;
                    }
                    if (field.getName().equals(key)) {
                        field.setAccessible(true);
                        try {
                            if (value != null && value.length > 1) {
                                List list = new ArrayList();
                                for (String val : value) {
                                    list.add(val);
                                }
                                field.set(o, list);
                            } else if (value != null && value.length == 1) {
                                field.set(o, getObject(field.getGenericType().getTypeName(), value[0]));
                            }

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                }
            }
        }
        return o;
    }

    private static Object getObject(String typeName, String value) {
        // TODO Auto-generated method stub
        if ("java.lang.Integer".equals(typeName)) {
            return Integer.parseInt(value);
        }
        if ("java.lang.Long".equals(typeName)) {
            return Long.parseLong(value);
        }
        if ("java.lang.Double".equals(typeName)) {
            return Double.parseDouble(value);
        }
        if ("java.lang.BigDecimal".equals(typeName)) {
            return new BigDecimal(value);
        }
        return value;
    }

    /**
     * 把map转为实例（map中key的下划线转为驼峰）
     */
/*	public static Object map2POByUnderline(Map<String, Object> map, Class clazz) {
		try {
			Object obj = clazz.getConstructor().newInstance();
			if (map != null && !map.isEmpty()) {
				for (String key : map.keySet()) {
					Object value = "";
					if (!key.isEmpty()) {
						value = map.get(key);
					}
					if ("java.sql.Timestamp".equals(value.getClass().getName())) {
						value = value.toString();
					}
					if ("java.sql.Date".equals(value.getClass().getName())) {
						value = value.toString();
					}

					Field[] fields = null;
					fields = obj.getClass().getDeclaredFields();
					String clzName = obj.getClass().getSimpleName();
					for (Field field : fields) {

						int mod = field.getModifiers();
						if (Modifier.isStatic(mod) || Modifier.isFinal(mod)||Modifier.isTransient(mod)) {
							continue;
						}
						AnnotatedType annotatedType = field.getAnnotatedType();
						Column column = field.getAnnotation(Column.class); // 获取指定类型注解
						String fieldName = column == null || column.username() == null || "".equals(column.username())
								? field.getName() : column.username();
						fieldName = MyStringUtil.changeUpper2UnderLine(fieldName);
						if (fieldName.equals(key)) {
							field.setAccessible(true);
							if ("java.lang.String".equals(field.getType().getName())) {
								field.set(obj, value.toString());
								continue;
							}

							if ("java.math.BigInteger".equals(field.getType().getName())) {
								field.set(obj, new BigInteger(value.toString()));
								continue;
							}
							if ("java.lang.Double".equals(field.getType().getName())){
								field.set(obj,new Double(value.toString()));
								continue;
							}
							*//*ColumnRole columnRole = field.getAnnotation(ColumnRole.class); // 获取规则注解
							if (columnRole != null && !columnRole.show()) {
								continue;
							}*//*
							field.set(obj, value);

							continue;
						}

					}
				}
			}
			return obj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}*/

    /**
     * 把List<Map>转为List<实体>
     */
    public static List listMap2listPO(List<Map> oldlist, Class clazz, Integer type) {
        List list = null;
        // 自动转为驼峰
        if (type == 1) {
            list = new ArrayList();
            for (Map map : oldlist) {
                //list.add(map2POByUnderline(map, clazz));
            }
        }
        // 按原名
        if (type == 2) {
            list = new ArrayList();
            for (Map map : oldlist) {
                try {
                    list.add(map2PO(map, clazz.getConstructor().newInstance()));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return list;
    }

    /**
     * 把List<Map>转为List<实体>
     */
    public static List listMap2listPO(List<Map> oldlist, Class clazz) {

        List list = listMap2listPO(oldlist, clazz, 2);
        return list;
    }


}
