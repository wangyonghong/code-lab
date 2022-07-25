package com.sensorsdata.analytics.android.plugin;

import com.sun.source.util.Trees;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.tools.Diagnostic;

@SuppressWarnings({"unused", "FieldCanBeLocal"})
public class SensorsAnalyticsTreeTranslator extends TreeTranslator {
    private Trees trees;
    private TreeMaker make;
    private Name.Table names;
    private Messager messager;

    SensorsAnalyticsTreeTranslator(ProcessingEnvironment env) {
        messager = env.getMessager();
        trees = Trees.instance(env);
        Context context = ((JavacProcessingEnvironment)
            env).getContext();
        make = TreeMaker.instance(context);
        names = Names.instance(context).table;
    }

    private void log(String message) {
        messager.printMessage(Diagnostic.Kind.NOTE, message);
    }

    @Override
    public void visitClassDef(JCTree.JCClassDecl jcClassDecl) {
        super.visitClassDef(jcClassDecl);
    }

    private void insertTrackCode(JCTree.JCMethodDecl jcMethodDecl, int paramsCount,
                                 SensorsAnalyticsInsertLocation sensorsAnalyticsInsertLocation) {
        JCTree.JCExpression sdkExpression = make.Ident(names.fromString("com"));
        sdkExpression = make.Select(sdkExpression, names.fromString("sensorsdata"));
        sdkExpression = make.Select(sdkExpression, names.fromString("analytics"));
        sdkExpression = make.Select(sdkExpression, names.fromString("android"));
        sdkExpression = make.Select(sdkExpression, names.fromString("sdk"));
        sdkExpression = make.Select(sdkExpression, names.fromString("SensorsDataAutoTrackHelper"));
        sdkExpression = make.Select(sdkExpression, names.fromString("trackViewOnClick"));

        List<JCTree.JCExpression> paramsList = List.nil();
        for (int i = 0; i < paramsCount; i++) {
            paramsList = paramsList.append(make.Ident(jcMethodDecl.getParameters().get(i).name));
        }

        JCTree.JCStatement statement = make.Exec(
            make.Apply(List.<JCTree.JCExpression>nil(),
                sdkExpression,
                paramsList
            )
        );

        if (sensorsAnalyticsInsertLocation == SensorsAnalyticsInsertLocation.AFTER) {
            jcMethodDecl.body.stats = jcMethodDecl.body.stats.appendList(List.of(statement));
        } else {
            jcMethodDecl.body.stats = jcMethodDecl.body.stats.prependList(List.of(statement));
        }
    }

    @Override
    public void visitMethodDef(JCTree.JCMethodDecl jcMethodDecl) {
        super.visitMethodDef(jcMethodDecl);

        try {
            List<JCTree.JCAnnotation> annotationList = jcMethodDecl.getModifiers().annotations;
            if (annotationList.toString().startsWith("@OnClick") ||
                annotationList.toString().contains("@SensorsDataTrackViewOnClick()")) {
                insertTrackCode(jcMethodDecl, 1, SensorsAnalyticsInsertLocation.AFTER);
                return;
            }

            SensorsAnalyticsMethodCell methodCell = SensorsAnalyticsConfig.isMatched(jcMethodDecl);
            if (methodCell != null) {
                insertTrackCode(jcMethodDecl, methodCell.getParamsType().size(),
                    methodCell.getInsertLocation());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
