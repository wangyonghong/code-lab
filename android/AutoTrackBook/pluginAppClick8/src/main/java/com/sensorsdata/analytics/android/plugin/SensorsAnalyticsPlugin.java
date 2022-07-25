package com.sensorsdata.analytics.android.plugin;

import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("*")
@AutoService(Processor.class)
public class SensorsAnalyticsPlugin extends AbstractProcessor {
    private ProcessingEnvironment processingEnvironment;
    private Trees trees;
//    private TreeMaker make;
//    private Name.Table names;
//    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment env) {
        super.init(env);
        processingEnvironment = env;
//        messager = env.getMessager();
        trees = Trees.instance(env);
        Context context = ((JavacProcessingEnvironment) env).getContext();
//        make = TreeMaker.instance(context);
//        names = Names.instance(context).table;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (!roundEnvironment.processingOver()) {
            Set<? extends Element> elements = roundEnvironment.getRootElements();
            for (Element element : elements) {
                if (element.getKind() == ElementKind.CLASS) {
                    JCTree tree = (JCTree) trees.getTree(element);
                    TreeTranslator visitor =
                        new SensorsAnalyticsTreeTranslator(processingEnvironment);
                    tree.accept(visitor);
                }
            }
        }
        return false;
    }
}
