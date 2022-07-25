package com.sensorsdata.analytics.android.plugin;

import com.sun.tools.javac.tree.JCTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SensorsAnalyticsConfig {
    private final static List<SensorsAnalyticsMethodCell> sInterfaceMethods = new ArrayList<>();

    static {
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onClick", "void",
            Collections.singletonList("View"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onClick", "void",
            Collections.singletonList("android.view.View"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onClick", "void", Arrays.asList(
            "DialogInterface", "int"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onClick", "void", Arrays.asList(
            "DialogInterface", "int", "boolean"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onOptionsItemSelected", "boolean",
            Collections.singletonList("MenuItem"), SensorsAnalyticsInsertLocation.BEFORE));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onContextItemSelected", "boolean",
            Collections.singletonList("MenuItem"), SensorsAnalyticsInsertLocation.BEFORE));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onCheckedChanged", "void",
            Arrays.asList("CompoundButton", "boolean"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onRatingChanged", "void",
            Arrays.asList("RatingBar", "float", "boolean"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onStopTrackingTouch", "void",
            Collections.singletonList("SeekBar"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onItemSelected", "void",
            Arrays.asList("AdapterView<?>", "View", "int", "long"),
            SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onItemClick", "void",
            Arrays.asList("AdapterView<?>", "View", "int", "long"),
            SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onTabChanged", "void",
            Collections.singletonList("String"), SensorsAnalyticsInsertLocation.AFTER));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onGroupClick", "boolean",
            Arrays.asList("ExpandableListView", "View", "int", "long"),
            SensorsAnalyticsInsertLocation.BEFORE));
        sInterfaceMethods.add(new SensorsAnalyticsMethodCell("onChildClick", "boolean",
            Arrays.asList("ExpandableListView", "View", "int", "int", "long"),
            SensorsAnalyticsInsertLocation.BEFORE));
    }

    private static boolean isParamsMatched(JCTree.JCMethodDecl jcMethodDecl,
                                           List<String> paramsList) {
        boolean isMatched = true;
        for (int i = 0; i < jcMethodDecl.getParameters().size(); i++) {
            if (!jcMethodDecl.getParameters().get(i).vartype.toString().equals(paramsList.get(i))) {
                isMatched = false;
                break;
            }
        }
        return isMatched;
    }

    public static SensorsAnalyticsMethodCell isMatched(JCTree.JCMethodDecl jcMethodDecl) {
        for (SensorsAnalyticsMethodCell methodCell : sInterfaceMethods) {
            if (jcMethodDecl.getName().toString().equals(methodCell.getName())) {
                if (jcMethodDecl.getReturnType().toString().equals(methodCell.getReturnType())) {
                    if (jcMethodDecl.getParameters().size() == methodCell.getParamsType().size()) {
                        if (isParamsMatched(jcMethodDecl, methodCell.getParamsType())) {
                            return methodCell;
                        }
                    }
                }
            }
        }
        return null;
    }
}
