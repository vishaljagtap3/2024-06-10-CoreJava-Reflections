import java.lang.reflect.*;

public class Main {
    public static void main(String[] args) {

        Drawable drawable = new Drawable() {
            @Override
            public void draw() {

            }
        };

        Class classInfo = drawable.getClass();
        System.out.println(classInfo.getName());
        System.out.println(classInfo.isAnonymousClass());

        /*Holder h1 = new Holder(10);
        Class c1 = Holder.class;
        Class c2 = h1.getClass();
        System.out.println(c1 == c2);

        printClassInfo(Pixel.class);
        printClassInfo(c1);
        printClassInfo(String.class);
        printClassInfo(Main.class);*/


        try {

            Class c = Pixel.class;
            Pixel p = (Pixel) c.newInstance();
            p.display();

            Class [] paramTypes = new Class[2];
            paramTypes[0] = int.class;
            paramTypes[1] = int.class;
            Constructor cons = c.getConstructor(paramTypes);

            System.out.println(cons);

            Pixel p1 = (Pixel) cons.newInstance( new Integer[] {100, 200} );
            //p1.display();

            Method methodDisplay = c.getMethod("display", null);
            methodDisplay.invoke(p1);

            Method methodMove = c.getMethod("move", new Class[] {int.class, int.class});
            methodMove.invoke( p1, new Integer[] {500, 500});

            p1.display();



        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printClassInfo(Class c) {
        System.out.println("Class Name: " + c.getName());
        System.out.println("Is array: " + c.isArray());
        System.out.println("Super class: " + c.getSuperclass().getName() );
        System.out.println("Is interface: " + c.isInterface());

        System.out.println("Fields");
        Field [] fields = c.getFields();
        for(Field field : fields) {
            System.out.println(field.getType().getName() + " " + field.getName());
        }
        System.out.println();

        System.out.println("Methods");
        Method [] methods = c.getMethods();
        for(Method method : methods) {
            System.out.print(method.getReturnType().getName() + " " + method.getName() + "( ");

            Parameter [] params = method.getParameters();
            for(Parameter p : params) {
                System.out.print(p.getType().getName() + " " + p.getName() + ", ");
            }
            System.out.print(") \n");
        }

        System.out.println();

        System.out.println("Constructors");
        Constructor [] constructors = c.getConstructors();
        for(Constructor cons : constructors) {
            System.out.print(cons.getName() + "( ");
            Parameter [] params = cons.getParameters();
            for(Parameter p : params) {
                System.out.print(p.getType().getName() + " " + p.getName() + ", ");
            }
            System.out.print(") \n");
        }
        System.out.println("-------------------------------------------------");
    }
}
