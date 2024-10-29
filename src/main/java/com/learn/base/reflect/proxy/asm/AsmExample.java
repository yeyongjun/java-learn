package com.learn.base.reflect.proxy.asm;

import com.learn.base.reflect.proxy.ProxyAction;
import jdk.internal.org.objectweb.asm.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yeyongjun
 * @since 2024/9/16 16:49
 */
public class AsmExample extends ClassLoader implements Opcodes {
    public static void main(String args[]) throws IOException, IllegalArgumentException, SecurityException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassReader cr = new ClassReader(ProxyAction.class.getName());
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new MethodChangeClassAdapter(cw);
        cr.accept(cv, Opcodes.ASM4);

        // 新增加一个方法
        MethodVisitor mw = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "add", "([Ljava/lang/String;)V", null, null);
        mw.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mw.visitLdcInsn("this is add method print!");
        mw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        mw.visitInsn(RETURN);
        // this code uses a maximum of two stack elements and two local
        // variables
        mw.visitMaxs(0, 0);
        mw.visitEnd();

        byte[] code = cw.toByteArray();
        AsmExample loader = new AsmExample();
        Class<?> exampleClass = loader.defineClass(ProxyAction.class.getName(), code, 0, code.length);

        for (Method method : exampleClass.getMethods()) {
            System.out.println(method);
        }

        System.out.println("***************************");
        // uses the dynamically generated class to print 'Helloworld'
        // 調用changeMethodContent，修改方法內容
        exampleClass.getMethods()[2].invoke(exampleClass.newInstance(), null);

        System.out.println("***************************");
        // 調用execute,修改方法名
        exampleClass.getMethods()[0].invoke(exampleClass.newInstance(), null);
        // gets the bytecode of the Example class, and loads it dynamically

        FileOutputStream fos = new FileOutputStream("e:\\Example.class");
        fos.write(code);
        fos.close();
    }
}
