package com.learn.base.reflect.proxy.asm;

import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author yeyongjun
 * @since 2024/9/16 16:47
 */
public class AsmMethodVisit extends MethodVisitor {
    public AsmMethodVisit(MethodVisitor mv) {
        super(Opcodes.ASM4, mv);
    }

    @Override
    public void visitInsn(int opcode) {
        // 此方法可以获取方法中每一条指令的操作类型，被访问多次
        // 如应在方法结尾处添加新指令，则应判断：
        if (opcode == Opcodes.RETURN) {
            // pushes the 'out' field (of type PrintStream) of the System class
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            // pushes the "Hello World!" String constant
            mv.visitLdcInsn("this is a modify method!");
            // invokes the 'println' method (defined in the PrintStream class)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        }
        super.visitInsn(opcode);
    }
}
