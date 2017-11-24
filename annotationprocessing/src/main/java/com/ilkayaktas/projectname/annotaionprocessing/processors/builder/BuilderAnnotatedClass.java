package com.ilkayaktas.projectname.annotaionprocessing.processors.builder;

import javax.annotation.processing.Messager;
import javax.lang.model.element.Element;
import javax.tools.Diagnostic;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aselsan on 24.11.2017 at 10:56.
 */

public class BuilderAnnotatedClass {
    public String className;
    public List<FieldsOfClass> fields = new ArrayList<>();
    public Messager messager; // for logging purposed field

    public BuilderAnnotatedClass(Element e, Messager messager) {
        this.messager = messager;

        if(e.getKind().isField()){
            log("___"+e.asType().toString() + " "+ e.getSimpleName().toString());
        }
    }

    class FieldsOfClass{
        boolean isPrimitive;
        String fieldName;
        String type;
    }

    private void log(String message){
        messager.printMessage(
                Diagnostic.Kind.WARNING,message);
    }
}
