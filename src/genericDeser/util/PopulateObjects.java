package genericDeser.util;

import genericDeser.fileOperations.FileProcessor;
import genericDeser.util.First;
import genericDeser.util.Second;
import genericDeser.tree.RBTree;
import genericDeser.fileOperations.Logger;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PopulateObjects {
    private RBTree<First> firsts;
    private RBTree<Second> seconds;

    public PopulateObjects() {
        firsts = new RBTree<>();
        seconds = new RBTree<>();
    }

    public void deserObjects(String inputFilename) {
        FileProcessor fp = new FileProcessor(inputFilename);
        Class<?> cls = null;
        Class<?>[] signature = new Class<?>[1];
        Object obj = null;
        Method method = null;
        Constructor type_constructor = null;

        String line;
        while ((line = fp.readLine()) != null) {
            String[] tokens = line.split("[\\s:]");
            if (tokens[0].equals("fqn")) {
                if (cls != null) {
                    addObjectToCorrectTree(cls, obj);
                }

                String fqn = tokens[1];
                try {
                    cls = Class.forName(fqn);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {}

                try {
                    obj = cls.newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } finally {}
            } else {
                String type  = "";
                String var   = "";
                String value = "";

                for (String token : tokens) {
                    String[] sides = token.split("=");
                    String left = sides[0];
                    String right = sides[1];

                    if (left.equals("type")) {
                        type  = right.replaceFirst(",",""); // get rid of comma
                    } else if (left.equals("var")) {
                        var   = right.replaceFirst(",",""); // get rid of comma
                    } else if (left.equals("value")) {
                        value = right;
                    }
                }

                Class param_class = classMap(type);
                signature[0] = param_class;

                try {
                    method = cls.getMethod("set" + var, signature);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } finally {}

                invokeMethod(type, obj, method, value);
            }
        }

        addObjectToCorrectTree(cls, obj);

        Logger.writeMessage("Firsts: " + firsts.getTotalCount(), Logger.DebugLevel.NONE);
        Logger.writeMessage("Unique Firsts: " + firsts.getUniqueCount(), Logger.DebugLevel.NONE);
        Logger.writeMessage("Seconds: " + seconds.getTotalCount(), Logger.DebugLevel.NONE);
        Logger.writeMessage("Unique Seconds: " + seconds.getUniqueCount(), Logger.DebugLevel.NONE);

        fp.finish();
    }

    public Class classMap(String type) {
        Class retClass = null;
        if (type.equals("int")) {
            retClass = int.class;
        } else if (type.equals("double")) {
            retClass = double.class;
        } else if (type.equals("boolean")) {
            retClass = boolean.class;
        } else if (type.equals("short")) {
            retClass = short.class;
        } else if (type.equals("float")) {
            retClass = float.class;
        } else if (type.equals("String")) {
            retClass = String.class;
        } else {
            Logger.writeMessage("Could not parse type.", Logger.DebugLevel.NONE);
        }

        return retClass;
    }

    public void invokeMethod(String type, Object obj, Method method, String value) {
        try {
            if (type.equals("int")) {
                method.invoke(obj, new Object[]{ Integer.parseInt(value) });
            } else if (type.equals("double")) {
                method.invoke(obj, new Object[]{ Double.parseDouble(value) });
            } else if (type.equals("boolean")) {
                method.invoke(obj, new Object[]{ Boolean.parseBoolean(value) });
            } else if (type.equals("short")) {
                method.invoke(obj, new Object[]{ Short.parseShort(value) });
            } else if (type.equals("float")) {
                method.invoke(obj, new Object[]{ Float.parseFloat(value) });
            } else if (type.equals("String")) {
                method.invoke(obj, new Object[]{ value });
            } else {
                Logger.writeMessage("Could not parse type.", Logger.DebugLevel.NONE);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {}
    }

    public void addObjectToCorrectTree(Class cls, Object obj) {
        if (cls.getName().equals("genericDeser.util.First")) {
            firsts.insert((First) obj);
        } else if (cls.getName().equals("genericDeser.util.Second")) {
            seconds.insert((Second) obj);
        } else {
            Logger.writeMessage("Could not parse fqn", Logger.DebugLevel.NONE);
        }
    }
}
