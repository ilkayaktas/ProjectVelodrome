package com.ilkayaktas.projectname.annotaionprocessing.processors.builder;

import com.google.auto.service.AutoService;
import com.ilkayaktas.projectname.annotaionprocessing.annotations.BuilderPattern;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AutoService(Processor.class)
public class BuilderProcessor extends AbstractProcessor {

    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();
        annotations.add(BuilderPattern.class.getCanonicalName());/*You can add other processors here*/
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations,
                           RoundEnvironment roundEnv) {

        log("Salutation from ilkay. Now is time to generate codes.");
        for (TypeElement element : annotations) {
            log("Supported annotations by BuilderProcessor: "+element.getSimpleName().toString());
        }

        // Itearate over all @annotations.BuilderPattern annotated elements
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(BuilderPattern.class)) {
            String className = annotatedElement.toString();

            // Check if a class has been annotated with @annotations.BuilderPattern
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                error(annotatedElement, "Only classes can be annotated with @%s",
                        BuilderPattern.class.getSimpleName());
                return true; // Exit processing
            }

            // We can cast it, because we know that it of ElementKind.CLASS
            TypeElement typeElement = (TypeElement) annotatedElement;


            log("BuilderPattern annotated class name: "+className);

            // Get all fields of annotated class
            List<BuilderAnnotatedClass> listOfFields = new ArrayList<>( );
            for (Element e : typeElement.getEnclosedElements()) {
                // if the element is field and acces modifier is public, than use it
                if(e.getKind().isField() && e.getModifiers().contains(Modifier.PUBLIC)){
                    listOfFields.add(new BuilderAnnotatedClass(e));
                }
            }

            try {
                writeBuilderFile(className, listOfFields);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            // Now we have all the information we need to generate a builder class:
            // the name of the source class, all its setter names, and their argument types.

//            try {
//                writeBuilderFile(className, setterMap);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return false;
    }

    private void log(String message){
        messager.printMessage(
                Diagnostic.Kind.WARNING,"### "+message);
    }

    private void error(Element e, String msg, Object... args) {
        messager.printMessage(
                Diagnostic.Kind.ERROR,
                "### "+String.format(msg, args),
                e);
    }


    private void writeBuilderFile(String className, List<BuilderAnnotatedClass> listOfFields) throws IOException {

        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String simpleClassName = className.substring(lastDot + 1);
        String builderClassName = className + "Builder";
        String builderSimpleClassName = builderClassName
                .substring(lastDot + 1);

        JavaFileObject builderFile = processingEnv.getFiler()
                .createSourceFile(builderClassName);

        try (PrintWriter out = new PrintWriter(builderFile.openWriter())) {

            if (packageName != null) {
                out.print("package ");
                out.print(packageName);
                out.println(";");
                out.println();
            }

            out.println("public class "+builderSimpleClassName+" {");

            out.println("    private static " + builderSimpleClassName + " thisObject;");
            out.println("    private static "+simpleClassName+" object;");

            // private constructor
            out.println("   private " + builderSimpleClassName +"(){}");
            out.println();

            out.println("   public static " + builderSimpleClassName +" instance(){");
            out.println("      if(thisObject == null) {");
            out.println("         thisObject = new "+builderSimpleClassName+"();");
            out.println("      }");
            out.println("      object = new "+simpleClassName+"();");
            out.println("      return thisObject;");
            out.println("   }");
            // public instance method
            out.println();
            // build method
            out.println("    public "+simpleClassName+" build() {");
            out.println("        return object;");
            out.println("    }");
            out.println();

            for (BuilderAnnotatedClass field : listOfFields) {
                String methodName = field.fieldName;
                String argumentType = field.qualifiedSuperClassName;

                out.println("    public "+builderSimpleClassName+" "+methodName+"("+argumentType+" value) {");
                out.println("        object."+methodName+" = value;");
                out.println("        return thisObject;");
                out.println("    }");
                out.println();
            }

            out.println("}");
        }
    }

    class BuilderAnnotatedClass{
        Element annotatedClassElement;
        String qualifiedSuperClassName;
        String fieldName;

        public BuilderAnnotatedClass(Element classElement) throws IllegalArgumentException {
            this.annotatedClassElement = classElement;
            // Here we access the @annotations.Factory annotation and check if the id is not empty.
            BuilderPattern annotation = classElement.getAnnotation(BuilderPattern.class);

            // Get the full QualifiedTypeName
            try {
                // The class is already compiled:
                // This is the case if a third party .jar contains compiled .class files with @annotations.Factory annotations.
                // In that case we can directly access the Class like we do in the try-block.

                qualifiedSuperClassName = annotatedClassElement.asType().toString();
                fieldName = annotatedClassElement.getSimpleName().toString();
            } catch (MirroredTypeException mte) {
                // The class is not compiled yet: This will be the case if we try to compile our source code
                // which has @annotations.Factory annotations.
                // Trying to access the Class directly throws a MirroredTypeException.
                // Fortunately MirroredTypeException contains a TypeMirror representation of our not yet compiled class.
                // Since we know that it must be type of class
                // (we have already checked that before) we can cast it to DeclaredType
                // and access TypeElement to read the qualified name.
                DeclaredType classTypeMirror = (DeclaredType) mte.getTypeMirror();
                TypeElement classTypeElement = (TypeElement) classTypeMirror.asElement();
                qualifiedSuperClassName = classTypeElement.getQualifiedName().toString();
                fieldName = classTypeElement.getSimpleName().toString();
            } finally {
                log("Type of field in annotated class:   ("+qualifiedSuperClassName + ")    " + fieldName);
            }
        }
    }
}