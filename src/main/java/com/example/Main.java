package com.example;

import spoon.Launcher;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;

public class Main {
    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.addInputResource("C:\\Users\\W540\\IdeaProjects\\helloWorld");
        launcher.setSourceOutputDirectory("./res");
        launcher.addProcessor(new AbstractProcessor<CtInvocation>() {
            @Override
            public void process(CtInvocation element) {

                //System.out.println(element.getType());
                //System.out.println(element.getArguments());
                //System.out.println(element.getClass().getSimpleName());
                //System.out.println(element.getTarget());
                CtCodeSnippetStatement snippet = getFactory().Core().createCodeSnippetStatement();
                String value = String.format("System.out.println(\"CALLER:%s,METHOD:%s,CALLEE:%s;\");\n",
                        element.getExecutable().getParent(CtClass.class).getSimpleName(),
                        element.getExecutable().getSignature(),
                        element.getTarget());
                snippet.setValue(value);
                try{
                    element.insertBefore(snippet);
                    System.out.println(element.getExecutable().getSignature());
                }catch (Exception e){

                }
            }
        });
        launcher.run();
    }
}
