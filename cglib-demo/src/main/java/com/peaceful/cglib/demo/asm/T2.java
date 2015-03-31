package com.peaceful.cglib.demo.asm;

import com.peaceful.cglib.demo.TestImpl;
import com.peaceful.common.util.Util;
import net.sf.cglib.asm.*;

import java.io.IOException;
import java.nio.file.FileVisitor;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */
//http://my.oschina.net/u/1166271/blog/220011
public class T2 {

    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader(TestImpl.class.getName());
        Util.report(classReader.getClassName());
        classReader.accept(new DemoClassVisitor(), ClassReader.SKIP_DEBUG);
        System.out.println("---ALL END---");
    }


}

class DemoClassVisitor extends ClassVisitor {
    public DemoClassVisitor() {
        super(Opcodes.ASM4);
    }

    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        System.out.println("at Method " + name);
        //
        MethodVisitor superMV = super.visitMethod(access, name, desc, signature, exceptions);
        return new DemoMethodVisitor(superMV, name);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, boolean b) {
        AnnotationVisitor annotationVisitor = super.visitAnnotation(s, b);
        return new DemoAnnotationVistor(annotationVisitor, s);
    }
}

class DemoMethodVisitor extends MethodVisitor {
    private String methodName;

    public DemoMethodVisitor(MethodVisitor mv, String methodName) {
        super(Opcodes.ASM4, mv);
        this.methodName = methodName;
    }

    public void visitCode() {
        System.out.println("at Method ‘" + methodName + "’ Begin...");
        super.visitCode();
    }

    public void visitEnd() {
        System.out.println("at Method ‘" + methodName + "’End.");
        super.visitEnd();
    }
}


class DemoAnnotationVistor extends AnnotationVisitor {
    private String annotaionName;

    public DemoAnnotationVistor(AnnotationVisitor annotationVisitor, String annotaionName) {
        super(Opcodes.ASM4, annotationVisitor);
        Util.report(annotaionName);
        this.annotaionName = annotaionName;
    }

    @Override
    public void visitEnum(String s, String s1, String s2) {
        super.visitEnum(s, s1, s2);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String s, String s1) {
        return super.visitAnnotation(s, s1);
    }

    @Override
    public void visit(String s, Object o) {
        super.visit(s, o);
        System.out.println("at Annotation ‘" + s + "’End.");
    }

    @Override
    public void visitEnd() {
        System.out.println("at Annotation ‘" + annotaionName + "’End.");
        super.visitEnd();
    }
}