package TestDemo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

public class Demo {
	public static void main(String[] args) throws Exception {
//		printSystemProperties();
		testClassLoader();
	}

	private static void printSystemProperties() {
		Properties propertie = System.getProperties();
		for (Entry entry : propertie.entrySet()) {
			System.out.println(String.format("key:%s, value:%s", entry.getKey(), entry.getValue()));
		}
	}

	private static void testClassLoader() throws Exception {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		Class<?> cls = classLoader.loadClass("Util.Mathematics");
		Field[] fields = cls.getFields();
		for (Field field : fields) {
			System.out.println(String.format("name:%s, type:%s", field.getName(), field.getType().getName()));
		}
		Method[] methods = cls.getMethods();
		for (Method method : methods) {
			Class<?>[] parameterTypes = method.getParameterTypes();
			System.out.print(String.format("methodName:%s, returnType:%s, parameters: ", method.getName(), method.getGenericReturnType()));
			for (Class<?> clsParameter : parameterTypes) {
				System.out.print(String.format("%s \t", clsParameter.getClass().getName()));
			}
			System.out.println("");
			
		}
		System.out.println(cls.getPackage().getName());
		
	 	Object instance = cls.newInstance();
		Class<?> paramTypes[] = { double.class, double.class };
		Object param[] = {1.2,5.1};
		Method method = cls.getMethod("add", paramTypes);
		Object obj = method.invoke(instance, param);
		System.out.println(obj);
	}
}
